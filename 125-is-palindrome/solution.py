class Solution:
    def isPalindrome(self, s: str) -> bool:
        s = s.lower()
        l, r = 0, len(s) - 1
        while l < r:
            while l < r and not s[l].isalnum():
                l += 1
            while l < r and not s[r].isalnum():
                r -= 1
            if s[l] != s[r]:
                return False
            l += 1
            r -= 1
        return True
if __name__ == "__main__":
    s = "A man, a plan, a canal: Panama"
    print("Ans = ", Solution().isPalindrome(s))

#如果字串是字母數字字串，則傳回 True，否則傳回 False。