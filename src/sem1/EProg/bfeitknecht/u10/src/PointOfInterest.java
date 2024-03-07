import gui.Color;

public class PointOfInterest {
    

    String name;
    int east;
    int north;
    
    double guiX;
    double guiY;
    
    PointOfInterest(String name, int east, int north) {
        this.name = name;
        this.east = east;
        this.north = north;
    }
    
    Color color() {
        return new Color(0, 0, 0);
    }
    
    String description() {
        return name;
    }
    
}
