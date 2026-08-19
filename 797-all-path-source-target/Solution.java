import java.util.*;
class Solution797 {
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    Deque<Integer> stack = new ArrayDeque<Integer>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        stack.offerLast(0);
        dfs(graph, 0, graph.length-1);
        return ans;
    }
    public void dfs(int[][] graph, int x, int n){
        if (x == n) {
            var toAdd = new ArrayList<Integer>(stack);
            ans.add(toAdd);
            return;
        }
        for (int y : graph[x]) { // graph[x] 的值就是[1, 2]
            stack.offerLast(y);  // 1. 做選擇：把鄰居 y 放進路徑（嘗試走這條路）
            dfs(graph, y, n);    // 2. 遞迴：繼續往下探險
            stack.pollLast();    // 3. 撤銷選擇（回溯）：探險完了，把 y 拿出來，退回上一步
        }
    }
    public static void main(String[] args) {
        int[][] graph = {{1,2},{3},{3},{}};
        Solution797 APST = new Solution797();
        System.out.println("Ans = " + APST.allPathsSourceTarget(graph));
    }
}
/*
官解
graph = [[1,2],[3],[3],[]]
[遞迴樹]
dfs(0, 3)    stack = [0]
│
├─ y=1 ─► stack = [0, 1]
│  └─ dfs(1, 3)
│     └─ y=3 ─► stack = [0, 1, 3]
│        └─ dfs(3, 3)
│           └─ x == 3 ✅ → ans 加入 [0,1,3] → return
│        ◄─ 回溯：pollLast() → stack = [0, 1]
│     ◄─ for 迴圈結束 → return
│  ◄─ 回溯：pollLast() → stack = [0]
│
└─ y=2 ─► stack = [0, 2]
   └─ dfs(2, 3)
      └─ y=3 ─► stack = [0, 2, 3]
         └─ dfs(3, 3)
            └─ x == 3 ✅ → ans 加入 [0,2,3] → return
         ◄─ 回溯：pollLast() → stack = [0, 2]
      ◄─ for 迴圈結束 → return
   ◄─ 回溯：pollLast() → stack = [0]
*/