/*
自己寫的看python, go
官方解答看java
*/
package main

import "fmt"

type Pair struct {
	key   int
	value int
}
type MyHashMap struct {
	pointer *Pair
	buckets [][]*Pair
}

func Constructor() *MyHashMap {
	return &MyHashMap{buckets: make([][]*Pair, 5)}
}

func (this *MyHashMap) Put(key int, value int) {
	index := key % 5
	if this.Get(key) != -1 {
		this.pointer.value = value
	} else {
		newPair := Pair{
			key:   key,
			value: value,
		}
		this.buckets[index] = append(this.buckets[index], &newPair)
	}
}

func (this *MyHashMap) Get(key int) int {
	index := key % 5
	for _, pair := range this.buckets[index] {
		if pair.key == key {
			this.pointer = pair
			return pair.value
		}
	}
	return -1
}

func (this *MyHashMap) Remove(key int) {
	if this.Get(key) == -1 {
		return
	}
	this.pointer.key = -1
}

func main() {
	MyHashMap := Constructor()
	MyHashMap.Put(1, 10)
	MyHashMap.Put(2, 20)
	MyHashMap.Put(7, 77)
	MyHashMap.Put(3, 30)
	MyHashMap.Put(1, 11)
	MyHashMap.Remove(2)
	fmt.Println(MyHashMap.Get(1))
	fmt.Println(MyHashMap.Get(2))
	fmt.Println(MyHashMap)
}

/*
值得補充的是Remove(key int), 並不是真的把那組鍵值對拔掉, 而是用key = -1, 改變鍵值對的長相而已
*/
