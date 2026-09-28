## str的型別是String, c的型別是char, 為什麼兩者相加不會出錯?
因為 Java 的 `+` 運算子對字串做了**自動型別轉換**。

當 `+` 的其中一邊是 `String` 時，另一邊會自動呼叫 `String.valueOf()` 轉成字串：

```java
str += c;
// 等價於
str = str + String.valueOf(c);
```

所以 `char` 的 `'a'` 會自動變成 `"a"`，再與 `String` 拼接。

這個規則適用於所有基本型別和物件：

```java
"a" + 3       // "a3"  (int → String)
"a" + true    // "atrue" (boolean → String)
"a" + 'x'     // "ax"  (char → String)
```

所以不會出錯，Java 會自動幫你處理。

## 所以java的String不算基本型, String是reference type嗎?
沒錯！`String` 是 **Reference Type（參考型別）**，不是基本型別。

Java 的 **8 個基本型別**：
```
int, long, short, byte, float, double, boolean, char
```

`String` 是一個 **Class**，所以：

```java
String a = "hello";
String b = "hello";
// a 和 b 指向同一個字串物件（字串池）

String c = new String("hello");
// c 指向堆積中一個新的 String 物件
```

這也是為什麼 `str += c` 慢的原因：

```java
str += 'a';
// 實際上是：str = new StringBuilder().append(str).append('a').toString();
// 每次都建立新物件 → 舊物件等著被 GC 回收 → 開銷很大
```

而 `StringBuilder` 是**可變物件**，`append()` 直接在原物件上修改，不建立新物件，所以快得多。

## 是否是這三行導致了執行用時被拖慢?
str += c;

num += c;

str = popOut[1] + str.repeat(Integer.parseInt(popOut[0]));


是的，你的理解正確！但影響程度不同：

| 行 | 影響程度 | 原因 |
|---|---|---|
| `str += c` | ⭐ **最大** | 每個字元都執行一次，每次建立新 `String` 物件 |
| `num += c` | 很小 | 數字通常只有 1~3 位，影響不大 |
| `popOut[1] + str.repeat(...)` | 中等 | 只在遇到 `]` 時執行，但仍有字串拼接開銷 |

所以**最關鍵的優化是把 `str` 改成 `StringBuilder`**，光這一點就能大幅提升速度。

## 目前這種寫法會在Integer, char, String之間切換, 導致執行用時不理想, 這部分有辦法改善嗎?

你的程式碼主要有兩個效能瓶頸：

### 1. `str += c` 迴圈內不斷拼接字串
每次 `+=` 都會建立新的 `String` 物件，應改用 **`StringBuilder`**：

```java
StringBuilder str = new StringBuilder();
// 加字元：str.append(c);
// 取結果：str.toString();
```

### 2. 用 `String[]` 存 stack 導致需要型別轉換
可以把數字和字串**分開存到兩個 stack**，避免 `String ↔ int` 的轉換：

```java
Deque<Integer> numStack = new ArrayDeque<>();
Deque<StringBuilder> strStack = new ArrayDeque<>();
```

---

### 綜合方向：

```java
Deque<Integer> numStack = new ArrayDeque<>();
Deque<StringBuilder> strStack = new ArrayDeque<>();
StringBuilder str = new StringBuilder();
int num = 0;

for (char c : s.toCharArray()) {
    if (Character.isDigit(c)) {
        num = num * 10 + (c - '0');     // 直接算 int，不用存字串再轉
    } else if (c == '[') {
        numStack.push(num);             // 存 int，不用 String[]
        strStack.push(str);
        str = new StringBuilder();
        num = 0;
    } else if (c == ']') {
        int n = numStack.pop();         // 直接取 int，不用 parseInt
        StringBuilder prev = strStack.pop();
        str = prev.append(str.toString().repeat(n));
    } else {
        str.append(c);                  // StringBuilder 附加，不建新物件
    }
}
return str.toString();
```

**核心改善：**
- `StringBuilder` 取代 `+=` → 避免大量暫存字串
- `int` 直接存 stack → 避免 `String ↔ int` 轉換
- 數字判斷用 `Character.isDigit(c)` 更直觀