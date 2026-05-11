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