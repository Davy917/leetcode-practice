"""
自己寫的看python, go
官方解答看java
"""
from dataclasses import dataclass
@dataclass
class Pair:
    key: int
    value: int

class MyHashMap:

    def __init__(self):
        self.buckets = [[] for _ in range(5)]
        self.temp = 0

    def put(self, key: int, value: int) -> None:
        index = key % 5
        exist = self.contains(key)
        if exist:
            exist.value = value # why??
        else:
            new_pair = Pair(key, value)
            self.buckets[index].append(new_pair)

    def get(self, key: int) -> int:
        exist = self.contains(key)
        if exist:
            return exist.value
        return -1
    
    def remove(self, key: int) -> None:
        index = key % 5
        exist = self.contains(key)
        if exist:
            self.buckets[index].remove(exist)
        
    def contains(self, key: int) -> Pair:
        index = key % 5
        for pair in self.buckets[index]:
            if pair.key == key:
                return pair
        return None

if __name__ == "__main__":
    my_hash_map = MyHashMap()
    my_hash_map.put(1, 5)
    my_hash_map.put(2, 20)
    my_hash_map.put(2, 22)
    my_hash_map.put(3, 30)
    my_hash_map.put(4, 40)
    print(my_hash_map.get(2))
    print(my_hash_map.buckets)