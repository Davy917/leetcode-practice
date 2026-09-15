# Java, JavaScript, Python, Go 棧 (Stack) 操作指南

本文件介紹了 Java、JavaScript、Python 與 Go 四種語言如何宣告、初始化棧，以及常用的查找、入棧、出棧操作，最後進行差異性比較。

---

## 1. Java

在 Java 中，雖然有舊的 `Stack` 類別，但現在官方推薦使用 `Deque` 介面及其實現類 `ArrayDeque` 來當作棧使用。

### 宣告與初始化
```java
import java.util.Deque;
import java.util.ArrayDeque;

// 宣告並初始化
Deque<Integer> stack = new ArrayDeque<>();
```

### 常用操作
- **入棧 (Push)**: `stack.push(element);`
- **出棧 (Pop)**: `stack.pop();` (若棧為空會拋出 `NoSuchElementException`)
- **查看棧頂 (Peek)**: `stack.peek();` (返回棧頂元素但不刪除，棧空返回 `null`)
- **檢查是否為空**: `stack.isEmpty();`
- **獲取大小**: `stack.size();`

---

## 2. JavaScript (Node.js)

JavaScript 沒有專門的 Stack 類型，通常直接使用內建的 `Array`。

### 宣告與初始化
```javascript
// 宣告並初始化
const stack = [];
```

### 常用操作
- **入棧 (Push)**: `stack.push(element);`
- **出棧 (Pop)**: `stack.pop();` (棧空返回 `undefined`)
- **查看棧頂 (Peek)**: `stack[stack.length - 1];`
- **檢查是否為空**: `stack.length === 0;`
- **獲取大小**: `stack.length;`

---

## 3. Python

Python 同樣使用動態數組 `list` 作為棧的基礎。

### 宣告與初始化
```python
# 宣告並初始化
stack = []
```

### 常用操作
- **入棧 (Push)**: `stack.append(element)`
- **出棧 (Pop)**: `stack.pop()` (棧空會拋出 `IndexError`)
- **查看棧頂 (Peek)**: `stack[-1]` (棧空會拋出 `IndexError`)
- **檢查是否為空**: `not stack` 或 `len(stack) == 0`
- **獲取大小**: `len(stack)`

---

## 4. Go

Go 語言中沒有內建的 Stack 容器，通常使用 `slice` (切片) 手動實現。

### 宣告與初始化
```go
// 宣告並初始化
stack := make([]int, 0)
// 或者
var stack []int
```

### 常用操作
- **入棧 (Push)**: `stack = append(stack, element)`
- **出棧 (Pop)**:
  ```go
  if len(stack) > 0 {
      element := stack[len(stack)-1]
      stack = stack[:len(stack)-1]
  }
  ```
- **查看棧頂 (Peek)**: `stack[len(stack)-1]`
- **檢查是否為空**: `len(stack) == 0`
- **獲取大小**: `len(stack)`

---

## 5. 綜合比較

| 特性 | Java (`Deque`) | JavaScript (`Array`) | Python (`list`) | Go (`slice`) |
| :--- | :--- | :--- | :--- | :--- |
| **底層結構** | 動態數組 (ArrayDeque) | 動態數組 (Array) | 動態數組 (List) | 動態數組 (Slice) |
| **入棧方法** | `push()` | `push()` | `append()` | `append()` |
| **出棧方法** | `pop()` | `pop()` | `pop()` | 切片操作 `stack[:len-1]` |
| **查看棧頂** | `peek()` | `stack[len-1]` | `stack[-1]` | `stack[len-1]` |
| **空棧彈出行為** | 拋出異常 (Exception) | 返回 `undefined` | 拋出異常 (IndexError) | 需自行判斷，否則 panic |
| **語法簡潔度** | 中等 (需要 Import) | 高 | 高 | 低 (需手動管理長度) |

### 差異性分析
1.  **專門類別 vs 通用容器**: Java 提供了 `Deque` 介面明確定義了棧的行為；而 JS、Python 和 Go 則更傾向於直接使用其最強大的通用序列容器（Array/List/Slice）。
2.  **安全機制**: Java 和 Python 在對空棧進行 `pop` 時會拋出明確的錯誤，強制開發者處理異常；JavaScript 則較為寬鬆，返回 `undefined`；Go 則完全交由開發者控制，操作不當會引發運行時崩潰 (panic)。
3.  **語法糖**: Python 的負索引 `stack[-1]` 使得查看棧頂非常優雅；Go 則最為原始，需要頻繁使用 `len()` 函數來定位末尾。
4.  **擴容機制**: 這四種語言底層基本都是動態數組，因此入棧的平均時間複雜度皆為 $O(1)$，但在發生擴容時可能會有暫時性的性能抖動。
