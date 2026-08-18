package UnionFind;

public class QuickFind {
    int[] parent;

    public QuickFind(int size){
        parent = new int[size];
        for(int i = 0; i < size; i++)
            parent[i] = i;
    }

    int find(int x){
        return parent[x];
    }
    void union(int x, int y){
        int parentX = find(x);
        int parentY = find(y);
        if (parentX != parentY)
            for (int i = 0; i < parent.length; i++)
                if (parent[i] == parentY)
                    parent[i] = parentX;
    }

    boolean connected(int x, int y){
        return find(x) == find(y);
    }
}
