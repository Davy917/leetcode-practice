## 這兩段代碼用起來都對, 但實際上它們有什麼區別
```go
func Constructor() *MyHashMap {
return &MyHashMap{buckets: make([][]*Pair, 5)}
}

func Constructor() MyHashMap {
return MyHashMap{buckets: make([][]*Pair, 5)}
}
```

實際差別是：

- `func Constructor() *MyHashMap`
  - 回傳的是指標
  - 物件存在堆上
  - 你拿到的是同一份資料
  - 之後修改，外面看到的也是同一份

- `func Constructor() MyHashMap`
  - 回傳的是值
  - 會複製一份結構體
  - 修改這份副本，不會影響原本其他地方的那份
  - 這個值在函數返回後就變成獨立實體

最核心的差別是「指標 vs 值」：

```go
a := Constructor()
b := Constructor()
```

如果是 pointer 版本，`a` 和其他持有同一個 `*MyHashMap` 的地方，看到的是同一個物件；  
如果是 value 版本，`a` 是一份複製品，和其他地方的不一定同一個。

所以這兩段雖然都能「跑」，但語意不同：

- pointer 版本：共享狀態
- value 版本：複製狀態

還有一個很重要的地方：如果你的方法 receiver 是 `*MyHashMap`，那麼當你有一個 `MyHashMap` 變數時，Go 會自動幫你取地址；但如果你是「直接拿函數回傳值」調方法，通常就不一定能直接用，要看是否可取地址。

例如這個：

```go
Constructor().Put(1, 2)
```

很多時候不會像你想的那麼自然，因為回傳值本身通常不是可取地址的。

所以你現在這種情況，`*MyHashMap` 版本和 `MyHashMap` 版本都能用，原因不是它們等價，而是「你呼叫它們的方式剛好讓編譯器幫你補上了地址」。

## 哪一種寫法在邏輯上會比較正確?
邏輯上比較正確的是這種：

```go
func Constructor() *MyHashMap
```

然後方法也用 `*MyHashMap` 當 receiver。

原因很簡單：你的 `HashMap` 本身是「有狀態的容器」，它要保存 `buckets`、`temp` 這些資料，並且讓不同方法調用之間共享這個狀態。  
`Put`、`Get`、`Remove` 都在修改同一個物件，這就是典型的「指標語意」。

如果你寫成：

```go
func Constructor() MyHashMap
```

那它回傳的是「值副本」。  
這意味著：

- 你拿到的是一份複製品
- 之後對這份副本做修改，不一定會影響其他地方持有的原始版本
- 在一個物件型別裡，這通常不符合「map 會持續保存資料」的直覺

所以在邏輯上，`HashMap` 這種資料結構更適合用 pointer 版本，因為它要維持一個持續存在的內部狀態。

簡單說：

- `*MyHashMap`：同一個 map 實體，狀態共享
- `MyHashMap`：每次都是複製一份，像一個新 map

對這類題目，通常要的是前者。  
也就是說，這個題目的「正確語意」是「一個可變的哈希表物件」，所以 pointer 版本比較符合。


## 對python版本的這段存在疑問

```python
    def put(self, key: int, value: int) -> None:
        index = key % 5
        exist = self.contains(key)
        if exist:
            exist.value = value

    #exist看起來是副本Pair, 但 exist.value = value 卻又會改到buckets[index] 裡面的Pair數值??
```

因為在 Python 裡，`exist` 並不是副本Pair，而是「同一個 Pair 物件的參考」。

這段：

```python
for pair in self.buckets[index]:
    if pair.key == key:
        return pair
```

回傳的是 `self.buckets[index]` 裡那個實際元素本身。  
也就是說：

```python
exist = self.contains(key)
```

這時 `exist` 和 `self.buckets[index][某個位置]` 指向同一個物件。

所以這行：

```python
exist.value = value
```

改的是「那個物件的屬性」，而不是改一個副本。  
它等價於：

```python
self.buckets[index][某個位置].value = value
```

因為 `Pair` 是一個可變物件，屬性賦值會直接改到原物件。

關鍵點是：

- 變數賦值 `x = obj`：不是複製 `obj`
- 它只是多了一個名字，指向同一個物件
- 所以 `exist.value = ...` 會改到原來存進 `buckets` 的那個 `Pair`

只有在你明確做「複製」時，才不會影響原本的資料，例如：

```python
exist = Pair(pair.key, pair.value)
```

這樣才是新物件；但你現在的 `return pair` 不是。

所以答案就是：`exist` 不是副本，而是同一個 `Pair` 實例的引用，這也是為什麼它會改到 `buckets[index]` 裡的值。


### 這段的pair 它實際上已經是位址了? 所以contains() 的 return pair 其實也是回傳位址嗎?

```python
    def contains(self, key: int) -> Pair:
        index = key % 5
        for pair in self.buckets[index]:
            #後面略過...
```

是的，對 Python 來說，這個「位址」不是 C 語言那種記憶體位址的字面值，而是「物件引用」。

所以這句：

```python
for pair in self.buckets[index]:
```

裡面的 `pair`，它其實是 `self.buckets[index]` 內那個 `Pair` 物件的引用。

而這句：

```python
return pair
```

回傳的也就是那個 `Pair` 物件本身，不是複製品。  
所以 `exist` 會和 `buckets[index]` 內的那個元素指向同一個物件。

也就是：

```python
exist is self.buckets[index][pos]
```

在這種情況下，會是 `True`，因為它們其實是同一個物件。

所以你可以把它理解成：

- `pair` 不是「值拷貝」
- 它是「指向物件的引用」
- `return pair` 只是把這個引用交出去
- 之後 `exist.value = value` 就會改到原物件

這和 Go 裡 `range` 迴圈裡的 `pair` 不一樣：  
Go 的 `range` 迴圈變數是「複製值」；Python 的 list 迭代拿到的是「原物件引用」。  
這就是為什麼 Python 這種寫法可以直接改到 `buckets` 內的資料，而 Go 這種寫法不行。

所以你現在的判斷方向是正確的：  
Python 裡的 `pair` 本質上是「對物件的引用」，不是複製出來的新資料。

## 深水區


不建議直接用 Go 那套「傳值 / 傳址」去硬套 Python。

更準確的說法是：

- Python 不是「純傳值」
- 也不是「純傳址」
- 它其實是「call by object reference」或更口語一點說「傳的是物件參考，但不等於 C 的記憶體位址」

可以這樣理解：

1. Python 的變數不是「盒子裡放值」
   它更像是「標籤，貼在某個物件上」

例如：

```python
a = [1, 2, 3]
b = a
```

這裡 `a` 和 `b` 都貼在同一個 list 物件上，不是複製一份 list。

2. 函數參數也是一樣
當你做：

```python
def f(x):
    x.append(4)

a = [1, 2, 3]
f(a)
print(a)   # [1, 2, 3, 4]
```

這裡不是「把 list 複製進去」，而是「把同一個 list 物件的引用傳進去」。
所以 `x.append(4)` 會改到原物件。

但如果你寫：

```python
def f(x):
    x = [9, 9, 9]

a = [1, 2, 3]
f(a)
print(a)   # [1, 2, 3]
```

這裡只是把函數內部的 `x` 重新綁定到另一個新 list，外面的 `a` 不會變。
這就是 Python 很容易讓人混淆的地方：

- 修改物件內容：會影響原物件
- 重新綁定參數名稱：不會影響外部

這和 Go 的「傳值」與「傳址」同時都有點像，但又不完全一樣。

3. 所以 Python 更適合這樣分兩層理解
- 物件本身是可變或不可變
- 變數是名稱，名稱可以重新指向另一個物件

例如：

```python
a = 1
a = 2
```

`a` 不是改原本那個 1 的值，而是改成指向一個新的 int 物件。
因為 `int` 是不可變物件。

但：

```python
a = []
a.append(1)
```

這是修改同一個 list 物件本身，因為 list 是可變物件。

4. 對你這個 `Pair` 的情境
這段是最典型的例子：

```python
exist = self.contains(key)
exist.value = value
```

如果 `contains()` 回傳的是 `self.buckets[index]` 裡那個 `Pair` 物件本身，
那 `exist` 就是那個物件的引用，不是副本。

所以：

```python
exist.value = value
```

等價於：

```python
self.buckets[index][某位置].value = value
```

這並不代表 Python 有「傳址」那種 C 語言級別的位址概念，
而是說「你拿到的是同一個物件」。

5. 最好的中文表述
如果要用一句話總結：

- Python 不是用 Go 的「傳值 / 傳址」角度理解最自然
- 比較自然的說法是：Python 傳的是「物件參考」，而且是否影響外部，取決於你是「修改原物件」還是「重新綁定變數」

所以你現在這種理解是對的：

- `return pair` 回傳的是同一個物件
- `exist.value = value` 修改的是該物件
- 不是複製出一份新 Pair

這樣理解比「傳值/傳址」更貼近 Python 真正的語義。