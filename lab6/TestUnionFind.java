import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {

    UnionFind uf = new UnionFind(6);

    @Test
    public void testBasic(){
        uf.union(0,1);
        uf.union(4,5);
        uf.union(2,3);

        assertEquals(uf.find(0),uf.find(1));
        assertEquals(uf.find(4),uf.find(5));
        assertTrue(uf.connected(0,1));
        assertFalse(uf.connected(1,2));
        assertEquals(2,uf.sizeOf(0));
    }

}
