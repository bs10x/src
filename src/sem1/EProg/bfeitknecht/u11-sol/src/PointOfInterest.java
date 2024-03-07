import gui.Window;
import gui.component.*;

public class PointOfInterest implements Drawable, Hoverable {

    static double HOVER_SIZE = 15;

    SwissMap map;

    /**
     * Name of this POI
     */
    String name;

    /**
     * East coordinate of this POI. Coordinates are in the Swiss coordinate system
     * (see <a href="https://en.wikipedia.org/wiki/Swiss_coordinate_system">Wikipedia</a>)
     */
    int east;

    /**
     * North coordinate of this POI. Coordinates are in the Swiss coordinate system
     * (see <a href="https://en.wikipedia.org/wiki/Swiss_coordinate_system">Wikipedia</a>)
     */
    int north;

    boolean hover;

    PointOfInterest(SwissMap map, String name, int east, int north) {
        this.map = map;
        this.name = name;
        this.east = east;
        this.north = north;
    }

    /**
     * Returns the description that should be displayed for this POI.
     */
    String description() {
        return name;
    }

    @Override
    public Rectangle getBoundingBox() {
        double guiX = map.toGuiX(east);
        double guiY = map.toGuiY(north);
        return new Rectangle(guiX - HOVER_SIZE / 2, guiY - HOVER_SIZE / 2,
                HOVER_SIZE, HOVER_SIZE);
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
        double guiX = map.toGuiX(east);
        double guiY = map.toGuiY(north);
        if (hover) {
            if (map.nightMode) {
                window.setColor(255, 255, 255);
            } else {
                window.setColor(0, 0, 0);
            }
            window.setFontSize(18);
            window.setBold(true);
            window.drawString(description(), guiX + HOVER_SIZE * 2, guiY + 6);
        }
    }
}