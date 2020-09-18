package bearmaps;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class KDTreeTest {

    @Test
    public void sanityNearestTest() {
        List<Point> pointList = new LinkedList<>();
        pointList.add(new Point(1,2));
        pointList.add(new Point(-1,2));
        pointList.add(new Point(-1,-2));
        pointList.add(new Point(1,-2));
        PointSet pointSet = new KDTree(pointList);
        Point nearestPoint = pointSet.nearest(3,3);
        assertEquals(pointSet.nearest(3,3).getX(), 1,0);
        assertEquals(pointSet.nearest(3,3).getY(),2,0);
    }

}
