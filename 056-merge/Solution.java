import java.util.*;

class Solution56 {
    public int[][] P0056_merge(int[][] intervals) {
        if (intervals.length == 0) {
            return intervals;
        }

        Comparator<int[]> comp = new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        };
        Arrays.sort(intervals, comp);

        // ✅ Lambda 簡化版
        //Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();

        for (int[] i : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < i[0]) {
                int[] toAdd = new int[]{i[0], i[1]};
                merged.add(toAdd);
                //System.out.println(Arrays.deepToString(merged.toArray(new int[merged.size()][])));
            }
            else {
                merged.get(merged.size() - 1)[1] =
                        Math.max(merged.get(merged.size() - 1)[1], i[1]);

                //System.out.println(Arrays.deepToString(merged.toArray(new int[merged.size()][])));
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
    public int[][] mergeLinkedList(int[][] intervals) {
        if (intervals.length == 0) {
            return intervals;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });

        List<int[]> merged = new LinkedList<>();

        for (int[] i : intervals) {
            if (merged.isEmpty() || merged.getLast()[1] < i[0]) {
                int[] toAdd = new int[]{i[0], i[1]};
                merged.add(toAdd);
                //System.out.println(Arrays.deepToString(merged.toArray(new int[merged.size()][])));
            }

            else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], i[1]);
                //System.out.println(Arrays.deepToString(merged.toArray(new int[merged.size()][])));
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {

        Solution56 sol = new Solution56();
        int [][] intervals = {{1,3}, {2,6}, {8,10}, {15,18}};

        //方法一, 用ArrayList實現
        System.out.println(Arrays.deepToString(sol.P0056_merge(intervals)));
        //方法二, 用LinkedList實現
        System.out.println(Arrays.deepToString(sol.mergeLinkedList(intervals)));
    }
}
/*
List<int[]> merged = new ArrayList<>();

for (int[] interval : intervals) {
    if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
        merged.add(new int[]{interval[0], interval[1]});
    } else {
        int[] last = merged.get(merged.size() - 1);
        last[1] = Math.max(last[1], interval[1]);
    }
}

return merged.toArray(new int[merged.size()][]);
 */