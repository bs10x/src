import gui.Window;
import gui.component.Clickable;
import gui.component.Drawable;
import gui.component.Hoverable;
import gui.component.Rectangle;

class NightModeButton implements Drawable, Hoverable, Clickable {
    
    static Rectangle box = new Rectangle(10, 10, 92, 32);
    
    SwissMap map;
    boolean hover;

    public NightModeButton(SwissMap map) {
        this.map = map;
    }

    @Override
    public Rectangle getBoundingBox() {
        return box;
    }

    @Override
    public void onLeftClick(double x, double y) {
        map.nightMode = !map.nightMode;
    }

    @Override
    public void onRightClick(double x, double y) {
        // Do nothing
    }

    @Override
    public void onMouseEnter() {
        hover = true;
    }

    @Override
    public void onMouseExit() {
        hover = false;
    }

    @Override
    public void draw(Window window) {
        if(map.nightMode && hover) {
            window.setColor(100, 100, 100);
        } else if(map.nightMode) {
            window.setColor(70, 70, 70);
        } else if(hover) {
            window.setColor(200, 200, 200);
        } else {
            window.setColor(220, 220, 220);
        }
        window.fillRect(box.x, box.y, box.width, box.height);
        
        if(map.nightMode) {
            window.setColor(255, 255, 255);
        } else {
            window.setColor(0, 0, 0);
        }
        window.setBold(false);
        window.setFontSize(14);
        window.drawString(map.nightMode ? " Day mode" : "Night mode", 20, 30);
    }
}