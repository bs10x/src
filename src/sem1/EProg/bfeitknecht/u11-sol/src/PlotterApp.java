import static java.lang.Double.isFinite;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.round;

public class PlotterApp {

    private static final int MOUSE_HIGHLIGHT_RADIUS = 3;

    public static void main(String[] args) {
        new PlotterApp(-6, 6).run();
    }

    private final double minX;
    private final double maxX;

    private PlotterWindow window;

    public PlotterApp(double minX, double maxX) {
        this.minX = minX;
        this.maxX = maxX;

        window = new PlotterWindow(500, 300);
        window.setResizable(true);
        window.setFontSize(12);
    }

    private void run() {
        window.open();

        while (window.isOpen()) {
            String function = window.getFunction();
            int width = (int) window.getWidth();

            try {
                long startTime = System.currentTimeMillis();

                double[] ys = new double[width];
                double minY = Double.POSITIVE_INFINITY;
                double maxY = Double.NEGATIVE_INFINITY;
                for (int guiX = 0; guiX < width; guiX++) {
                    double y = new ExprEvaluator(fromGuiX(guiX)).eval(function);
                    ys[guiX] = y;
                    if (isFinite(y)) {
                        minY = min(minY, ys[guiX]);
                        maxY = max(maxY, ys[guiX]);
                    }
                }
                long evalTime = System.currentTimeMillis() - startTime;

                drawAxes(minY, maxY);
                drawFunction(ys, minY, maxY);
                drawMouseHighlight(ys, minY, maxY);
                drawEvalTime(evalTime);
            } catch (EvaluationException e) {
                window.setColor(255, 0, 0);
                window.drawString(e.getMessage(), 10, window.getHeight() - 10);
            }

            window.refreshAndClear(20);
        }
    }

    private void drawAxes(double minY, double maxY) {
        int tickHalfLength = 3;
        int labelPad = 4;

        window.setColor(190, 190, 190);
        double zeroGuiX = toGuiX(0);
        double zeroGuiY = toGuiY(0, minY, maxY);
        window.drawLine(0, zeroGuiY, window.getWidth(), zeroGuiY);
        window.drawLine(zeroGuiX, 0, zeroGuiX, window.getHeight());

        for (long x = round(minX); x < maxX; x++) {
            if (x != 0) {
                double guiX = toGuiX(x);
                window.drawLine(guiX, zeroGuiY - tickHalfLength, guiX, zeroGuiY + tickHalfLength);
                window.drawString(Long.toString(x), guiX - 3, zeroGuiY - tickHalfLength - labelPad);
            }
        }

        double bottomY = fromGuiY(window.getHeight(), minY, maxY);
        double topY = fromGuiY(0, minY, maxY);
        for (long y = round(bottomY); y < topY; y++) {
            if (y != 0) {
                double guiY = toGuiY(y, minY, maxY);
                window.drawLine(zeroGuiX - tickHalfLength, guiY, zeroGuiX + tickHalfLength, guiY);
                window.drawString(Long.toString(y), zeroGuiX + tickHalfLength + labelPad, guiY + 4);
            }
        }
    }

    private void drawFunction(double[] ys, double minY, double maxY) {
        window.setColor(0, 0, 0);
        for (int guiX = 1; guiX < ys.length; guiX++) {
            if (isFinite(ys[guiX - 1]) && isFinite(ys[guiX]))
                window.drawLine(guiX - 1, toGuiY(ys[guiX - 1], minY, maxY),
                        guiX, toGuiY(ys[guiX], minY, maxY));
        }
    }

    private void drawMouseHighlight(double[] ys, double minY, double maxY) {
        int mouseX = (int) window.getMouseX();
        if (mouseX >= 0 && mouseX < ys.length) {
            double y = ys[mouseX];
            int radius = MOUSE_HIGHLIGHT_RADIUS;
            window.setColor(255, 0, 0);
            window.fillOval(mouseX - radius, toGuiY(y, minY, maxY) - radius, 2 * radius + 1, 2 * radius + 1);

            String coords = String.format("x: %.3f, y: %.3f", fromGuiX(mouseX), y);
            window.setColor(150, 150, 150);
            window.drawString(coords, 10, window.getHeight() - 10);
        }
    }

    private void drawEvalTime(long evalTime) {
        window.setColor(150, 150, 150);
        String text = "Eval time: " + evalTime + " ms";
        window.drawString(text, window.getWidth() - 100, window.getHeight() - 10);
    }

    private double toGuiX(double x) {
        return (x - minX) / (maxX - minX) * window.getWidth();
    }

    private double toGuiY(double y, double minY, double maxY) {
        return (1 - (y - minY) / (maxY - minY)) * (window.getHeight() * 0.9) + window.getHeight() * 0.05;
    }

    private double fromGuiX(double guiX) {
        return guiX * 1.0 / window.getWidth() * (maxX - minX) + minX;
    }

    private double fromGuiY(double guiY, double minY, double maxY) {
        return (1 - (guiY - window.getHeight() * 0.05) / (window.getHeight() * 0.9)) * (maxY - minY) + minY;
    }
}
