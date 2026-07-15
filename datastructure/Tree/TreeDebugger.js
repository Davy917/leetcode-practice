const TreeNode = require('./TreeNode');

class TreeDebugger {
    static buildLevelOrderTree(levelOrder) {
        if (levelOrder === null)
            return null
        const root = new TreeNode(levelOrder[0]);
        const nodes = []
        nodes.push(root)

        for (let i = 1; i < levelOrder.length; i++) {
            let newNode = null
            if (levelOrder[i] != null)
                newNode = new TreeNode(levelOrder[i])

            let parentIndex = Math.floor((i - 1) / 2)
            let parent = nodes[parentIndex]
            if (parent === null) {
                nodes.push(null)
                continue
            }
            if (i % 2 === 1) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
            nodes.push(newNode);

        }
        return root;
    }
    static main(){
        const levelOrder = [5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1]
        this.buildLevelOrderTree(levelOrder)
    }
}
if (require.main === module) {
    TreeDebugger.main();
}
module.exports = TreeDebugger;

/*
import
const TreeDebugger = require('../datastructure/Tree/TreeDebugger')
 */