package main

import test "gopractice/datastructure/Tree"

func main() {
	// 使用 []any，寫法與 Java 的 Integer[] 完全一致！
	//testCase := []any{1, 2, 4, nil, nil, nil, 3, nil, 5, nil, nil}
	//test.BuildPreorderTree(testCase)
	//fmt.Println("\n構建完成！")

	testCase := []any{3, 9, 20, nil, nil, 15, 7}
	test.BuildLevelOrderTree(testCase)
}
