import gui.Window;

public class PongGui {
    
    public static void main(String[] args) {
        int width = 800;
        int height = 400;
        
        PongGame game = new PongGame(width, height);
        
        Window window = new Window("Pong", width, height);
        window.open();
        while(window.isOpen()) {
            
            window.refreshAndClear(20);
        }
    }
}
