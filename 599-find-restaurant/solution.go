package main

import (
	"fmt"
	"math"
)

func findRestaurant(list1 []string, list2 []string) (result []string) {
	//特殊情境, 直接返回
	if list1[0] == list2[0] {
		result = append(result, list1[0])
		return
	}
	//找出哪一個list比較長
	var long, short []string
	var m map[string]int
	if len(list1) > len(list2) {
		m = make(map[string]int, len(list1))
		long, short = list1, list2
	} else {
		m = make(map[string]int, len(list2))
		long, short = list2, list1
	}
	minSumIndex := math.MaxInt
	index := 0
	for index < len(long) {

		if index < len(short) {
			str := short[index]
			_, exist := m[str]
			if exist {
				m[str] = index - m[str]
				if m[str] < minSumIndex {
					result = []string{}
					minSumIndex = m[str]
					result = append(result, str)
				} else if m[str] == minSumIndex {
					result = append(result, str)
				}
			} else {
				m[str] = -index
			}
		}

		str := long[index]
		_, exist := m[str]
		if exist {
			m[str] = index - m[str]
			if m[str] < minSumIndex {
				result = []string{}
				minSumIndex = m[str]
				result = append(result, str)
			} else if m[str] == minSumIndex {
				result = append(result, str)
			}
		} else {
			m[str] = -index
		}
		index++
	}
	return result
}
func main() {
	list1 := []string{"Shogun", "Tapioca Express", "Burger King", "KFC"}
	list2 := []string{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"}
	fmt.Println("Ans = ", findRestaurant(list1, list2))
}

/*
自己寫的
兩個索引一定要被遍歷過
能否一起遍歷? 比較快
可以
把較長的那個list走完就結束了

長的list叫做long
短的list叫做short

代碼結構:
for 遍歷 long
	同時遍歷short
		現在這個short[index]字串有在Map中嗎?
			有
				比 minSumIndex 小
					更新minSumIndex, result
				跟 minSumIndex一樣
					加進去result
			沒有
				加進去Map中
	現在這個long[index]字串有在Map中嗎?
		有
			比 minSumIndex 小
				更新minSumIndex, result
			跟 minSumIndex一樣
				加進去result
		沒有
			加進去Map中

觀察:
long, short 每一次迴圈都跑相同的檢查邏輯, 直到long遍歷完

講解result:
result只放最小的下標總和的str, 用minSumIndex記錄當前最小的下標總和
str > minSumIndex, 不理會
str < minSumIndex, 找到更小的 ---> result 清空 ---> 加入 str
str == minSumIndex, result 加入 str

講解Map:
Map安排, Map[string, index]
題目已經限制了一個字串最多就出現兩次
出現一次的不可能是答案, 出現兩次才被考慮

用同一個Map來存str, 第一次存故意把index 存成負的 -> Map[str, -index]
如果出現第二次再把這個 index 導正, 可以很輕鬆的用這兩行來篩選出答案候選人

代碼:
str第二次出現, 把 index導正
m[str] = 正的index - 之前存的負index

if exist{
	m[str] = index - m[str]
*/
