from typing import List

class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:


        for i in range (len(strs[0])):

            for j in range(1, len(strs)):
                #檢查到當前字串後面已無字元
                if i == len(strs[j]):
                    return strs[0][:i]
                #檢查到有任何一個字不同
                if strs[j][i] != strs[0][i]:
                    return strs[0][:i]
                
        #如果字串中的元素都一樣
        return strs[0]
    
result = Solution().longestCommonPrefix(["flo", "flo", "flo"])
print(result)