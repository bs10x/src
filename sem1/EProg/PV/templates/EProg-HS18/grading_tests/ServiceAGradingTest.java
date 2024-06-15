import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

public class ServiceAGradingTest {

    @Test
    public void testExample() {
        // contains a bit of extra whitespace to be ignored:
        String input = "111111004  5.0  5.0  6.0\n" + 
                "111111005  3.75 3.0  4.0\n\n" + 
                "111111006  4.5  2.25 4.0\n\n\n";
        Service service = new Service(new Scanner(input));
        assertEquals(Set.of(111111005), new HashSet<>(service.critical(4, 8)));
    }

    @Test
    public void testEmptyFile() {
        String input = "\n\n";
        Service service = new Service(new Scanner(input));
        assertEquals(Collections.emptyList(), service.critical(1, 1));
    }

    @Test
    public void testParamRange() {
        String input = "\n\n";
        Service service = new Service(new Scanner(input));
        assertEquals(Collections.emptyList(), service.critical(0, 0));
        assertEquals(Collections.emptyList(), service.critical(100, 100));
        
        input = "123  3.75 3.0  4.0\n" + 
                "124  3.75 4.0  3.0\n" + 
                "125  6.0  4.0  3.0\n" + 
                "126  1.0  6.0  6.0\n";
        service = new Service(new Scanner(input));
        assertEquals(Set.of(), new HashSet<>(service.critical(0, 0)));
        assertEquals(Set.of(123, 124, 125, 126), new HashSet<>(service.critical(100, 100)));
    }

    @Test
    public void testOneLineCritical() {
        String input = "123 3.0 4.0 5.0\n";
        Service service = new Service(new Scanner(input));
        assertEquals(List.of(), service.critical(1, 1));
        assertEquals(List.of(), service.critical(99, 1));
        assertEquals(List.of(), service.critical(1, 99));
        assertEquals(List.of(123), service.critical(3.1, 9.1));
    }

    @Test
    public void testSomeCriticals() {
        String input = "123  3.75 3.0  4.0\n" + 
                "124  3.75 4.0  3.0\n" + 
                "125  6.0  4.0  3.0\n" + 
                "126  1.0  6.0  6.0\n";
        Service service = new Service(new Scanner(input));
        assertEquals(Set.of(123, 124), new HashSet<>(service.critical(4, 8)));
    }

    @Test
    public void testCriticalSmallerEq() {
        String input = "123  4.0 4.0 3.0\n"  
                + "124  3.9 4.0 3.0\n"
                + "125  4.0 4.0 2.9\n"
                + "126  3.9 4.0 2.9\n";;
        Service service = new Service(new Scanner(input));
        assertEquals(Set.of(125, 126), new HashSet<>(service.critical(4, 7)));
    }
}
