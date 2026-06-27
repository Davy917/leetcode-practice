from typing import List
from TreeNode import TreeNode
class TreeDebugger:
    def build_levelorder_tree(self, level_order: List) -> TreeNode:
        n = len(level_order)
        root = TreeNode(level_order[0])
        nodes: List = []
        nodes.append(root)
        for i in range(1, n):
            new_node = None
            if level_order[i] is not None:
                new_node = TreeNode(level_order[i])
            parent_index = (i-1) // 2
            parent = nodes[parent_index]
            if i % 2 == 1:
                parent.left = new_node
            else:
                parent.right = new_node
            nodes.append(new_node)            
        return root
    
if __name__ =="__main__":
    level_order = [3, 9, 20, None, None, 15, 7]
    TreeDebugger().build_levelorder_tree(level_order)

#要匯入可以直接寫 from datastructure import TreeDebugger