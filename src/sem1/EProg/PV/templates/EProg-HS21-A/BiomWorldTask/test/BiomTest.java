import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class BiomTest {
	
/** a) **/
	
	@Test
	public void testGetBiomName() {
        String [][] input = {
        		{"W","F","W","W"},
        		{"F","F","F","F"},
        		{"W","W","W","W"},
        		{"F","F","W","W"}
        };
        
        String [][] expectedType = {
        		{"W","F","W","W"},
        		{"F","F","F","F"},
        		{"W","W","W","W"},
        		{"F","F","W","W"}
        };
        
        World w = new World(input);
        
        assertArrayEquals(expectedType, actualBiomTypes(w, input.length));
	}
	
	@Test
	public void testGetBiomFlora() {
        String [][] input = {
        		{"W","F","W","W"},
        		{"F","F","F","F"},
        		{"W","W","W","W"},
        		{"F","F","W","W"}
        };

        
        int [] [] expectedFlora = {
        		{15,12,15,15},
        		{12,12,12,12},
        		{15,15,15,15},
        		{12,12,15,15}
        };
        
        int [] [] expectedHeights = {
        		{0,3,0,0},
        		{3,3,3,3},
        		{0,0,0,0},
        		{3,3,0,0}
        };
        
        World w = new World(input);
        
        assertArrayEquals(expectedFlora, actualFlora(w, expectedFlora.length));
        assertArrayEquals(expectedHeights, actualHeights(w, expectedHeights.length));
	}
	
/** b) **/
	@Test
	public void testDryUpWater() {
        String [][] input = {
        		{"W","W","W","W"},
        		{"W","W","W","W"},
        		{"W","W","W","W"},
        		{"W","W","W","W"},
        };
        
        World w = new World(input);
        
        w.stepDryUp();
        
        int [][] expectedFlora1 = {
        		{10,10,10,10},
        		{10,10,10,10},
        		{10,10,10,10},
        		{10,10,10,10},        		
        };
                
        assertArrayEquals(expectedFlora1, actualFlora(w, expectedFlora1.length));
        
        w.stepDryUp();
        
        int [][] expectedFlora2 = {
        		{5,5,5,5},
        		{5,5,5,5},
        		{5,5,5,5},
        		{5,5,5,5},        		
        };

        assertArrayEquals(expectedFlora2, actualFlora(w, expectedFlora2.length));
        
        w.stepDryUp(); 
        w.stepDryUp();
        
        int [][] expectedFlora4 = {
        		{0,0,0,0},
        		{0,0,0,0},
        		{0,0,0,0},
        		{0,0,0,0},        		
        };
        
        assertArrayEquals(expectedFlora4, actualFlora(w, expectedFlora4.length));
	}
	
	@Test
	public void testDryUpGeneral() {
        String [][] input = {
        		{"F","W","W"},
        		{"F","F","W"},
        		{"F","W","W"},
        };
        
        World w = new World(input);
        w.stepDryUp(); 
        
        String [][] expectedType1 = {
        		{"F","W","W"},
        		{"F","F","W"},
        		{"F","W","W"},
        };
        
        int [][] expectedFlora1 = {
        		{9,10,10},
        		{9,9,10},
        		{9,10,10},
        };
        
        int [][] expectedHeights1 = {
        		{2,0,0},
        		{2,2,0},
        		{2,0,0},
        };
        
        assertArrayEquals(expectedType1, actualBiomTypes(w, expectedType1.length));
        assertArrayEquals(expectedFlora1, actualFlora(w, expectedFlora1.length));
        assertArrayEquals(expectedHeights1, actualHeights(w, expectedHeights1.length));
        
        w.stepDryUp();
        w.stepDryUp();
        
        String [][] expectedType3 = {
        		{"W","W","W"},
        		{"W","W","W"},
        		{"W","W","W"},
        };
        
        int [][] expectedFlora3 = {
        		{3,0,0},
        		{3,3,0},
        		{3,0,0},
        };
        
        int [][] expectedHeights3 = {
        		{0,0,0},
        		{0,0,0},
        		{0,0,0},
        };

        assertArrayEquals(expectedType3, actualBiomTypes(w, expectedType3.length));
        assertArrayEquals(expectedFlora3, actualFlora(w, expectedFlora3.length));
        assertArrayEquals(expectedHeights3, actualHeights(w, expectedHeights3.length));
        
        w.stepDryUp();
        
        assertArrayEquals(expectedHeights3, actualHeights(w, expectedHeights3.length));        
	}
	
/** c) **/
	@Test
	public void testDistributeAndDryUp() {
        String [][] input = {
        		{"F","W","W"},
        		{"F","F","W"},
        		{"F","W","W"},
        };
        
        World w = new World(input);
        w.stepDistribute(1);
        
        String [][] expectedType1 = {
        		{"F","W","W"},
        		{"F","F","W"},
        		{"F","W","W"},
        };
        
        int [][] expectedFlora1 = {
        		{27,39,30},
        		{36,57,42},
        		{27,39,30},
        };
        
        int [][] expectedHeights1 = {
        		{4,0,0},
        		{6,4,0},
        		{4,0,0},
        };
        
        assertArrayEquals(expectedType1, actualBiomTypes(w, expectedType1.length));
        assertArrayEquals(expectedFlora1, actualFlora(w, expectedFlora1.length));
        assertArrayEquals(expectedHeights1, actualHeights(w, expectedHeights1.length));
        
        w.stepDryUp();
        
        String [][] expectedType2 = {
        		{"F","W","W"},
        		{"F","F","W"},
        		{"F","W","W"},
        };
        
        int [][] expectedFlora2 = {
        		{24,34,25},
        		{33,54,37},
        		{24,34,25},
        };
        
        int [][] expectedHeights2 = {
        		{3,0,0},
        		{5,3,0},
        		{3,0,0},
        };
        
        assertArrayEquals(expectedType2, actualBiomTypes(w, expectedType2.length));
        assertArrayEquals(expectedFlora2, actualFlora(w, expectedFlora2.length));
        assertArrayEquals(expectedHeights2, actualHeights(w, expectedHeights2.length));
	}
	
/** Helper methods **/	
	public static String [][] actualBiomTypes(World w, int size) {
		String [][] types = new String[size][size];
        for(int i = 0; i < size; i++) {
        	for(int j = 0; j < size; j++) {
        		assertNotNull(w.getBiom(i, j));
        		types[i][j] = w.getBiom(i, j).getBiomType();
        	}
        }
        
        return types;
	}

	public static int [][] actualFlora(World w, int size) {
		int [][] flora = new int[size][size];
        for(int i = 0; i < size; i++) {
        	for(int j = 0; j < size; j++) {
        		assertNotNull(w.getBiom(i, j));
        		flora[i][j] = w.getBiom(i, j).getFlora();
        	}
        }
        
        return flora;
	}
	
	public static int [][] actualHeights(World w, int size) {
		int [][] height = new int[size][size];
        for(int i = 0; i < size; i++) {
        	for(int j = 0; j < size; j++) {
        		assertNotNull(w.getBiom(i, j));
        		height[i][j] = w.getBiom(i, j).getHeight();
        	}
        }
        
        return height;
	}
}
