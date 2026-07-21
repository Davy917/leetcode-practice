package Tree

func BuildLevelOrderTree_v2(levelOrder []any) *TreeNode {
	if levelOrder == nil || len(levelOrder) == 0 || levelOrder[0] == nil {
		return nil
	}

	root := &TreeNode{Val: levelOrder[0]}
	queue := []*TreeNode{root}
	index := 1

	for len(queue) > 0 && index < len(levelOrder) {
		curNode := queue[0]
		queue = queue[1:]

		// 建左子樹
		if index < len(levelOrder) { //不寫其實也不會出錯, 因為12行就判定了, 但寫了邏輯會比較完整
			if levelOrder[index] != nil {
				curNode.Left = &TreeNode{Val: levelOrder[index]}
				queue = append(queue, curNode.Left)
			}
			index++
		}

		// 建右子樹
		if index < len(levelOrder) {
			if levelOrder[index] != nil {
				curNode.Right = &TreeNode{Val: levelOrder[index]}
				queue = append(queue, curNode.Right)
			}
			index++
		}
	}
	return root
}

/*
核心差異：

舊版：用完全二元樹索引法 parentIndex = (i-1)/2，假設每個位置都代表樹上的某個點
新版：用 queue 依序處理，對每個非 nil 節點展開左右子。遇到 nil 只跳過，不為它擴展子節點

不加 17, 26行的判斷式, 在什麼情境下會出錯?

情景 1：陣列長度是奇數
levelOrder := []any{1, 2, 3}
    1
   / \
  2   3
不加第 17 行判斷：
	處理 node 1 時
	index=1 時，左子樹建立成 2，index++ 變 2
	index=2 時，準備建右子樹，沒判斷直接訪問 levelOrder[2]，得到 3（還好）
	但若陣列只有 [1, 2]，那 index=2 已經 >= len(levelOrder)，訪問會 index out of range


第 17、26 行的 if index < len(levelOrder) 的作用：
	確保 index 不會超出陣列範圍
	防止訪問不存在的元素（crash）
	讓算法正確處理「不完全二元樹」的情況

沒加的話，一旦遇到「陣列比預期短」或「層次不規則」，就會 runtime panic: index out of range。
*/
