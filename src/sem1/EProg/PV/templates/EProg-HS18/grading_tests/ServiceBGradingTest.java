import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

public class ServiceBGradingTest {

    @Test
    public void testExample() {
        // contains a bit of extra whitespace to be ignored:
        String input = "111111004  5.0  5.0  6.0\n" + 
                "111111005  3.75 3.0  4.0\n\n" + 
                "111111006  4.5  2.25 4.0\n\n\n";
        Service service = new Service(new Scanner(input));
        List<Integer> top = service.top(2);
        assertTrue(top.equals(List.of(111111004, 111111006))
                || top.equals(List.of(111111004, 111111005)));
    }

    @Test
    public void testEmptyFile() {
        String input = "\n\n";
        Service service = new Service(new Scanner(input));
        assertEquals(Collections.emptyList(), service.top(10));
    }

    @Test
    public void testOneLineTop() {
        String input = "123 3.0 4.0 5.0\n";
        Service service = new Service(new Scanner(input));
        assertEquals(List.of(123), service.top(1));
    }

    @Test
    public void testOneLineTopRobust() {
        String input = "123 3.0 4.0 5.0\n";
        Service service = new Service(new Scanner(input));
        assertEquals(List.of(123), service.top(100));
    }

    @Test
    public void testTopSorted() {
        String input = "123 3.0 4.0 5.0\n"
                + "124 5.5 6.0 6.0\n"
                + "100 1.0 1.0 1.0\n";
        Service service = new Service(new Scanner(input));
        assertEquals(List.of(124), service.top(1));
        assertEquals(List.of(124, 123), service.top(2));
        
        input = "124 3.0 4.0 5.0\n"
                + "123 5.5 6.0 6.0\n"
                + "100 1.0 1.0 1.0\n";
        service = new Service(new Scanner(input));
        assertEquals(List.of(123), service.top(1));
        assertEquals(List.of(123, 124), service.top(2));
        
        input = "124 1.0 1.0 1.0\n"
                + "123 1.0 1.0 1.0\n";
        service = new Service(new Scanner(input));
        List<Integer> top = service.top(1);
        assertTrue(List.of(123).equals(top) 
                || List.of(124).equals(top));
        assertEquals(Set.of(123, 124), new HashSet<>(service.top(2)));
    }
}
