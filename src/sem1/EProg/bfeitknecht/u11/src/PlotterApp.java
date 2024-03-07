public class PlotterApp {

    public static void main(String[] args) {
        new PlotterApp(-6, 6, -1.5, 1.5).run();
    }

    private final double minX;
    private final double maxX;
    private final double minY;
    private final double maxY;

    private PlotterWindow window;

    public PlotterApp(double minX, double maxX, double minY, double maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;

        window = new PlotterWindow(500, 300);
        window.setResizable(true);
    }

    private void run() {
        window.open();
        while (window.isOpen()) {
            String function = window.getFunction();

            // TODO

            window.refreshAndClear(20);
        }
    }
}
