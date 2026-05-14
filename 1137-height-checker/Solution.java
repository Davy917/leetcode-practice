import java.util.Arrays;

class Solution1137 {
    public static int heightChecker(int[] heights) {
        int[] buckets = new int[101];
        for (int height : heights)
            buckets[height]++;
        System.out.println(Arrays.toString(buckets));

        int result = 0;
        for (int bucketsIndex = 1, heightsIndex = 0; bucketsIndex < buckets.length; bucketsIndex++)
            while (buckets[bucketsIndex]-- > 0)
                if (heights[heightsIndex++] != bucketsIndex)
                    result++;

        return result;
    }
    static void main(String[] args) {
        int[] students = {1, 1, 4, 2, 1, 3};
        System.out.println(heightChecker(students));
    }
}

/*
++ / -- 的基本概念
Java 裡這兩個是遞增 / 遞減運算子：
x++：先用舊值，再 x = x + 1
++x：先 x = x + 1，再用新值
x--：先用舊值，再 x = x - 1
--x：先 x = x - 1，再用新值

while (buckets[bucketsIndex]-- > 0)

意思是：
    先拿目前 bucket 數量來判斷是否大於 0，判斷完後再把它減 1。

if (heights[heightsIndex++] != bucketsIndex)
意思是：
    先用目前 heightsIndex 位置的元素來比較，比完後再把 heightsIndex 加 1。
 */