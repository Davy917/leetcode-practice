package main

type MovingAverage struct {
	sum     int
	maxSize int
	queue   []int
}

func Constructor(size int) MovingAverage {
	var dq = MovingAverage{0, size, []int{}}
	return dq
}

func (this *MovingAverage) Next(val int) float64 {
	this.sum += val
	if len(this.queue) != this.maxSize {
		this.queue = append(this.queue, val)
		return float64(this.sum) / float64(len(this.queue))
	}
	leave := this.queue[0]
	this.sum -= leave
	this.queue = this.queue[1:]
	this.queue = append(this.queue, val)
	return float64(this.sum) / float64(len(this.queue))
}
func main() {
	movingAverage := Constructor(3)
	movingAverage.Next(1)
	movingAverage.Next(10)
	movingAverage.Next(3)
	movingAverage.Next(5)
}

/*
Q1:
用了大量的this, 要如何讓它更簡化
Go 的命名約定：

A:
接收者名稱用 1-2 字母：m, ma, r 等
避免用 this 或 self（這是其他語言的習慣）
這樣代碼看起來更簡潔，也符合 Go 的風格。

Q2:
MovingAverage 的第三個參數是queue   []int, 我們在它實例化的時候一定要寫[]int{} 嗎

A:
不一定非要。你有几种选择：
1. 不初始化（零值是 `nil`)
func Constructor(size int) MovingAverage {
	return MovingAverage{0, size, nil}
}
这也能工作，`nil` 切片和空切片在 `append` 时表现一样。

2. 空切片
return MovingAverage{0, size, []int{}}
长度为 0，容量为 0。

3. 预分配容量（推荐）
return MovingAverage{0, size, make([]int, 0, size)}
这样更高效，因为你知道最多会存 `size` 个元素，预先分配避免多次扩容。

`[]int{}` 可以不写，但也没错
如果知道最大容量，用 `make([]int, 0, size)` 性能更好
用哪种方式都能工作，只是效率不同
*/
