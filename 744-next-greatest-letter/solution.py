"""
參考代碼模板:
algo\BinarySearch\BinarySearch_basic
"""
class Solution(object):
    def nextGreatestLetter(self, letters, target):
        left = 0
        right = len(letters) - 1
        
        while left < right:
            middle = left + (right - left) // 2
            print(f"left = {left}, right = {right}, middle = {middle}")
            if letters[middle] <= target:
                left = middle + 1
            else:
                right = middle

        return letters[right] if letters[right] > target else letters[0]

if __name__ == "__main__":
    letters = ['c', 'f', 'j', 'l', 'v', 'w', 'y']
    letters2 = ['e','e','g','g']
    target = 'g'
    print("Ans = ", Solution().nextGreatestLetter(letters2, target))
