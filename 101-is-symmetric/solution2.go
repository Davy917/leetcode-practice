// 官解
package main

func isSymmetric_v2(root *TreeNode) bool {
	u, v := root, root
	q := []*TreeNode{}
	q = append(q, u)
	q = append(q, v)
	for len(q) > 0 {
		u, v = q[0], q[1]
		q = q[2:]
		if u == nil && v == nil {
			continue
		}
		if u == nil || v == nil {
			return false
		}
		if u.Val != v.Val {
			return false
		}
		q = append(q, u.Left)
		q = append(q, v.Right)

		q = append(q, u.Right)
		q = append(q, v.Left)
	}
	return true
}

/*
官解:
方法二
https://leetcode.cn/problems/symmetric-tree/solutions/268109/dui-cheng-er-cha-shu-by-leetcode-solution/

拿 python 版本來改的, 用for迴圈改寫遞歸, 建議先看過102題, 了解層序遍歷是如何運作的
102-level-order

這一版自己想不到, 值得學習的就是它巧妙的安排

左半邊:
	1
   /
  2
 / \
3   4
		右半邊:
			1
			 \
			  2
			 / \
			4   3
*/
