const pick = 6
/**
 * @param {number} num
 * @return {number}
 */
var guess = function (num) {
    if (num > pick)
        return -1
    else if (num < pick)
        return 1
    else
        return 0
}

/**
 * @param {number} n
 * @return {number}
 */
var guessNumber = function(n) {
    let [left, right] = [1, n]
    while (left <= right){
        const middle = left + Math.floor((right - left) / 2)
        if (guess(middle) === -1){
            right = middle - 1
        }
        else if (guess(middle)){
            left = middle + 1
        }
        else {
            return middle
        }
    }
};
if (require.main === module){
    const player = guessNumber(10);
    console.log("Ans = ", player)
}