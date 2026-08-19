function QuickUnion(size){
    this.parent = []
    this.count = size
    for (let i = 0; i < size; i++)
        this.parent[i] = i;
    console.log("parent = ", this.parent)
}

QuickUnion.prototype.find = function (x){
    if (this.parent[x] === x)
        return x
    return this.parent[x] = this.find(this.parent[x])
}

QuickUnion.prototype.find_v2 = function (x){
    if (this.parent[x] !== x)
        this.parent[x] = this.find_v2(this.parent[x])

    return this.parent[x] //為什麼不是return x 見下方
}

QuickUnion.prototype.union = function (x, y){
    let parentX = this.find(x)
    let parentY = this.find(y)
    if (parentX !== parentY){
        this.parent[parentY] = parentX
        this.count--
    }
}

QuickUnion.prototype.isConnected = function (x, y){
    return this.find(x) === this.find(y)
}

if (require.main === module){
    new QuickUnion(4)
}
module.exports = QuickUnion
/*
實戰:
323-count-components/solution.js

find, find_v2 都是路徑壓縮的寫法, 邏輯不同

Example:
  root = [0, 1, 2, 3] //數值
parent = [0, 0, 1, 2]

find_v2[遞迴樹]
find_v2 = function (3)
    if (this.parent[3] !== 3)

    find_v2 = function (2)
        if (this.parent[2] !== 2)

        find_v2 = function (1)
            if (this.parent[1] !== 1)

            find_v2 = function (0)
                if (this.parent[1] !== 0)<------false
                return parent[0] = 0

            this.parent[1] = 0
            return parent[1] = 0

        this.parent[2] = 0
        return parent[2] = 0

    this.parent[3] = 0
    return parent[3] = 0

得到結果
parent = [0, 0, 0, 0]

回答第18行的問題
return this.parent[x] //為什麼不是return x ??
先看上面遞迴樹, 如果是 return x 那麼遞迴樹中第64, 67, 70就不會回傳0, 而是回傳當層 x 值
會導致路徑無法被壓縮
*/
