class Solution(object):
    def myPow(self, x, n) -> float:
        result = 1
        half = abs(n) // 2
        if n < 0:
            for i in range(0, half):
                result *= 1 / x
        else:
            for i in range(0, half):
                result *= x
              
        return result * result * x if n & 1 else result * result
      
if __name__ == "__main__":
    x = 2.0
    n = -200000000
    print("result = ", Solution().myPow(x, n))
