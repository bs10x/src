import gui.Color;

public class City extends PointOfInterest {
	
	String name;
	int east;
	int north;
	
	int population;
	double area;

	
	
	City(String name, int east, int north, int population, double area) {
		super(name, east, north);
		
		this.name = name;
		this.east = east;
		this.north = north;
		
		this.population = population;
		this.area = area;

	}
	
	Color color() {
		return new Color(255, 0, 0);
	}

	String description() {
		
		String description = "";
		description = name + " (" + area + " km², " + population + " Einwohner" + ")";
	
		return description;			
	}
}