import gui.Window;

public class Mountain extends PointOfInterest {

    int height;

    Mountain(SwissMap map, String name, int east, int north, int height) {
        super(map, name, east, north);
        this.height = height;
    }

    String description() {
        return super.description() + " (" + height + " m.ü.M.)";
    }

    @Override
    public void draw(Window window) {
        super.draw(window);

        double scale = hover ? 0.5 : 0.4;
        window.drawImageCentered("mountain" + (map.nightMode ? "_night" : "") + ".png",
                map.toGuiX(east), map.toGuiY(north), scale);
    }
}