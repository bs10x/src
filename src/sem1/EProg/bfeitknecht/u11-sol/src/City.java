import gui.Window;

public class City extends PointOfInterest {

    int inhabitants;
    double area;

    City(SwissMap map, String name, int east, int north, int inhabitants, double area) {
        super(map, name, east, north);
        this.inhabitants = inhabitants;
        this.area = area;
    }

    String description() {
        return super.description() + " (" + inhabitants + " Einw., " + area + " km²)";
    }

    @Override
    public void draw(Window window) {
        super.draw(window);

        double scale = hover ? 0.5 : 0.4;
        window.drawImageCentered("city" + (map.nightMode ? "_night" : "") + ".png",
                map.toGuiX(east), map.toGuiY(north), scale);
    }
}
