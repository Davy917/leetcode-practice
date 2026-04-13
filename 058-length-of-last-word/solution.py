class Solution1122:
    def lengthOfLastWord(self, s: str) -> int:

        #宣告計數器
        Ans = 0

        #進入迴圈判定
        for i in range (len(s)-1, -1, -1):
            print(i)
            
            if s[i] == " " and Ans == 0:
                print("in if")
                continue

            elif s[i] == " " and Ans != 0:
                print("in elif")
                return Ans
            
            else:
                Ans += 1
                print("in else", Ans)
        
        return Ans

result = Solution1122().lengthOfLastWord("y")
print(result)