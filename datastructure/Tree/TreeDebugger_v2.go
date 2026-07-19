package Tree

func BuildLevelOrderTree_v2(levelOrder []any) *TreeNode {
    if levelOrder == nil || len(levelOrder) == 0 {
        return nil
    }
    if levelOrder[0] == nil {
        return nil
    }

    root := &TreeNode{Val: levelOrder[0]}
    queue := []*TreeNode{root}
    index := 1

    for len(queue) > 0 && index < len(levelOrder) {
        node := queue[0]
        queue = queue[1:]

        // 建左子樹
        if index < len(levelOrder) {
            if levelOrder[index] != nil {
                node.Left = &TreeNode{Val: levelOrder[index]}
                queue = append(queue, node.Left)
            }
            index++
        }

        // 建右子樹
        if index < len(levelOrder) {
            if levelOrder[index] != nil {
                node.Right = &TreeNode{Val: levelOrder[index]}
                queue = append(queue, node.Right)
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
*/