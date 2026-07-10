function TreeNode(val, left, right) {
    this.val = (val === undefined ? 0: val)
    this.left = (val === undefined ? null: left)
    this.right = (val === undefined ? null: right)
}
module.exports = TreeNode;