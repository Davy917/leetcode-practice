nums = [1, 2, 3]
for num in nums:
    print(num)
#print(dir(nums))

iterator = iter(nums)
print("what is iterator", iterator)

try:
    while True:
        num = next(iterator)
        print(num)
except StopIteration:
    pass


"""
教學影片:
https://www.youtube.com/watch?v=J-BgmKvYY2o

FAQ:
iter方法跟__iter__的差異在哪邊??

iter() 是內建函式（built-in function）
你呼叫它，它幫你從一個可迭代物件取得 iterator：
nums = [1, 2, 3]
iterator = iter(nums)   # 呼叫 nums.__iter__()
next(iterator)          # 1
next(iterator)          # 2
 
__iter__ 是 dunder 方法（定義在類別裡）
你定義它，告訴 Python「這個物件怎麼產生 iterator」：
class DoublyLinkedList:
    def __iter__(self):
        curr = self._head
        while curr:
            yield curr.val
            curr = curr.next

iter(obj) 背後就是呼叫 obj.__iter__()，這就是整個連結：
for val in obj
    → iter(obj)
        → obj.__iter__()
            → 回傳 iterator
                → for 迴圈不斷呼叫 next()  
                
直接對照兩者角色：

---

### `iter()` 是內建函式（built-in function）

你**呼叫它**，它幫你從一個可迭代物件取得 iterator：

```python
nums = [1, 2, 3]
iterator = iter(nums)   # 呼叫 nums.__iter__()
next(iterator)          # 1
next(iterator)          # 2
```

---

### `__iter__` 是 dunder 方法（定義在類別裡）

你**定義它**，告訴 Python「這個物件怎麼產生 iterator」：

```python
class DoublyLinkedList:
    def __iter__(self):
        curr = self._head
        while curr:
            yield curr.val
            curr = curr.next
```

---

### 兩者的關係

`iter(obj)` 背後就是呼叫 `obj.__iter__()`，這就是整個連結：

```
for val in obj
    → iter(obj)
        → obj.__iter__()
            → 回傳 iterator
                → for 迴圈不斷呼叫 next()


| | 角色 | 誰用 |
|---|---|---|
| `__iter__` | 定義「怎麼迭代」 | 你在類別裡實作 |
| `iter()` | 取得 iterator 物件 | Python / 你手動呼叫 |

`iter()` 是給外部用的入口，`__iter__` 是你在類別裡提供的實作。          
"""