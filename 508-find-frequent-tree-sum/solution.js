const TreeNode = require('../datastructure/Tree/TreeNode');
const TreeDebugger = require('../datastructure/Tree/TreeDebugger')
/**
 * @param {TreeNode} root
 * @return {number[]}
 */
var findFrequentTreeSum = function(root) {
    const count = new Map();
    dfs(root, count)
    return findMaxFreq(count)
};
/**
 * @param {TreeNode} root
 * @param {Map} count
 * @return {number[]}
 */
function dfs(root, count){
    if (!root)
        return 0
    let leftSum = dfs(root.left, count)
    let rightSum = dfs(root.right, count)
    let sum = leftSum + rightSum + root.val
    count.set(sum, (count.get(sum) || 0) + 1)
    return sum
}
/**
 * @param {Map} count
 * @return {number[]}
 */
function findMaxFreq(count){
    let maxFreq = 1
    for (const freq of count.values()) {
        if (freq > maxFreq)
            maxFreq = freq
    }
    const result = []
    for (const [sum, freq]of count.entries()) {
        if (freq === maxFreq)
            result.push(sum)
    }
    return result
}
if (require.main === module){
    const levelOrder = [5,2,-3]
    const root = TreeDebugger.buildLevelOrderTree(levelOrder)
    console.log("Ans = ", findFrequentTreeSum(root))
}