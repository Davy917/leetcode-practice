import java.util.*;

class Solution506 {
    public String[] findRelativeRanks(int[] score) {
        int len = score.length;
        List<Integer> originalScore = new ArrayList<>();
        for (int i : score) {
            originalScore.add(i);
        }
        for (int delta = len/2; delta > 0 ; delta /= 2){
            System.out.println("delta = " + delta);//debug
            for (int start = 0; start < delta; start++) {
                System.out.println("start = " + start);//debug
                for (int i = start + delta; i < len; i += delta) {
                    int j = i;
                    int temp = score[i];
                    System.out.println("i = " + i);//debug
                    while (j - delta >= 0 && score[j - delta] < temp){
                        score[j] = score[j - delta];
                        j -= delta;
                    }
                    score[j] = temp;
                }
            }
            System.out.println(Arrays.toString(score));//debug
        }
        System.out.println("End of sort");
        Map<Integer, String> map = new HashMap<>();
        int rank = 0;
        for (int i : score) {
            rank++;
            if (rank == 1){
                map.put(i, "Gold Medal");
            } else if (rank == 2) {
                map.put(i, "Silver Medal");
            } else if (rank == 3) {
                map.put(i, "Bronze Medal");
            }
            else
                map.put(i, String.valueOf(rank));
        }
        String[] result = new String[len];
        int j = 0;
        for (int i : originalScore){
            result[j++] = map.get(i);
        }
        System.out.println(originalScore);
        System.out.println(Arrays.toString(result));
        return result;
    }
    /*
    {10, 3, 8, 9, 4, 6, 7, 5}
    [10, 9, 8, 7, 6, 5, 4, 3]
    输出：["Gold Medal","5","Bronze Medal","Silver Medal","4"]
     */
    static void main(String[] args) {
        int[] score = {10, 3, 8, 9, 4, 6, 7, 5};
        Solution506 sol = new Solution506();
        String[] result = sol.findRelativeRanks(score);
        System.out.println(Arrays.toString(result));
    }
}