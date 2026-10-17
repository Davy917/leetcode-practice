"""
自己寫的看python
官方解看java
"""
class MyHashSet:

    def __init__(self):
        self.buckets = [[] for _ in range(10)]
    def add(self, key: int) -> None:
        if self.contains(key):
            return
        index = key % 10
        self.buckets[index].append(key)

    def remove(self, key: int) -> None:
        index = key % 10
        if self.contains(key):
            self.buckets[index].remove(key)

    def contains(self, key: int) -> bool:
        index = key % 10
        if key in self.buckets[index]:
            return True
        return False

if __name__ == "__main__":
    my_hash_set = MyHashSet()
    my_hash_set.add(1)
    my_hash_set.add(2)
    my_hash_set.add(0)
    my_hash_set.add(5)
    my_hash_set.remove(2)
"""
自己寫的, 鏈地址法
兩個問題:
如何讓映射結果分布具有均勻性?
發生衝突時線性查找, 怎麼優化?
705-my-hash-set\python_improve.md
"""