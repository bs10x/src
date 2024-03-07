import gui.Color;

public class City extends PointOfInterest {
    
    int inhabitants;
    double area;
    
    City(String name, int east, int north, int inhabitants, double area) {
        super(name, east, north);
        this.inhabitants = inhabitants;
        this.area = area;
    }
    
    Color color() {
        return new Color(200, 0, 0);
    }
    
    String description() {
        return super.description() + " (" + inhabitants + " Einw., " + area + " km²)";
    }
}