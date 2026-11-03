# `__iter__` 與 `yield` 拆解說明

## 第一層：`__iter__` 是做什麼的？

告訴 Python：「當有人對我用 `for`，要怎麼一個一個給值。」

```python
for val in my_list:   # Python 問：怎麼給？
    print(val)        # 你的 __iter__ 回答這個問題
```

---

## 第二層：`yield` 是做什麼的？

`yield` 就是「**暫停，先給你一個值，等你要下一個再繼續**」。

對比 `return`：

```python
def normal():
    return 1    # 給完就結束，函式消失

def generator():
    yield 1     # 給 1，暫停，等待
    yield 2     # 再給 2，暫停，等待
    yield 3     # 再給 3，函式結束
```

```python
gen = generator()
next(gen)  # 1
next(gen)  # 2
next(gen)  # 3
```

---

## 第三層：`DoublyLinkedList` 的 `__iter__` 在做什麼？

```python
def __iter__(self):
    curr = self._head      # 從頭節點開始
    while curr:
        yield curr.val     # 給出這個節點的值，然後暫停
        curr = curr.next   # 等 for 要下一個，才繼續走到下一個節點
```

逐步拆解（假設鏈表是 `1 <-> 2 <-> 3`）：

```
for val in dll:
    第一次：curr = node(1)，yield 1，暫停  → val = 1
    第二次：curr = node(2)，yield 2，暫停  → val = 2
    第三次：curr = node(3)，yield 3，暫停  → val = 3
    第四次：curr = None，while 結束        → for 結束
```

---

## 為什麼要用 `yield` 而不是 `return`？

如果改成 `return`：

```python
def __iter__(self):
    curr = self._head
    while curr:
        return curr.val   # 給完第一個就結束了，後面全部拿不到
```

`yield` 的作用就是「**每次只給一個，保留目前走到哪**」，這樣 `for` 才能一個一個走完整條鏈表。

---

## `iter()` 與 `__iter__` 的差異

| | 角色 | 誰用 |
|---|---|---|
| `__iter__` | 定義「怎麼迭代」（食譜） | 你在類別裡實作 |
| `iter()` | 取得 iterator 物件（廚師） | Python / 你手動呼叫 |

`iter(obj)` 背後就是呼叫 `obj.__iter__()`：

```
for val in obj
    → iter(obj)
        → obj.__iter__()
            → 回傳 iterator（generator）
                → for 迴圈不斷呼叫 next()
                    → StopIteration → 結束
```

你也可以手動拆解 `for` 迴圈：

```python
nums = [1, 2, 3]
iterator = iter(nums)     # 等同於 nums.__iter__()

try:
    while True:
        num = next(iterator)
        print(num)
except StopIteration:
    pass
```

這段和 `for num in nums: print(num)` 完全一樣效果。

---

## 一句話總結

`__iter__` 裡的 `yield` 就是：

> 「走到這個節點，先給你值，我在這裡等；你要下一個，我再繼續往前走。」

---

## 參考代碼

- `LanguagePractice/PythonPractice/generate.py` — yield / generator 練習
- `LanguagePractice/PythonPractice/iter.py` — iter() / next() 練習
- `datastructure/LinkedList/DoublyLinkedList.py` — `__iter__` 實際應用

