public class Coordinate {
	
  private double x;
  private double y;

  public Coordinate(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return this.x;
  }
  
  public double getY() {
    return this.y;
  }
  
  public double distance(Coordinate c) {
    return Math.sqrt( Math.pow(this.x-c.getX(), 2) + Math.pow(this.y-c.getY(), 2) );
  }
  
  public String toString(){//overriding the toString() method  
	  return "("+this.getX()+", "+this.getY()+")";  
  }
  
}