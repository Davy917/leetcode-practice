package main
import "fmt"
func maxVowels(s string, k int) int {

	curVal := 0
	for _, c := range s[:k]{
		if c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' {
			curVal++
		}
	}
	maxVal := curVal

	l := 0
	for r := k; r < len(s); r++ {
		if s[r] == 'a' || s[r] == 'e' || s[r] == 'i' || s[r] == 'o' || s[r] == 'u' {
			curVal++
		}
		if s[l] == 'a' || s[l] == 'e' || s[l] == 'i' || s[l] == 'o' || s[l] == 'u' {
			curVal--
		}
		maxVal = max(maxVal, curVal)
		l++
	}
	return  maxVal
}

func main(){
	s := "abciiidef"
	k := 3
	fmt.Println("Ans = ", maxVowels(s, k))
}
/*
第7行判斷式可以改成
if strings.ContainsRune("aeiou", c) {
    curVal++
}
執行方式:
go run ./1456-max-vowels
*/