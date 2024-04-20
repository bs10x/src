import static java.util.Arrays.asList;
import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class LinkedRingBufferBGradingTest {

    @Test
    public void testEnqueueDequeue() {
        for (int cap : asList(1, 2, 5, 10)) {
            for (int elems = 1; elems <= cap; elems++) {
                LinkedRingBuffer buffer = new LinkedRingBuffer(cap);
                for (int i = 0; i < elems; i++) {
                    buffer.enqueue(42 + i);
                }
                for (int i = 0; i < elems; i++) {
                    assertEquals(42 + i, buffer.dequeue());
                }
            }
        }
    }

    @Test
    public void testEnqueueDequeueHighCap() {
        int cap = 768;
        for (int elems = 1; elems <= cap; elems++) {
            LinkedRingBuffer buffer = new LinkedRingBuffer(cap);
            for (int i = 0; i < elems; i++) {
                buffer.enqueue(42 + i);
            }
            for (int i = 0; i < elems; i++) {
                assertEquals(42 + i, buffer.dequeue());
            }
        }
    }

    @Test
    public void testEnqueueException() {
        for (int cap : asList(1, 2, 5, 10, 100)) {
            LinkedRingBuffer buffer = new LinkedRingBuffer(cap);
            for (int i = 0; i < cap; i++) {
                buffer.enqueue(44);
            }
            // dequeue as well, just in case...
            buffer.dequeue();
            buffer.enqueue(44);
            try {
                buffer.enqueue(44);
                fail("missing exception");
            } catch (RuntimeException e) {
                assertTrue(e instanceof IllegalStateException);
            }
        }
    }

    @Test
    public void testDequeueException() {
        for (int cap : asList(1, 2, 5, 10, 100)) {
            for (int elems = 0; elems <= cap; elems = elems * 2 + 1) {
                LinkedRingBuffer buffer = new LinkedRingBuffer(cap);
                for (int i = 0; i < elems; i++) {
                    buffer.enqueue(42 + i);
                }
                for (int i = 0; i < elems; i++) {
                    buffer.dequeue();
                }
                try {
                    buffer.dequeue();
                    fail("missing exception");
                } catch (RuntimeException e) {
                    assertTrue(e instanceof IllegalStateException);
                }
            }
        }
    }

    @Test
    public void testLengthEnqueue() {
        LinkedRingBufferAGradingTest.assertNoIllegalModifications();
        for (int cap : asList(1, 2, 5, 10, 100)) {
            for (int elems = 1; elems <= cap; elems *= 2) {
                LinkedRingBuffer buffer = new LinkedRingBuffer(cap);
                assertEquals(0, buffer.length());
                for (int i = 0; i < elems; i++) {
                    buffer.enqueue(42);
                    assertEquals(i + 1, buffer.length());
                }
            }
        }
    }

    @Test
    public void testLengthDequeue() {
        LinkedRingBufferAGradingTest.assertNoIllegalModifications();
        for (int cap : asList(1, 2, 5, 10, 100)) {
            for (int elems = 1; elems <= cap; elems *= 2) {
                LinkedRingBuffer buffer = new LinkedRingBuffer(cap);
                for (int i = 0; i < elems; i++) {
                    buffer.enqueue(42);
                }
    
                assertEquals(elems, buffer.length());
                for (int i = elems - 1; i >= 0; i--) {
                    buffer.dequeue();
                    assertEquals(i, buffer.length());
                }
            }
        }
    }

    @Test
    public void testEnqueueDequeueLengthStress() {
        LinkedRingBufferAGradingTest.assertNoIllegalModifications();
        Random random = new Random(24);
        for (int cap : asList(1, 2, 5, 10, 100)) {
            LinkedRingBuffer buffer = new LinkedRingBuffer(cap);
            int length = 0;
            for (int i = 0; i < 10000; i++) {
                if (random.nextBoolean() && length < cap || length == 0) {
                    buffer.enqueue(i);
                    length++;
                } else {
                    buffer.dequeue();
                    length--;
                }
                assertEquals(length, buffer.length());
            }
        }
    }
}