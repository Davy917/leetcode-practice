class TreeNode {
    constructor(val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

module.exports = TreeNode;

/*
import方式:
const TreeNode = require('../datastructure/Tree/TreeNode');

import vs require的差異
LanguagePractice/JSPractice/Compare4Language/import vs require.md

function TreeNode(val, left, right) {
    this.val = (val === undefined ? 0: val)
    this.left = (val === undefined ? null: left)
    this.right = (val === undefined ? null: right)
}
 */