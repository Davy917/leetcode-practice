package main

import "fmt"

type TwoSum struct {
	nums map[int]int
}

func Constructor() TwoSum {
	return TwoSum{nums: make(map[int]int)}
}

func (this *TwoSum) Add(number int) {
	this.nums[number] += 1
}

func (this *TwoSum) Find(value int) bool {
	for num, _ := range this.nums {
		this.nums[num]--
		target := value - num
		if count, exist := this.nums[target]; exist && count > 0 {
			this.nums[num]++ //容易被忽略, 不寫提交會出錯
			return true
		}
		this.nums[num]++
	}
	return false
}

func main() {
	ts := Constructor()
	ts.Add(0)
	fmt.Println(ts.Find(0))
}

/*
自己寫的

首先產生一個疑問, 這兩題有何不同?
001-two-sum\solution.go

001 要一邊查找一邊加入map
170 這題則是要把加入跟查找分開
在跑Add(0), Find(0)這組測資時會產生很大的區別

所以這題思路要重新想
設計哈希映射 => [num, 出現次數]

情境:
假設題目要找兩數之和為0但是Map如下, 預期return false
Map[[0, 1]] => [num, 出現次數]

先把 Map[[0, 1]] 變成 Map[[0, 0]] 代表現在這個0已經列入考慮了, 如果沒有另一個 0 那就return false
等判定結束再把0的出現次數加回去, 第22行那個跟第25行都要加 this.nums[num]++, 缺一不可
*/
