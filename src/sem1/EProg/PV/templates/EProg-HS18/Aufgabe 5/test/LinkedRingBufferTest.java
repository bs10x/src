import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LinkedRingBufferTest {

    @Test
    public void testConstructorAndCapacity() {
        assertEquals( 2, new LinkedRingBuffer( 2).capacity());
        assertEquals( 3, new LinkedRingBuffer( 3).capacity());
        assertEquals(10, new LinkedRingBuffer(10).capacity());
    }

    @Test
    public void testConstructorAndCapacityOne() {
        // this is something of a special case
        assertEquals(1, new LinkedRingBuffer(1).capacity());
    }

    @Test
    public void testInitialLength() {
        assertEquals(0, new LinkedRingBuffer(1).length());
        assertEquals(0, new LinkedRingBuffer(2).length());
        assertEquals(0, new LinkedRingBuffer(3).length());
    }

    @Test
    public void testLengthEnqueueDequeue() {
        LinkedRingBuffer buffer = new LinkedRingBuffer(10);
        buffer.enqueue(1);
        assertEquals(1, buffer.length());
        buffer.enqueue(2);
        assertEquals(2, buffer.length());
        
        buffer.dequeue();
        assertEquals(1, buffer.length());
        buffer.dequeue();
        assertEquals(0, buffer.length());
    }

    @Test
    public void testEnqueueDequeue() {
        LinkedRingBuffer buffer = new LinkedRingBuffer(10);
        buffer.enqueue(42);
        assertEquals(42, buffer.dequeue());

        buffer.enqueue(43);
        buffer.enqueue(44);
        buffer.enqueue(45);
        assertEquals(43, buffer.dequeue());
        assertEquals(44, buffer.dequeue());
        assertEquals(45, buffer.dequeue());
    }
}