package bonus;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class WordServiceTest {

    @Test
    public void testExample() throws FileNotFoundException {
        WordScanner input = new WordScanner(new File("loremIpsum.txt"));
        WordService service = new WordService(input);
        List<MyWord> words = service.getWords();
	    List<MyWord> topWords = service.top(3);
	    
	    assertEquals(63, words.size());
	    assertContainsWord("non", Set.of(56), words);
	    assertContainsWord("ut", Set.of(13, 30, 19), words);
	    
	    assertEquals(3, topWords.size());
	    assertEquals("dolor", topWords.get(0).getWord());
	    assertEquals(Set.of(2, 39), topWords.get(0).getPositions());
	    assertEquals("dolore", topWords.get(1).getWord());
	    assertEquals(Set.of(16, 47), topWords.get(1).getPositions());
	    assertEquals("in", topWords.get(2).getWord());
	    assertEquals(Set.of(40, 59, 42), topWords.get(2).getPositions());
    }
    
    
    /** Tested ob 'expectedWord' mit den Positionen 'expectedePositions' in der Liste von Woertern 'words' vorkommt. */
    public static void assertContainsWord(String expectedWord, Set<Integer> expectedPositions, List<MyWord> words) {
    	Set<Integer> foundPositions = null;
    	
    	for (MyWord word : words) {
    		if (word.getWord().equals(expectedWord)) {
    			assertTrue(foundPositions == null);
    			foundPositions = word.getPositions();
    		}
    	}
    	
    	assertEquals(expectedPositions, foundPositions);
    }
}
