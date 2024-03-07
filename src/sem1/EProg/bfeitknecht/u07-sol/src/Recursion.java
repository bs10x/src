import gui.Window;

public class Recursion {

	private static final int SIZE = 500;

	public static void main(String[] args) {
		Window window = new Window("Recursion", SIZE, SIZE);
		window.open();

		while (window.isOpen()) {
			drawTree(window, SIZE / 2, SIZE - 10, 100, Math.PI / 2);
			window.refreshAndClear(20);
		}
	}

	public static void drawTree(Window win, double x, double y, double length, double angle) {
		double nx = x + Math.cos(angle) * length;
		double ny = y - Math.sin(angle) * length;

		win.setStrokeWidth(length / 20);
		win.setColor(0, 0, 0);
		win.drawLine(x, y, nx, ny);

		if (length < 10.0) {
			win.setColor(0, 180, 0);
			win.fillCircle(nx, ny, 3);
		} else {
			drawTree(win, nx, ny, length * 0.8, angle + Math.PI / 5);
			drawTree(win, nx, ny, length * 0.6, angle - Math.PI / 3);
		}
	}
}
