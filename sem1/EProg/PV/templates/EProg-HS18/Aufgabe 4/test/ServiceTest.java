import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

public class ServiceTest {

    @Test
    public void testExample() {
        // contains a bit of extra whitespace to be ignored:
        String input = "111111004  5.0  5.0  6.0\n" + 
                "111111005  3.75 3.0  4.0\n\n" + 
                "111111006  4.5  2.25 4.0\n\n\n";
        Service service = new Service(new Scanner(input));
        assertEquals(Set.of(111111005), new HashSet<>(service.critical(4, 8)));
        List<Integer> top = service.top(2);
        assertTrue(top.equals(List.of(111111004, 111111006))
                || top.equals(List.of(111111004, 111111005)));
    }
}
