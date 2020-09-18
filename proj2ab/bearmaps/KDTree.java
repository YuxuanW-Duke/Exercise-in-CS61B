package bearmaps;

import bearmaps.Point;
import java.util.List;
import java.util.TreeSet;

public class KDTree implements PointSet {

    private Node root;

    private class Node {
        private Point point;
        private Node left;
        private Node right;
        private boolean divLR;

        public Node() {
            divLR = true;
        }

        public Node(Point point, boolean divLR) {
            this.point = point;
            this.divLR = divLR;
        }

        public Point getPoint() { return point;}
        public Node getLeft() { return left;}
        public Node getRight() { return right;}
        public boolean isDivLR() { return divLR;}

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    public KDTree(List<Point> pointList) {
        for (Point p:pointList) {
            addPoint(p);
        }
    }

    public void addPoint(Point p) {
        root = addPoint(root, p, true);
    }

    private Node addPoint(Node node, Point p, boolean divLR) {
        if (node == null) {
            node = new Node(p, divLR);
            return node;
        }
        if (node.isDivLR()) {
            if (p.getX() < node.getPoint().getX()) {
                node.setLeft(addPoint(node.getLeft(), p, !divLR));
            } else {
                node.setRight(addPoint(node.getRight(), p, !divLR));
            }
        } else {
            if (p.getY() < node.getPoint().getY()) {
                node.setLeft(addPoint(node.getLeft(), p, !divLR));
            } else {
                node.setRight(addPoint(node.getRight(), p, !divLR));
            }
        }
        return node;
    }


    @Override
    public Point nearest(double x, double y) {
        return nearest(root, x, y, null);
    }

    private Point nearest(Node node, double x, double y, Point best) {
        if (node == null) { return best; }
        double bestDistance;
        double bestDistance1;
        Point best1;
        if (best == null) {
            best = node.getPoint();
            bestDistance = Point.distance(best.getX(), x, best.getY(), y);
        } else {
            bestDistance = Point.distance(best.getX(), x, best.getY(), y);
            // Process the "node" Node
            best1 = node.getPoint();
            bestDistance1 = Point.distance(best1.getX(), x, best1.getY(), y);
            best = bestDistance1 < bestDistance ? best1:best;
        }

        // Process the "node.closerChild" Node
        boolean ifLeftUnder = getCloserSide(node, x, y);
        if (ifLeftUnder) {
            best1 = nearest(node.getLeft(), x, y, best);
        } else {
            best1 = nearest(node.getRight(), x, y, best);
        }
        bestDistance1 = Point.distance(best1.getX(), x, best1.getY(), y);
        best = bestDistance1 < bestDistance ? best1:best;
        bestDistance = Math.max(bestDistance, bestDistance1);

        // Process the "node.furtherChild" Node
        if (worthAnother(node, x, y, bestDistance)) {
            if (!ifLeftUnder) {
                best1 = nearest(node.getLeft(), x, y, best);
            } else {
                best1 = nearest(node.getRight(), x, y, best);
            }
            bestDistance1 = Point.distance(best1.getX(), x, best1.getY(), y);
            best = bestDistance1 < bestDistance ? best1:best;
        }

        return best;
    }

    private boolean getCloserSide(Node node, double x, double y) {
        if (node.isDivLR()) {
            return x < node.getPoint().getX();
        } else {
            return y < node.getPoint().getY();
        }
    }

    private boolean worthAnother(Node node, double x, double y, double bestDistance) {
        if (node.isDivLR()) {
            return Math.abs(x - node.getPoint().getX()) < bestDistance;
        } else {
            return Math.abs(y - node.getPoint().getY()) < bestDistance;
        }
    }

}
