package bearmaps;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {

    @Test
    public void sanityAddTest() {
        ExtrinsicMinPQ<String> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add("I", 0);
        minPQ.add("love", 0.1);
        minPQ.add("you", 100);
        assertTrue(minPQ.contains("love"));
        assertFalse(minPQ.contains("hate"));
    }

    @Test
    public void sanityRemoveTest() {
        ExtrinsicMinPQ<String> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add("I", 0);
        minPQ.add("love", 0.1);
        minPQ.add("you", 100);
        minPQ.removeSmallest();
        assertTrue(minPQ.contains("love"));
        assertFalse(minPQ.contains("I"));
    }

    @Test
    public void sanityChangePriorityTest() {
        ExtrinsicMinPQ<String> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add("I", 0);
        minPQ.add("love", 0.1);
        minPQ.add("you", 100);
        minPQ.changePriority("I", 1);
        minPQ.removeSmallest();
        assertFalse(minPQ.contains("love"));
        assertTrue(minPQ.contains("I"));
    }

    @Test
    public void sanityOtherTest() {
        ExtrinsicMinPQ<String> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add("I", 0);
        minPQ.add("love", 0.1);
        minPQ.add("you", 100);
        assertEquals("I", minPQ.getSmallest());
        assertEquals(3, minPQ.size());
    }
}
