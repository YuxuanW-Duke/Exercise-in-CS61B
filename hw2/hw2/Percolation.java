package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;
import org.junit.Assert.*;

public class Percolation {
    /** N-by-N grid */
    private int[][] grid;
    private int N;
    /** unionfind */
    private WeightedQuickUnionUF uf;

    /** create N-by-N grid, with all sites initially blocked */
    public Percolation(int N) {
        if (N <= 0) {
            throw new RuntimeException("The N you input is invalid.");
        }
        this.grid = new int[N][N];
        this.N = N;
        this.uf = new WeightedQuickUnionUF(N*N + 1);
    }

    /** open the site (row, col) if it is not open already */
    public void open(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("The index is out of range.");
        }
        if (grid[row][col] == 0) {
            grid[row][col] = 1;
            unionNeighbors(row, col);
            update();
        }
    }

    /** update the grid status */
    private void update() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (isFull(row, col)) { grid[row][col] = 2; }
            }
        }
    }

    /** is the site open? */
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("The index is out of range.");
        }
        return (grid[row][col] >= 1);
    }

    /** is the site full? */
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("The index is out of range.");
        }
        if (isOpen(row, col)) {
            for (int i = 0; i < N; i++) {
                if (uf.connected(i, row*N + col)) { return true; }
            }
        }
        return false;
    }

    /** number of open sites */
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(grid[i][j] >= 1) { count++; }
            }
        }
        return count;
    }

    /** helper method: union neighbors */
    private void unionNeighbors(int row, int col) {
        int index = row * N + col;
        // connect left item
        if (col > 0 && grid[row][col - 1] != 0) { uf.union(index - 1, index);}
        // connect right item
        if (col < N - 1 && grid[row][col + 1] != 0) { uf.union(index, index + 1);}
        // connect above item
        if (row > 0 && grid[row - 1][col] != 0) { uf.union(index - N, index);}
        // connect below item
        if (row < N - 1 && grid[row + 1][col] != 0) { uf.union(index, index + N);}
    }

    /** does the system percolate? */
    public boolean percolates() {
        for (int i = 0; i < N; i++) {
            if ( isFull(N - 1, i) ) { return true; }
        }
        return false;
    }

    /** unit test */
    public static void main(String[] args) {

    }
}
