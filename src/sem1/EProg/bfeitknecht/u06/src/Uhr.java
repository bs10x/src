import gui.Window;

public class Uhr {
    private static final int SIZE = 400;

    public static void main(String[] args) {
        Window window = new Window("Uhr", SIZE, SIZE);
        window.open();
        
        while(window.isOpen()) {
            // TODO: draw clock
            
            // display everything and then fill the canvas with white:
            window.refreshAndClear(20);
        }
    }
}
