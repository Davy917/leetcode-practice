class Solution:
    def maxVowels(self, s: str, k: int) -> int:
        cur_sum = sum(1 for c in s[:k] if c in ('a', 'e', 'i', 'o', 'u'))
        max_sum = cur_sum
        left = 0
        for right in range(k, len(s)):
            
            if s[left] in ('a', 'e', 'i', 'o', 'u'):
                cur_sum -= 1

            if s[right] in ('a', 'e', 'i', 'o', 'u'):
                cur_sum += 1
            max_sum = max(max_sum, cur_sum)
            left += 1
            
        return max_sum

if __name__ == "__main__":
    s = "abciiidef"
    k = 9
    print("Ans = ", Solution().maxVowels(s, k))