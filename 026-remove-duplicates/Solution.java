import java.util.*;

//Python用列表,java用雙指針
//java有雙指針版本, 以及列表版本
class Solution26 {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        int i=1;
        for(int j=1; j<nums.length; j++){
            //打印j, i的變化
            System.out.println("j = " +j + " i = " +i);
            if (nums[j]!=nums[i-1]){
                nums[i] = nums[j];
                i++;
            }
            //打印nums的變化
            System.out.println(Arrays.toString(nums));
        }
        return i;
    }
    public int removeDuplicatesWithList(int[] nums){
        List<Integer> mainList = new ArrayList<>();
        for (int n :nums){
            mainList.add(n);
        }
        System.out.println(mainList);
        int i = 1;
        while (i < mainList.size()){
            System.out.println("i = " + i);
            System.out.println("List = " + mainList.size());
            if (mainList.get(i).equals(mainList.get(i-1))){
                mainList.remove(i);
            }
            else {
                i++;
            }
        }
        for (int j=0; j< mainList.size(); j++){
            nums[j] = mainList.get(j);
            System.out.println("nums = " + Arrays.toString(nums));
        }
        return mainList.size();
    }
    public static void main(String[] args) {
        System.out.println(new Solution26().removeDuplicates(new int[] {1, 1, 2, 2, 5}));
    //    System.out.println(new Solution26().removeDuplicatesWithList(new int[] {0,0,1,1,1,2,2,3,3,4}));
    }
}