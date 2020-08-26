public class UnionFind {

    private int[] id;
    private int[] weight;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        id = new int[n];
        weight = new int[n];
        for (int i = 0; i < n; i++){
            id[i] = i;
//            weight[i] = 1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if(vertex < 0 || vertex >= id.length) {
            throw new RuntimeException("It is not a valid index.");
        }
    }

    /* Returns the size of the set v1 belongs to. */
//    public int sizeOf(int v1) {
//        validate(v1);
//        if(id[v1] == v1) { return weight[v1]; }
//        return sizeOf(parent(v1));
//    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        if(id[v1] == v1) { return -1; }
        return id[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a
       vertex with itself or vertices that are already connected should not
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
//        if (find(v1) == find(v2)) {
//            throw new RuntimeException("These two vertices is already connected.");
//        }
        id[find(v2)] = id[find(v1)];
//        if (sizeOf(v1) >= sizeOf(v2)) {
//            id[find(v2)] = id[find(v1)];
//            weight[find(v1)] += weight[find(v2)];
//        } else {
//            id[find(v1)] = id[find(v2)];
//            weight[find(v2)] += weight[find(v1)];
//        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        if(id[vertex] == vertex) { return vertex; }
        return find(parent(vertex));
    }

    public void reset(int vertex) {
        id[vertex] = vertex;
    }

}
