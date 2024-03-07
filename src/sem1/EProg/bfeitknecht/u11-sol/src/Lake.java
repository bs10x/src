import gui.Window;

public class Lake extends PointOfInterest {

    double area;
    int depth;

    Lake(SwissMap map, String name, int east, int north, double area, int depth) {
        super(map, name, east, north);
        this.area = area;
        this.depth = depth;
    }

    String description() {
        return super.description() + " (" + area + " km², " + depth + " m tief)";
    }

    @Override
    public void draw(Window window) {
        super.draw(window);

        double scale = hover ? 0.5 : 0.4;
        window.drawImageCentered("lake" + (map.nightMode ? "_night" : "") + ".png",
                map.toGuiX(east), map.toGuiY(north), scale);
    }
}