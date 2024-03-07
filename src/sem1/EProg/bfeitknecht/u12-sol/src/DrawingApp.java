

import static java.lang.Double.isFinite;
import static java.lang.Math.round;
import static java.util.Collections.min;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DrawingApp {

	/* alpha = i * ((2*PI) * (1 / 6.05));
	   size = (0.25 * cos(t/2)) + 0.75;
	   x = cos(alpha + (0.3 * t)) * size;
	   y = sin((1.5 * alpha) + t) * size;
	   r = (cos(alpha + (2 * t)) + 1) / 2;
	   g = (sin(alpha + (2 * t)) + 1) / 2;
	   b = (cos(alpha +  (PI/2)) + 1) / 2;
	 */
    
    
    /* MANDELBROT
        T = (t/2);
        col = i/n;
        
        xo = (0.5*(sin(T/5)) )-0.21;
        yo = ((0.7*cos(T)));

        xtemp = ((x^2) - (y^2)) + xo;
        y = ((2 * x) * y) + yo;
        x = xtemp;
        
        r = 0.2 + (col^0.3);
        g = 0.2+(col^0.9);
        b = 0.2+(col^0.1);
     */
	
	public static void main(String[] args) {
		new DrawingApp(-1.1, 1.1, -1.1, 1.1).run();
	}

	private PlotterWindow window;
	private final double minX;
	private final double maxX;
	private final double minY;
	private final double maxY;

	private Program program;
	private String compileError = "";
	private long zeroTime;

	private static final int FPS_HISTORY_LENGTH = 10;
	private final LinkedList<Double> fpsHistory = new LinkedList<>();

	private int iterations;

	public DrawingApp(double minX, double maxX, double minY, double maxY) {
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;

		window = new PlotterWindow(700, 600);
		window.setResizable(true);
		window.setFontSize(12);
	}

	private void run() {
		window.open();

		String oldProgram = "";
		while (window.isOpen()) {
			if (!oldProgram.equals(window.getProgram())) {
				oldProgram = window.getProgram();
				setProgram(oldProgram);
			}
			setRepetitions(window.getRepetitions());

			int height = (int) window.getHeight();

			if (program == null) {
				window.setColor(255, 0, 0);
				window.drawString(compileError, 10, height - 10);
			} else {
				try {
					long startTime = System.nanoTime();

					drawAxes();
					executeAndDraw();

					long drawTime = System.nanoTime() - startTime;
					drawFps(1000000000.0 / drawTime);
				} catch (ExecutionException e) {
					window.setColor(255, 0, 0);
					window.drawString("Execution error: " + e.getMessage(), 10, height - 10);
				}
			}

			window.refreshAndClear(20);
		}
	}

	/**
	 * runs the program and draws the calculated lines
	 */
	private void executeAndDraw() throws ExecutionException {
		double[] xs = new double[iterations];
		double[] ys = new double[iterations];
		Color[] colors = new Color[iterations];

		Map<String, Double> vars = new HashMap<>();
		vars.put("x", 0.0);
		vars.put("y", 0.0);
		vars.put("r", 0.0);
		vars.put("g", 0.0);
		vars.put("b", 0.0);
		vars.put("n", (double) (iterations - 1));
		vars.put("PI", Math.PI);
		vars.put("E", Math.E);
		vars.put("t", (System.currentTimeMillis() - zeroTime) / 1000.0);

		for (int i = 0; i < iterations; i++) {
			vars.put("i", (double) i);
			program.execute(vars);
			xs[i] = vars.get("x");
			ys[i] = vars.get("y");
			int r = (int) (vars.get("r") * 255);
			int g = (int) (vars.get("g") * 255);
			int b = (int) (vars.get("b") * 255);
			colors[i] = new Color(clamp(r), clamp(g), clamp(b));
		}

		drawLines(xs, ys, colors);
	}

	/**
	 * Sets the program to be "drawn" on the panel.
	 */
	public void setProgram(String programString) {
		try {
			this.program = new Compiler().compile(programString);
			this.compileError = "";
		} catch (ProgramException e) {
			this.compileError = e.getMessage();
			this.program = null;
		}

		zeroTime = System.currentTimeMillis();
	}

	/**
	 * Sets the number of repetitions <i>n</i>. The program will be executed <i>n + 1</i> times and
	 * hence <i>n</i> lines will be drawn.
	 */
	public void setRepetitions(int repetitions) {
		if (repetitions < 1) {
			throw new IllegalArgumentException("repetitions must be positive");
		}

		this.iterations = repetitions + 1; // need at least two points to draw something
	}

	/**
	 * "Clamps" the given double value into the [0,1] interval and returns it as a float.
	 */
	private static int clamp(int channel) {
		return Math.min(Math.max(channel, 0), 255);
	}

	/**
	 * Draws the axes of the coordinate system and ticks for every unit.
	 */
	private void drawAxes() {
		int tickHalfLength = 3;

		window.setColor(new Color(191, 191, 191));
		int zeroGuiX = toGuiX(0);
		int zeroGuiY = toGuiY(0);
		window.drawLine(0, zeroGuiY, window.getWidth(), zeroGuiY);
		window.drawLine(zeroGuiX, 0, zeroGuiX, window.getHeight());

		for (long x = round(minX); x < maxX; x++) {
			if (x != 0) {
				int guiX = toGuiX(x);
				window.drawLine(guiX, zeroGuiY - tickHalfLength, guiX, zeroGuiY + tickHalfLength);
			}
		}

		for (long y = round(minY); y < maxY; y++) {
			if (y != 0) {
				int guiY = toGuiY(y);
				window.drawLine(zeroGuiX - tickHalfLength, guiY, zeroGuiX + tickHalfLength, guiY);
			}
		}
	}

	/**
	 * Draws the lines given by the x and y coordinates and the colors. Note that the first color in
	 * the array is ignored, since the color of each line is determined by the end point.
	 */
	private void drawLines(double[] xs, double[] ys, Color[] colors) {
		for (int i = 1; i < xs.length; i++) {
			if (isFinite(xs[i - 1]) && isFinite(ys[i - 1]) && isFinite(xs[i]) && isFinite(ys[i])) {
				window.setColor(colors[i]);
				window.drawLine(toGuiX(xs[i - 1]), toGuiY(ys[i - 1]), toGuiX(xs[i]), toGuiY(ys[i]));
			}
		}
	}

	/**
	 * Draws the number of frames per second that the panel could draw (the actual number of frames
	 * that are drawn is determined by how often {@link #repaint()} is called) in the lower right
	 * corner.
	 */
	private void drawFps(double newFps) {
		fpsHistory.addLast(newFps);
		if (fpsHistory.size() > FPS_HISTORY_LENGTH) {
			fpsHistory.removeFirst();
		}

		window.setColor(new Color(153, 153, 153));
		String text = String.format("FPS (min): %.1f", min(fpsHistory));
		int textWidth = text.length() * 7;
		window.drawString(text, window.getWidth() - textWidth - 10, window.getHeight() - 10);
	}

	/**
	 * Converts an x value in the "program" coordinate system to a GUI x coordinate.
	 */
	private int toGuiX(double x) {
		return (int) ((x - minX) / (maxX - minX) * window.getWidth());
	}

	/**
	 * Converts a y value in the "program" coordinate system to a GUI y coordinate.
	 */
	private int toGuiY(double y) {
		return (int) ((1 - (y - minY) / (maxY - minY)) * window.getHeight());
	}
}
