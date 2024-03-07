import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PersonTest {
    
    @Test
    public void testBodyMassIndex() {
        assertEquals(25.00, new Person(25, 100, 200, true, 40, 20, 30).bodyMassIndex(), 0.01);
        assertEquals(24.69, new Person(25,  80, 180, true, 40, 20, 30).bodyMassIndex(), 0.01);
        assertEquals(24.22, new Person(25,  70, 170, true, 40, 20, 30).bodyMassIndex(), 0.01);
        assertEquals(23.44, new Person(25,  60, 160, true, 40, 20, 30).bodyMassIndex(), 0.01);
    }
}
