/*
插入排序法看這版, 堆排序看solution2.go
插入排序會超時, 真正能通過的是堆排序
*/
package main

import (
	"fmt"
	//"math/rand"
)

func findKthLargest(nums []int, k int) int {
	fmt.Println("init arr = ", nums)

	n := len(nums)
	left , right, target := 0, n-1, n-k //第k大元素的下標是len - k
	return quickselect(nums, left, right, target)

	/*
	堆排序實現
	return findKth(nums, k)
	*/
}

func quickselect(nums []int, left int, right int, target int) int {
	if left >= right{
		//基底：區間只剩一個元素，就是答案
		return nums[left]
	}
	pivotIndex := partition_v2(nums, left, right)
	if pivotIndex == target{
		return nums[pivotIndex]
	}else if pivotIndex < target{
		return quickselect(nums, pivotIndex+1, right, target)
	} else {
		return quickselect(nums, left, pivotIndex-1, target)
	}
}

func partition(nums []int, start int, end int) int {
	left, right, pivot := start, end, nums[start]
	for left < right{
		for left < right && nums[right] >= pivot{
			right--
		}
		for left < right && nums[left] <= pivot{
			left++
		}
		nums[left], nums[right] = nums[right], nums[left]
	}
	nums[start], nums[right] = nums[right], nums[start]
	fmt.Printf("nums = %v, right = %d\n", nums, right)
	return right
}

func partition_v2(nums []int, left int, right int) int {
	// randomIndex := left + rand.Intn(right-left+1)
	// nums[left], nums[randomIndex] = nums[randomIndex], nums[left]
	// fmt.Printf("randomIndex = %d, nums = %v\n", randomIndex, nums)

	pivot, le := nums[left], left
	for i := left + 1; i <= right; i++ {
		fmt.Printf("i = %d, le = %d\n", i, le)
		if nums[i] <= pivot {
			le++
			fmt.Printf("nums[i] <= pivot, i = %d, le = %d\n", i, le)
			nums[le], nums[i] = nums[i], nums[le]
			fmt.Println(nums)
		}
	}
	nums[left], nums[le] = nums[le], nums[left]
	fmt.Println(nums)
	return le
}

func main() {
	nums := []int{2, 7, 6, 5, 3, 4, 1}
	/*
	單獨測試partition
	fmt.Println(partition(nums, 0, len(nums)-1))
	fmt.Println(partition_v2(nums, 0, len(nums)-1))
	*/
	k := 4
	fmt.Println("Ans = ", findKthLargest(nums, k))
}

/*
執行命令
go run ./215-find-kth-largest

partition是參考底下這版本的哨兵劃分
algo/QuickSort/quicksort_advance.go

partition_v2是官方解法
官方解法:
https://leetcode.cn/leetbook/read/sliding-window-and-two-pointers/rli5s3/

我们定义 pivot = nums[left] ，剩下的区间 [left + 1..right] 被变量 le 分成三个部分：
	[left + 1 .. le] <= pivot；
	(le..i] > pivot；
	(i..right] 是程序没有看到的部分。
*/
