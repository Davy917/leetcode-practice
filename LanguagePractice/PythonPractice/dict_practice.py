from collections import defaultdict
class dict_practice:
    def different_dict(self):
        #一般dict存取不存在的元素會直接報錯
        d = {}
        try:
            print(d['a'])
        except KeyError:
            print("d['a'] KeyError")


        words = ["apple", "banana", "apple", "cherry", "banana"]
        dd = defaultdict(int)
        for w in words:
            for char in w:
                dd[char] += 1 #dd[char]不存在時會自動變 0
                #print(dd)

        data = [("red", 1), ("blue", 2), ("red", 3), ("blue", 4)]
        dd = defaultdict(list)
        #tuple自動解包
        for color, num in data:
            print(color, num) #觀察打印結果
            dd[color].append(num)
            print(dd)

        #enumerate使用
        for color, num in enumerate(data):
            print(color, num)

if __name__ == "__main__":
    dp = dict_practice()
    dp.different_dict()

"""
工廠函數 (Factory Function) 的概念
工廠函數是一個沒有參數的函數，它返回一個默認值。常見的工廠函數有：  

    int: 返回 0 (整數的默認值)。
    list: 返回 [] (空列表)。
    dict: 返回 {} (空字典)。
    set: 返回 set() (空集合)。

你需要傳入一個可調用對象 (callable)，它在被調用時會返回一個默認值。
最常見的可調用對象是內建類型（如 int, list, dict, set）或你自定義的無參函數。  
defaultdict 的括號裡必須有一個參數，這個參數就是告訴 defaultdict 當遇到新鍵時，應該創建什麼樣的默認值。  
"""