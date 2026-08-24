import java.util.*;

class Solution210 {
    boolean hasCycle = false;
    Deque<Integer> stack = new ArrayDeque<>();
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] color = new int[numCourses];
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            edges.add(new ArrayList<>());
        for (int[] prereq : prerequisites) //prerequisites 轉鄰接表
        {
            int course = prereq[0]; //想修的課
            int pre = prereq[1]; //先修的課
            edges.get(pre).add(course);
        }   
        for (int i = 0; i < numCourses; i++){
            if (color[i] == 0)
                dfs(edges, color, i);
            if (hasCycle) //如果剛的dfs發現hasCycle, 那後面的也都不用看了, 直接return
                return new int[0];
        }
        int n = stack.size();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) //stack轉成陣列後再return
            result[i] = stack.pollLast();
        return result;
    }
    void dfs(List<List<Integer>> edge, int[] color, int cur){
        if (color[cur] == 1) //走到訪問中的節點, 發現環
        {
            hasCycle = true;
            return;
        }
        if (color[cur] == 2) //走到已訪問的節點, 直接返回
            return;

        color[cur] = 1; //沒被訪問過, 標記為訪問中
        for(int next : edge.get(cur)){
            dfs(edge, color, next);
            if (hasCycle)
                return;
        }
        color[cur] = 2;
        stack.offerLast(cur);
    }
    public static void main(String[] args) {
        Solution210 sol = new Solution210();
//        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        int[][] prerequisites = {{1,0},{0,1}};
        System.out.println("Ans = " + Arrays.toString(sol.findOrder(4, prerequisites)));
    }
}