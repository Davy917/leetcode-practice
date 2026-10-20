package main

import "fmt"

func isHappy(n int) bool {
    set := make(map[int]struct{})
    for n != 1 {
        temp := 0
        for n != 0 {
            temp += (n % 10) * (n % 10)
            n /= 10
        }
        n = temp
        if _, exist := set[n]; exist{
            return false
        }
        set[n] = struct{}{}
    }
    return true
}
func main(){
	fmt.Println("Ans = ", isHappy(19))
}
/*
自己寫的
如果在循環的過程中, 又遇到之前測試過的數, 那這筆輸入一定不可能是快樂數
其它的都有可能是快樂數

12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
*/