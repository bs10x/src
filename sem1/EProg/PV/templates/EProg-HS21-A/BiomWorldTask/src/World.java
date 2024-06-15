public class World {

    private int size;
    // TODO: weitere Attribute nach Wahl
    
	@Override 
	public String toString() {
    	String str = "";
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
            	str += this.getBiom(x, y).getBiomType();
            }
            str += System.lineSeparator();
        }
        return str;
	}
	
    public World(String [] [] biomGrid) {
        size = biomGrid.length;

    	if(biomGrid.length < 2) {
    		throw new IllegalArgumentException();
    	}
    	
        for (int i = 0; i < biomGrid.length; i++) {
        	if(biomGrid[i].length != biomGrid.length) {
        		throw new IllegalArgumentException();        		
        	}
        			
        	for(int j = 0; j < biomGrid[i].length; j++) {
        		String biomRepr = biomGrid[i][j];
        		
        		if(biomRepr.equals("W")) {
                    // TODO Element (i,j) ist ein Wasser Biom
        			
        		} else if(biomRepr.equals("F")) {
                    // TODO Element (i,j) ist ein Flachland Biom
        			
        		} else {
        			throw new IllegalArgumentException();
        		}
        	}
        }
    }

    public Biom getBiom(int x, int y) {
        // TODO 
        return null;
    }

    public void stepDryUp() {
        // TODO
    }

    public void stepDistribute(int p) {
        // TODO
    }
    
}

