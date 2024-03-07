import gui.Color;

public class Mountain extends PointOfInterest {
    
    int height;

    Mountain(String name, int east, int north, int height) {
        super(name, east, north);
        this.height = height;
    }
    
    Color color() {
        return new Color(150, 150, 150);
    }
    
    String description() {
        return super.description() + " (" + height + " m.ü.M.)";
    }
}