import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		// Sie koennen die main Methode beliebig aendern.

		{
			Node n00 = new Node();
			
			Node n10 = new Node();
			
			Node n01 = new Node();
			
			n00.setDown(n10);
			n00.setRight(n01);
			
			Node n11 = new Node();
			
			n01.setDown(n11);
			
			n10.setRight(new Node());
			
			System.out.println("isPartialGrid Resultat (erwartet: false): " + PartialGrid.isPartialGrid(n00));
					
			n10.setRight(n11);
					
			System.out.println("isPartialGrid Resultat (erwartet: true): " + PartialGrid.isPartialGrid(n00));
		}
		
		{
			ArrayList<Coordinate> coordinates0 = new ArrayList<Coordinate>();
			coordinates0.add(new Coordinate(2,2));
			coordinates0.add(new Coordinate(2,4));
			
			System.out.println("isRepresentable Resultat (erwartet: false): " + PartialGrid.isRepresentable(coordinates0));
		}
		
		{
		
			ArrayList<Coordinate> coordinates1 = new ArrayList<Coordinate>();
			coordinates1.add(new Coordinate(2,2));
			coordinates1.add(new Coordinate(2,3));
			coordinates1.add(new Coordinate(2,1));
			
			System.out.println("isRepresentable Resultat (erwartet: true): " + PartialGrid.isRepresentable(coordinates1));
		}
	}

}
