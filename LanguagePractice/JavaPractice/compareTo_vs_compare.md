# `compareTo` 與 `compare` 的原始定義

## 問題
如果我們不覆寫 `compare`、`compareTo`，那麼這兩個東西原先是什麼，以及他們在做什麼？

---

## 1. `compareTo` 來自 `Comparable` 介面

`compareTo` 是 `java.lang.Comparable` 介面的方法：

```java
public interface Comparable<T> {
    int compareTo(T o);
}
```

**Java 內建的很多類都已經覆寫過它**，例如：

- `Integer`：按數字大小比
- `String`：按字典順序比
- `Double`：按數字大小比

所以當你把 `Integer` 或 `String` 放進 `TreeSet`，它們能自動排序，就是因為這些類已經寫好了 `compareTo`。

---

## 2. 如果你的類不覆寫 `compareTo`，放進 `TreeSet` 會怎樣？

如果你寫了：

```java
class User {
    String name;
    int age;
}
```

**沒有** `implements Comparable`，然後直接：

```java
set.add(new User("Tom", 25));
```

會在執行時丟出：

```
ClassCastException: User cannot be cast to java.lang.Comparable
```

因為 `TreeSet` 要排序，但你的類沒有提供比較規則。

---

## 3. `compare` 來自 `Comparator` 介面

`compare` 是 `java.util.Comparator` 介面的方法：

```java
public interface Comparator<T> {
    int compare(T o1, T o2);
}
```

它不是類本身的方法，而是一個**外部比較器**。  
你可以用它來「不改動原始類」就定義排序規則，像 `ts2()` 裡的做法：

```java
var com = new Comparator<>() {
    @Override
    public int compare(Object o1, Object o2) { ... }
};
var set = new TreeSet<>(com);
```

---

## 4. 回傳值的意義（兩者相同）

無論是 `compareTo` 還是 `compare`，回傳值代表：

| 回傳值 | 意思 |
|---|---|
| `< 0` | 第一個參數排在前面 |
| `= 0` | 視為相同元素 |
| `> 0` | 第一個參數排在後面 |

---

## 5. 總結對照表

|  | `compareTo` | `compare` |
|---|---|---|
| 來自 | `Comparable` 介面（類本身實作） | `Comparator` 介面（外部傳入） |
| 覆寫位置 | 在元素類裡面 | 在外部定義，傳給 `TreeSet` |
| 不覆寫時 | 沒有 → 拋 `ClassCastException` | 沒有 → 不傳 `Comparator`，改用自然排序 |
| Java 內建類 | `Integer/String` 等已寫好 | 沒有預設值，需要自己寫 |

