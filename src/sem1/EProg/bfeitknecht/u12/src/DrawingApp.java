
import static java.lang.Double.isFinite;
import static java.lang.Math.round;

public class DrawingApp {

	private PlotterWindow window;

	private final double minX;
	private final double maxX;
	private final double minY;
	private final double maxY;

	private String program;
	private int iterations;

	public static void main(String[] args) {
		new DrawingApp(-1.1, 1.1, -1.1, 1.1).run();
	}

	/**
	 * Creates a window that displays the area given by the mins and maxes.
	 */
	public DrawingApp(double minX, double maxX, double minY, double maxY) {
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;

		window = new PlotterWindow(700, 600);
		window.setResizable(true);
		window.setFontSize(12);
	}

	/**
	 * opens a window and runs the main-loop until the window is closed
	 */
	private void run() {
		window.open();

		while (window.isOpen()) {
			/* read updated values from the window */
			if (!window.getProgram().equals(program)) {
				setProgram(window.getProgram());
			}
			setRepetitions(window.getRepetitions());

			int height = (int) window.getHeight();
			try {
				long startTime = System.nanoTime();

				double[] xs = new double[iterations];
				double[] ys = new double[iterations];
				Color[] colors = new Color[iterations];

				
				
				// TODO: Interpret the program 'iterations' times, retrieve the values of x, y, and
				// the color for each iteration and store them in xs, ys, and colors.
				if (program != null) {
					throw new ProgramException("not implemented yet");
				}

				
				
				drawAxes();
				drawLines(xs, ys, colors);

				long drawTime = System.nanoTime() - startTime;
				drawFps(1000000000.0 / drawTime);
			} catch (ProgramException e) {
				window.setColor(255, 0, 0);
				window.drawString("Execution error: " + e.getMessage(), 10, height - 10);
			}

			window.refreshAndClear(20);
		}
	}

	/**
	 * Sets the program to be "drawn" on the panel.
	 */
	public void setProgram(String programText) {
		program = programText;
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
	private void drawFps(double fps) {
		window.setColor(new Color(153, 153, 153));
		String text = String.format("FPS (min): %.1f", fps);
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
