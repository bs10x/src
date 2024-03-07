import gui.Color;

public class Mountain extends PointOfInterest {
	
	String name;
	int east;
	int north;
	
	int height;
	
	
	Mountain(String name, int east, int north, int height) {
		super(name, east, north);
		
		this.name = name;
		this.east = east;
		this.north = north;
		
		this.height = height;
	}
	
	Color color() {
		return new Color(127, 127, 127);
	}

	String description() {
		
		String description = "";
		description = name + " (" + height + " m hoch" + ")";
		
		return description;		
	}
}