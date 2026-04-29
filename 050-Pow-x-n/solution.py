class Solution(object):
    #修正前
    def myPow_original(self, x, n) -> float:
        result = 1
        half = abs(n) // 2
        if n < 0:
            for i in range(0, half):
                result *= 1 / x
        else:
            for i in range(0, half):
                result *= x
              
        return result * result * x if n & 1 else result * result
    #修正後
    def myPow(self, x, n) -> float:
        result = 1
        #負數n預處理
        if n < 0:
            x = 1 / x
            n = abs(n)
        print(f"init\nn = {n}, x = {x}, result = {result}")
        while n > 0:
            if n & 1:
                result *= x
            x *= x
            n //= 2
            print(f"n = {n}, x = {x}, result = {result}")
        return result
        
if __name__ == "__main__":
    x = 2.0
    n = 10
    print("result = ", Solution().myPow(x, n))

"""
快速冪影片教學
https://youtu.be/GbDtCFhq20A?si=tWEqyB_81Zd6tiRe
"""
