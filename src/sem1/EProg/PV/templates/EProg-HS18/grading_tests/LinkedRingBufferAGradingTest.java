import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Consumer;

import org.junit.BeforeClass;
import org.junit.Test;

public class LinkedRingBufferAGradingTest {

    @BeforeClass
    public static void checkForChangedTypes() throws ReflectiveOperationException {
        Method method = Class.forName("TestRunner").getMethod("staticCheck", Consumer.class);
        method.invoke(null, (Consumer<PrintStream>) err -> {
            List<Class<?>> bufferFieldTypes = stream(LinkedRingBuffer.class.getDeclaredFields())
                    .map(Field::getType).sorted(comparing(Class::getName)).collect(toList());
            if (!List.of(Node.class, Node.class).equals(bufferFieldTypes)) {
                err.println("Warning: field types of LinkedRingBuffer changed to "
                        + bufferFieldTypes);
            }

            List<Class<?>> nodeFieldTypes = stream(Node.class.getDeclaredFields())
                    .map(Field::getType).sorted(comparing(Class::getName)).collect(toList());
            if (!List.of(Node.class, int.class).equals(nodeFieldTypes)) {
                err.println("Warning: field types of Node changed to " + nodeFieldTypes);
            }
        });
    }

    static void assertNoIllegalModifications() {
        List<String> bufferFieldNames = stream(LinkedRingBuffer.class.getDeclaredFields())
                .map(Field::getName).sorted().collect(toList());
        assertEquals("illegal mod!", List.of("first", "last"), bufferFieldNames);
        assertEquals("illegal mod!", Object.class, LinkedRingBuffer.class.getSuperclass());

        List<String> nodeFieldNames = stream(Node.class.getDeclaredFields())
                .map(Field::getName).sorted().collect(toList());
        assertEquals("illegal mod!", List.of("next", "value"), nodeFieldNames);
        assertEquals("illegal mod!", Object.class, Node.class.getSuperclass());
    }

    @Test
    public void testConstructorAndCapacity() {
        assertNoIllegalModifications();
        for (int cap : asList(2, 3, 4, 5, 10, 20, 100, 10000)) {
            assertEquals(cap, new LinkedRingBuffer(cap).capacity());
        }
    }

    @Test
    public void testConstructorAndCapacityOne() {
        assertNoIllegalModifications();
        // this is something of a special case
        assertEquals(1, new LinkedRingBuffer(1).capacity());
    }

    @Test
    public void testConstructorException() {
        try {
            new LinkedRingBuffer(0);
            fail();
        } catch (RuntimeException e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }
}