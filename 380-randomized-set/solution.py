import random
class RandomizedSet:

    def __init__(self):
        self.dict  = {}
        self.list = []
        self.rng = random.Random() # random 的引用（實例）

    def insert(self, val: int) -> bool:
        pass

    def remove(self, val: int) -> bool:
        pass

    def getRandom(self) -> int:
        pass

if __name__ == "main":
    randomizedSet = RandomizedSet()
    randomizedSet.insert(1)
    randomizedSet.remove(2)
    randomizedSet.insert(2)
    randomizedSet.getRandom()
    randomizedSet.remove(1)
    randomizedSet.insert(2)
    randomizedSet.getRandom()