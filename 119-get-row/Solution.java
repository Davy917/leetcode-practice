import java.util.*;

class Solution119 {
    public List<Integer> P0119_getRow(int rowIndex) {

        List<List<Integer>> mainList = new ArrayList<>();
        mainList.add(new ArrayList<>(Arrays.asList(1)));

        if (rowIndex == 0){
            return mainList.get(0);
        }

        for (int i=1; i<=rowIndex; i++){
            List<Integer> subList = new ArrayList<>(Arrays.asList(1));

            for (int j=1; j<i; j++){
                subList.add(mainList.get(i-1).get(j-1) + mainList.get(i-1).get(j));
            }

            subList.add(1);
            mainList.add(subList);
        }
        return mainList.get(rowIndex);
    }
    static void main(String[] args) {
        Solution119 sol = new Solution119();
        System.out.println(sol.P0119_getRow(0));
    }
}