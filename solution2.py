class Solution(object):
    def nextGreatestLetter(self, letters, target):
        left = 0
        right = len(letters) - 1
        while left < right:
            middle = left + (right - left) // 2
            print(f"left = {left}, right = {right}, middle = {middle}")
            if letters[middle] < target:
                left = middle + 1
            elif letters[middle] > target:
                right = middle

            else:
                right = middle + 1
                return letters[right]
        return letters[right] if letters[right] > target else letters[0]


if __name__ == "__main__":
    letters = ['c', 'f', 'j', 'l', 'b', 'v']
    letters2 = ['e','e','g','g']
    target = 'g'
    print("Ans = ", Solution().nextGreatestLetter(letters2, target))
