import gui.Color;

public class PointOfInterest {
    
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
    
    PointOfInterest(String name, int east, int north) {
        this.name = name;
        this.east = east;
        this.north = north;
    }
    
    /**
     * Returns the color in which this POI should be rendered on the map.
     */
    Color color() {
        return new Color(0, 0, 0);
    }
    
    /**
     * Returns the description that should be displayed for this POI.
     */
    String description() {
        return name;
    }
}