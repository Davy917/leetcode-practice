from typing import Dict
class Solution:
    def romanToInt(self, s: str) -> int:

        dict : Dict[str, int] = {
            'I': 1,
            'V': 5,
            'X': 10,
            'L': 50,
            'C': 100,
            'D': 500,
            'M': 1000
        }
        result = dict[s[len(s)-1]]

        for i in range(len(s)-2, -1, -1):
            print("i = ", i)
            if dict[s[i+1]] > dict[s[i]]:
                result -= dict[s[i]]
            else:
                result += dict[s[i]]
        return result
    
if __name__ == "__main__":
    s = "MCMXCIV"
    print(Solution().romanToInt(s))