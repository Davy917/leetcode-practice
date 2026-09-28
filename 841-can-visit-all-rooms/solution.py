class Solution:
    def canVisitAllRooms(self, rooms: list[list[int]]) -> bool:
        def dfs(num):
            if visited[num] is True:
                return
            visited[num] = True
            for key in rooms[num]:
                dfs(key)
        visited = [False] * len(rooms)
        dfs(0)
        for v in visited:
            if not v:
                return False
        return True
if __name__ == "__main__":
    rooms = [[1,3],[3,0,1],[2],[0]]
    print("Ans = ", Solution().canVisitAllRooms(rooms))