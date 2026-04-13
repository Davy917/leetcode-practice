class Solution1122:
    def isHappy(self, n: int) -> bool:
        main_set = set()
        while n not in main_set:
            main_set.add(n)
            next = 0
            while n > 0:
                next += (n % 10) ** 2
                n //= 10
                print("n = ", n)
            n = next
            print("next = ", next)
            if next == 1:
                return True
        return False

print(Solution1122().isHappy(19))

"""
注意事項:
外部while迴圈不能寫成
while main_set.add(n):

因為set的返回值是None，而不是布林值
檢查元素是否在集合中需使用 in 和 not in
所以才寫成
while n not in main_set:
"""