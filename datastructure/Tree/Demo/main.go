package main

import test "gopractice/datastructure/Tree"

func main() {
	// 使用 []any，寫法與 Java 的 Integer[] 完全一致！
	//testCase := []any{1, 2, 4, nil, nil, nil, 3, nil, 5, nil, nil}
	//test.BuildPreorderTree(testCase)
	//fmt.Println("\n構建完成！")

	testCase := []any{5, 4, 8, 11, nil, 13, 4, 7, 2, nil, nil, nil, 1}
	test.BuildLevelOrderTree(testCase)
}
