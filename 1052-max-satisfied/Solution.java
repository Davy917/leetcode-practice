class Solution1052 {
    public static int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int basicSatisfied = 0;
        int i =0;
        for (int val : customers){
            if (grumpy[i] == 0)
                basicSatisfied += val;
            i++;
        }
        int addSatisfied = 0;
        for (int j = 0; j < minutes; j++)
            if (grumpy[j] == 1)
                addSatisfied += customers[j];
        int maxAddSatisifed = addSatisfied;

        int l =0;
        for (int r = minutes; r < customers.length; r++) {
            if (grumpy[l] == 1)
                addSatisfied -= customers[l];
            if (grumpy[r] == 1)
                addSatisfied += customers[r];
            System.out.println(addSatisfied);
            maxAddSatisifed = Math.max(maxAddSatisifed, addSatisfied);
            l++;
        }
        return basicSatisfied + maxAddSatisifed;
    }
    static void main(String[] args) {
        int[] customers = {1,0,1,2,1,1,7,5};
        int[] grumpy =    {0,1,0,1,0,1,0,1};
        System.out.println("Ans = " + maxSatisfied(customers, grumpy, 3));
    }
}