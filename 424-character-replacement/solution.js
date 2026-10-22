/*
官方解答java
自己寫的失敗版本js
自己寫的成功版本go
*/
/**
 * @param {string} s
 * @param {number} k
 * @return {number}
 */
var characterReplacement = function(s, k) {
    const m = new Map()
    let[K, right, left, maxLen] = [k, 0, 0, 0]

    cur = s.at(0)
    while(right < s.length) {
        if (cur == s.at(right)) 
            m.set(s.at(left), left)
        else{
            if(K == k)
                left = right //問題的源頭
            if(K > 0)
                K--
            else { //炸掉, 從left再出發
                K = k
                right = left
                cur = s.at(left)
                continue
            }
        }
        maxLen = Math.max(maxLen, right - m.get(cur) + 1)
        right++
    }
    return maxLen
}

if (require.main === module){
    const s = "AABACBBB"
    const s2 = "ABBB"
    console.log("Ans = ", characterReplacement(s2, 2))
}
/*
自己寫的
直上進階版失敗 -> 滑動窗口 + 哈希表

滑動窗口:
cur我現在在比的是哪個字元
left定義: 如果走到炸掉, 我等一下要從哪裡開始
right右邊界, 一直往下走
map[char: 該char的起點]
maxLen = max(maxLen, right - m[char])
                        終點 - 起點

這版遇到測資 ABBB 註定失敗
必須承認 left, right移動機制還是有問題, 要重寫

這版提交會失敗, 改進
424-character-replacement\solution.go
*/