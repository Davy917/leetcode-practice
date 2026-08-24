class quick_union:
    def __init__(self, size):
        self.count = size
        self.parent = [0] * size
        for i in range(size):
            self.parent[i] = i
    def find(self, x: int) -> int:
        if self.parent[x] == x:
            return x
        self.parent[x] = self.find(self.parent[x])  #不熟
        return self.parent[x]
    def union(self, x: int, y: int):
        parent_x = self.find(x)
        parent_y = self.find(y)
        if parent_x != parent_y:
            self.parent[parent_y] = parent_x #容易忽略
            self.count -= 1
    def isConnected(self, x: int, y: int):
        return self.find(x) == self.find(y)


"""
正確import方式看
684-find-redundant-connection/solution.py
"""