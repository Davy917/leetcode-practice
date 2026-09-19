function _Node(val, neighbors) {
    this.val = val === undefined ? 0 : val
    this.neighbors = neighbors === undefined ? [] : neighbors
}

/**
 * @param {_Node} origin
 * @param {_Node} copy
 * @param {Map} built
 * @return null
 */
const dfs = (origin, copy, built) => {
    for (const neighbor of origin.neighbors) {
        if (!built.has(neighbor.val)){
            var newNode = new _Node(neighbor.val, undefined);
            built.set(newNode.val, newNode) //newNode.val, neighbor.val是一樣的, 二選一即可
            copy.neighbors.push(newNode)
            dfs(neighbor, newNode, built)
        }
        else {
            let toAdd = built.get(neighbor.val) //這時取出來的是我們拷貝的
            copy.neighbors.push(toAdd)
        }
    }
}

/**
 * @param {_Node} node
 * @return {_Node}
 */
const cloneGraph = function (node) {
    const built = new Map();
    if (node === null)
        return null
    let root = new _Node(node.val, undefined)
    built.set(root.val, root)
    dfs(node, root, built)
    return root
};

/*
dfs要做的是遍歷 node 節點, 然後在遍歷的過程中我們把整張圖給拷貝起來

陷阱:
const newNode = new _Node(node.val, node.neighbors)
這種建出來的 newNode.neighbors, 會直接指向 node.neighbors 不算深拷貝
深拷貝必須連 newNode.neighbors 裡面的元素也都是拷貝出來的

假設 adjList = [[2,3],[1,4,5],[1],[2,6],[2],[4]]

初始化:
在外面先把節點1建起來

進入dfs:
節點1那層
    訪問adjList[2, 3]
        節點2建立起了?
            沒有--->建立, 並塞入節點1的鄰居, 走進節點2 <-----------
            有--->塞入節點1的鄰居
節點2那層
    訪問adjList[1, 4, 5]
        節點1建立起了?
            沒有--->建立, 並塞入節點2的鄰居, 走進節點1
            有--->塞入節點2的鄰居 <-----------
        節點4建立起了?
            沒有--->建立, 並塞入節點2的鄰居, 走進節點4 <-----------
            有--->塞入節點2的鄰居
節點4那層
    訪問adjList[2, 6]
        節點2建立起了?
            沒有--->建立, 並塞入節點4的鄰居, 走進節點2
            有--->塞入節點4的鄰居 <-----------
        節點6建立起了?
            沒有--->建立, 並塞入節點4的鄰居, 走進節點6 <-----------   
            有--->塞入節點4的鄰居
節點6那層
    訪問adjList[4]
        節點4建立起了?
            沒有--->建立, 並塞入節點1的鄰居
            有--->塞入節點6的鄰居 <-----------
            退出自動回到節點4那層
節點4那層
        退出自動回到節點2那層
節點2那層
        節點5建立起了?
            沒有--->建立, 並塞入節點2的鄰居, 走進節點5 <-----------
            有--->塞入節點2的鄰居
後面掠過

總結:
節點2那層, 鄰居為[1, 4, 5], 發現1已經建立過了, 代表1是2的父節點, 走到節點2之前一定經過了節點1, 所以我們可以不用走回去
也就是說, 所有當前的鄰居, 如果有已經建立過的代表我們可以不用再走回去

代碼結構:
遍歷所有origin.neighbors
    如果 neighbor 還沒 copy 過：
        建立 copy
        記錄到 built
        加到目前節點的 neighbors
        繼續 dfs

    如果 neighbor 已經 copy 過：
        從 built 取出 copy
        加到目前節點的 neighbors

練習時一定要把鄰接表轉成實際的圖, 並畫在紙上
*/