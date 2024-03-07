
public class Mountain extends PointOfInterest {

    int height;

    Mountain(String name, int east, int north, int height) {
        super(name, east, north);
        this.height = height;
    }

    String description() {
        return super.description() + " (" + height + " m.ü.M.)";
    }
}