/*
自己完成
*/
package main
import "fmt"
func maximumUniqueSubarray(nums []int) int {
	numsMap := make(map[int]int)
	score, maxScore := 0, 0
    l, r := 0, 0
	for r < len(nums){
		numsMap[nums[r]]++
		score += nums[r]
		for numsMap[nums[r]] > 1{
			numsMap[nums[l]]--
			score -= nums[l]
			l++
		}
		maxScore = max(maxScore, score)
		r++
	}
	return  maxScore
}
func main(){
	nums := []int {5,2,1,2,5,2,1,2,5}
	fmt.Println("Ans = ", maximumUniqueSubarray(nums))
}
/*
執行代碼:
go run ./1695-maximum-unique-subarray
*/