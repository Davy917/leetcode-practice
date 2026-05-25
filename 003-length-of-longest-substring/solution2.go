//這個版本是用map實現
package main
func lengthOfLongestSubstring2(s string) int {
	charMap := make(map[byte]int)
	maxLen := 0
	l, r := 0, 0
	for r < len(s){
		charMap[s[r]]++
		for charMap[s[r]] > 1{
			charMap[s[l]]--
			l++
		}
		maxLen = max(maxLen, r-l+1)
		r++
	}
	return  maxLen
}