# Copilot Chat Conversation Export: Java 字串類別比較：String、StringBuilder

**User:** @Davy917
**Thread URL:** https://github.com/copilot/c/34a8c6ff-e96d-4b4d-a0de-ae0be8acc680

## @Davy917

這是一個java隨便問問專區

## Copilot

收到，之後我會用這個方式配合你：

- 這裡是 **Java 隨便問問專區**
- 你問 Java 觀念、語法、LeetCode 寫法、效能、除錯、重構，我都直接用這個 repo 脈絡回答
- 如果有終端機指令，我會：
  - 用 ```bash``` 程式碼區塊
  - 多步驟用 `&&` 串成同一行
  - 路徑有空白自動加雙引號
  - 若是 WSL，Windows 磁碟路徑改成 `/mnt/<drive>/`

也會盡量符合你這個 repo 的習慣：
- 以單題資料夾為單位思考
- 優先維持 Java 解答自包含
- 避免引入跨資料夾共用類別造成 class name 衝突
- 會傾向配合當前題目資料夾既有命名風格

你可以直接丟問題，例如：
- `ArrayList 跟 LinkedList 什麼時候選哪個？`
- `為什麼 Java 的 pass by value 常讓人搞混？`
- `這題 two sum 用 HashMap 怎麼想？`
- `我這段 Java 為什麼 NPE？`
- `幫我把這題改成比較像面試可講的版本`

直接問就行。

## @Davy917

介紹以及比對, String, StringBuilder, StringBuffer

## Copilot

先講結論：**大多數情況用 `String`；需要頻繁修改字串時用 `StringBuilder`；只有在多執行緒且同一個可變字串物件會被共享時，才考慮 `StringBuffer`。**

---

## 1. `String`

`String` 是 **不可變** 的字串類別。

意思是你一旦建立了字串內容，就不能直接改它；任何看起來像「修改」的操作，其實都是**產生新物件**。

### 例子

```java
String s = "hello";
s = s + " world";
System.out.println(s);
```

看起來像是把 `s` 改掉，但實際上是：
- 原本 `"hello"` 不變
- 新產生 `"hello world"`
- 再把 `s` 指向新字串

### 優點
- 安全、不容易出 bug
- 很適合拿來當常數、參數、回傳值
- 可當 `HashMap` key，因為內容不會變
- JVM 對 `String` 有很多最佳化，例如 string pool

### 缺點
- 如果你一直拼接字串，會產生很多中間物件
- 效能可能比較差，特別是在迴圈裡面

---

## 2. `StringBuilder`

`StringBuilder` 是 **可變** 字串。

你可以在同一個物件上一直 append、insert、delete，不用每次都建立新字串。

### 例子

```java
StringBuilder sb = new StringBuilder();
sb.append("hello");
sb.append(" ");
sb.append("world");

System.out.println(sb.toString());
```

### 優點
- 適合頻繁修改字串
- 效能通常比 `String` 反覆 `+` 好很多
- 單執行緒場景最常用

### 缺點
- 不是 thread-safe
- 如果多執行緒共用同一個 `StringBuilder`，可能有問題

---

## 3. `StringBuffer`

`StringBuffer` 跟 `StringBuilder` 很像，也是 **可變** 字串，差別是它的方法大多有 `synchronized`，所以是 **thread-safe**。

### 例子

```java
StringBuffer sb = new StringBuffer();
sb.append("hello");
sb.append(" ");
sb.append("world");

System.out.println(sb.toString());
```

### 優點
- 多執行緒共用時比較安全

### 缺點
- 因為有同步鎖，通常比 `StringBuilder` 慢
- 現代 Java 開發中，使用頻率通常比 `StringBuilder` 低很多

---

# 三者核心差異表

| 類別 | 可變性 | 執行緒安全 | 效能 | 適用場景 |
|---|---|---:|---:|---|
| `String` | 不可變 | 安全（因不可變） | 拼接多時較差 | 一般字串表示 |
| `StringBuilder` | 可變 | 否 | 快 | 單執行緒頻繁字串操作 |
| `StringBuffer` | 可變 | 是 | 較慢 | 多執行緒共享字串緩衝 |

---

# 常見比較

## `String` vs `StringBuilder`

### `String`
適合：
- 單純存字串值
- 幾乎不修改
- 可讀性優先

```java
String name = "Davy";
String message = "Hello, " + name;
```

這種少量拼接其實很正常，直接用 `String` 就好。

### `StringBuilder`
適合：
- 迴圈中反覆拼接
- 組 SQL、組輸出、組大段文字
- LeetCode 題目像反轉字串、壓縮字串、模擬輸出

```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append(i);
}
String result = sb.toString();
```

這種情況如果用 `String`，通常會浪費很多效能。

---

## `StringBuilder` vs `StringBuffer`

功能很像，主要差在 **同步**。

### `StringBuilder`
```java
StringBuilder sb = new StringBuilder();
sb.append("abc");
```

### `StringBuffer`
```java
StringBuffer sb = new StringBuffer();
sb.append("abc");
```

如果你沒有多執行緒共享需求，**幾乎都選 `StringBuilder`**。

---

# 為什麼 `String` 拼接有時候又不慢？

這點很容易混淆。

像這種：

```java
String s = "a" + "b" + "c";
```

編譯器常會直接幫你優化成 `"abc"`。

甚至像：

```java
String result = x + y + z;
```

編譯器/JVM 在很多情況下也會幫你轉成類似 `StringBuilder` 的操作。

但是如果是在**迴圈內反覆拼接**，你還是不要賭優化，通常直接自己用 `StringBuilder` 最穩。

---

# LeetCode / 面試角度怎麼選

## 用 `String`
當你只是：
- 接收輸入字串
- 做 `substring`
- 比較內容
- 判斷字元

例如：

```java
String s = "leetcode";
char c = s.charAt(0);
```

---

## 用 `StringBuilder`
這是 LeetCode 最常見的字串工具之一。

### 典型情境
1. **反轉字串**
```java
String s = "abc";
String reversed = new StringBuilder(s).reverse().toString();
```

2. **累積答案**
```java
StringBuilder ans = new StringBuilder();
for (char ch : s.toCharArray()) {
    if (Character.isDigit(ch)) {
        ans.append(ch);
    }
}
return ans.toString();
```

3. **模擬題**
像括號處理、解碼字串、整理輸出格式。

---

## 用 `StringBuffer`
LeetCode 幾乎很少需要。
因為大多數題目都是單執行緒環境。

---

# 常見誤區

## 1. 以為 `String` 的 `+` 一定很差
不一定。

- 少量拼接：通常沒差，可讀性更重要
- 大量拼接或迴圈：`StringBuilder` 更合適

---

## 2. 以為 `StringBuffer` 比 `StringBuilder` 高級
不是。

它只是多了同步保護，不代表比較新或比較推薦。
在單執行緒程式裡，通常 `StringBuilder` 更適合。

---

## 3. 以為 `String` 改值會改到原本內容
不會。

```java
String a = "hello";
String b = a;
a = a + " world";
System.out.println(b); // 還是 hello
```

因為 `String` 不可變。

---

# 實務選擇口訣

你可以記這個：

- **不太改字串** → `String`
- **一直改字串** → `StringBuilder`
- **多執行緒共享同一份可變字串** → `StringBuffer`

---

# 面試版一句話回答

**`String` 不可變，適合一般字串使用；`StringBuilder` 可變且效能較好，適合單執行緒頻繁拼接；`StringBuffer` 可變且 thread-safe，適合多執行緒共享場景，但通常比 `StringBuilder` 慢。**

---

如果你要，我下一則可以直接幫你補：
1. **`==` vs `equals()` 在 String 上的差別**
2. **String pool 是什麼**
3. **LeetCode 常見 `StringBuilder` 技巧整理**
