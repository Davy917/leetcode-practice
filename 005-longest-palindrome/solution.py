#這邊對於回文的定義是,正著唸跟反著唸都一樣就叫做回文,所以單一字符也是回文
class Solution1122:
    def P0005_longestPalindrome(self, s: str) -> str:    
        if not s:#如果是空字串的話,則回傳空值
            return ""
        if not isinstance(s, str):#如果傳入非字串則主動拋出錯誤
            raise ValueError("Input must be a string")
        start = 0
        max_len = 1
        
        for i in range(len(s)):

            left = i
            right = i
            cur_len = 1#每一次都要把當前長度初始化

            while left >= 0 and right < len(s) and s[left] == s[right]:
                print("i=", i, "odd match", s[left])
                left -= 1
                right += 1

            cur_len = right - left -1#最核心的就這一句
            
            if cur_len > max_len:
                max_len = cur_len
                start = left + 1
                print("We get the longer odd palindrome string", s[start:max_len + start])
                print("start = ", start, ", max_len = ", max_len)
#----------------------------------------------------------------------------------
            if i + 1 < len(s):#判斷是不是偶數

                left = i
                right = i + 1
                cur_len = 1

                while left >= 0 and right < len(s) and s[left] == s[right]:#如果判斷出是偶數的話,就會一直待在此迴圈中判斷
                    print("i=", i, "even match", s[left], s[right])
                    left -= 1
                    right += 1

                cur_len = right - left - 1

                if cur_len > max_len:
                    max_len = cur_len
                    start = left + 1
                    print("We get the longer even palindrome string", s[start:max_len + start])
                    print("start = ", start, ", max_len = ", max_len)

#----------------------------------------------------------------------------------

        return s[start:max_len + start]


sol = Solution1122()
print(sol.P0005_longestPalindrome("QWEWQ"))

"""
left來自i, i從0開始, 所以while迴圈第一圈時left勢必是0, 而right也是0,此時while的進入判斷式一定為true, 進入迴圈之後left馬上被-1,其值變成-1,那不就代表,while迴圈不會再被執行了嗎??

ok那這時候回到for迴圈

奇數模擬babad
i = 0進了迴圈一次後
left = -1
right = 1
start = 0
cur_len = 1

i = 1進了迴圈一次
left = 0
right = 2
start = 1
cur_len = 1

i = 2進了迴圈兩次
left = 0
right = 4
start = 1
cur_len = 3

偶數模擬
i = 0進了迴圈一次後
left = -1
right = 1
start = 0
cur_len = 1

i = 1進了迴圈一次後
left = 0
right = 2
start = 1
cur_len = 1

i = 1進了迴圈一次後
left = 0
right = 2
start = 1
cur_len = 1

i = 2進了迴圈一次後
left = 1
right = 3
start = 2
cur_len = 1

進入偶數迴圈兩次
left = 0
right = 5
start = 1
cur_len = 4

babad == aba, bab
i
babad
 i
babad
  i

什麼情形會進到while迴圈
i 左邊有數,右邊也有數,
baabad
i

while迴圈裡面要做什麼??
while迴圈做完之後下一步是什麼??
給出我們找到的回文數長度
left = ?
right = ?
0 >= 0, 0 < 5, 左邊等於右邊

length1, start1 = expand_around_center(i, i) length1哪邊來? start1是?

length1 是 right - left - 1
start1是 left + 1

if length1 > max_len: 如果後來者大於前者
                max_len = length1則前者被取代
                start = start1
"""