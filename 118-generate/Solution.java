import java.util.*;
class Solution118 {
    public List<List<Integer>> P0118_generate(int numRows) {

        List<List<Integer>> mainList = new ArrayList<>();
        mainList.add(new ArrayList<>(Arrays.asList(1)));
        //System.out.println("Layer1: " + mainList);

        for (int i=1; i<numRows; i++){
            List<Integer> subList = new ArrayList<>(Arrays.asList(1));
            for (int j=1; j<i; j++){
                subList.add(mainList.get(i-1).get(j-1) + mainList.get(i-1).get(j));
            }
            subList.add(1);
            mainList.add(subList);
            //System.out.println("Layer" + (i + 1) + ": " + mainList);
        }
        return mainList;
    }
    static void main(String[] args) {
        Solution118 sol = new Solution118();
        System.out.println(sol.P0118_generate(3));
    }
}