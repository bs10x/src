import gui.Color;

public class Lake extends PointOfInterest {
	
	String name;
	int east;
	int north;
	
	int depth;
	double area;
	
	
	Lake(String name, int east, int north, double area, int depth) {
		super(name, east, north);
		
		this.name = name;
		this.east = east;
		this.north = north;
		
		this.depth = depth;
		this.area = area;	
	}
	
	Color color() {
		return new Color(0, 0, 255);
	}

	String description() {
		
		String description = "";
		description = name + " (" + area + " km², " + depth + " m tief)";
		
		return description;		
	}

}