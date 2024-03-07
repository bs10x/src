
public class Lake extends PointOfInterest {

    double area;
    int depth;

    Lake(String name, int east, int north, double area, int depth) {
        super(name, east, north);
        this.area = area;
        this.depth = depth;
    }

    String description() {
        return super.description() + " (" + area + " km², " + depth + " m tief)";
    }
}