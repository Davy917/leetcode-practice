import java.util.*;

class Pair implements Comparable<Pair> {
    int end;
    int dist;

    Pair(int end, int dist) {
        this.end = end;
        this.dist = dist;
    }

    @Override
    public int compareTo(Pair other) {
        return Integer.compare(this.dist, other.dist);
    }
}

class Solution743 {
    private HashMap<Integer, List<Pair>> graph;
    private boolean[] visited;
    private int maxVal;
    public int networkDelayTime(int[][] times, int n, int k) {
        this.graph = new HashMap<>();
        this.visited = new boolean[n + 1];
        this.maxVal = -1;
        for (int[] time : times){
            Pair newPair = new Pair(time[1], time[2]);
            graph.putIfAbsent(time[0], new ArrayList<>());
            graph.get(time[0]).add(newPair);
        }
//        System.out.println("graph = " + graph);
        maxVal = dijkstra(k);
        for (int i = 1; i < visited.length; i++)
            if (!visited[i])
                return -1;
        return maxVal;
    }
    private int dijkstra(int k) {
        var heap = new PriorityQueue<Pair>();
        heap.offer(new Pair(k, 0));
        int layerSize = 0;
        while (!heap.isEmpty()){
            Pair cur = heap.poll();
            if (visited[cur.end])
                continue;

            visited[cur.end] = true;
            maxVal = Math.max(maxVal, cur.dist);

            if (graph.get(cur.end) != null)
                layerSize = graph.get(cur.end).size();
            else continue;
            for (int i = 0; i < layerSize; i++) {
                Pair newPair = new Pair(
                        graph.get(cur.end).get(i).end, graph.get(cur.end).get(i).dist + cur.dist);
                heap.offer(newPair);
            }
        }
        return maxVal;
    }
    static void main(String[] args) {
        int[][] times = {{1, 2, 1}, {1, 3, 2}, {2, 4, 5}, {3, 5, 3}};
        Solution743 sol = new Solution743();
        System.out.println("Ans = " + sol.networkDelayTime(times, 5, 1));
    }
}