package main

import "fmt"

func twoSum(nums []int, target int) []int {
	m := make(map[int]int, len(nums))
    for index, num := range(nums){
        if _, exist := m[target - num]; exist{
            return []int{m[target-num], index}
        }
        m[num] = index
    }
    return []int {0, 0} //理論上不會走到這
}

func main() {
	nums := []int{2, 7, 11, 15}
	fmt.Println("Ans = ", twoSum(nums, 9))
}

/*
自己寫的
[2, 11, 15, 7]
存成這樣
map[數值: 下標]
[[2, 0], [11, 1], [15, 2], [7,3]]

走到2時, 7有出現過嗎? 還沒

走到11時, -2有出現過嗎? 還沒

走到15時, -6有出現過嗎? 還沒

走到7時, 2有出現過嗎? 有

返回7 & 2 下標
*/