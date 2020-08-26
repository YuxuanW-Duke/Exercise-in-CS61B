public class BubbleGrid {
    private int[][] grid;
    private UnionFind uf;
    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        this.uf = new UnionFind(grid.length * grid[0].length);
    }

    private void connecting(int i, int j) {
        int index = i * grid[0].length + j;
        // connect left item
        if (j > 0 && grid[i][j - 1] == 1) { uf.union(index - 1, index);}
        // connect right item
        if (j < grid[0].length - 1 && grid[i][j + 1] == 1) { uf.union(index, index + 1);}
        // connect above item
        if (i > 0 && grid[i - 1][j] == 1) { uf.union(index - grid[0].length, index);}
        // connect below item
        if (i < grid.length - 1 && grid[i + 1][j] == 1) { uf.union(index, index + grid[0].length);}
    }

    private void unionNeighbor() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    connecting(i, j);
                }
            }
        }
    }

    private int dropCalc(UnionFind uf) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int root = uf.find(i*grid[0].length + j);
                if ((grid[i][j] == 1) && (root < 0 || root >= grid[0].length)) {
                    count++;
                }
            }
        }
        return count;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        int[] dropNum = new int[darts.length];
        for (int i = 0; i < darts.length; i++) {
            grid[darts[i][0]][darts[i][1]] = 0;
            /* Everytime the dart shot a bubble, change the id of this position in uf to
            its index. */
            uf.reset(darts[i][0] * grid[0].length + darts[i][1]);
            unionNeighbor();
            dropNum[i] = dropCalc(uf);
        }
        return dropNum;
    }
}
