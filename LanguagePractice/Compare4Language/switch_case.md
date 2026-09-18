### Java, JavaScript, Python, Go Switch-Case 語法比較

在演算法與邏輯控制中，`switch` (或類似的模式匹配) 是處理多分支選擇的常見方式。以下整理了四種語言的寫法與特性。

---

#### 1. Java
Java 的 `switch` 經歷了多次演進，目前支持傳統語法與 Java 12+ 引入的表達式語法。

*   **傳統語法**: 使用 `case` 與 `break`。若忘記 `break` 會發生 **fall-through**。
*   **Switch 表達式 (Java 12+)**: 使用 `->`，不需要 `break`，且可以有返回值。
*   **多值匹配**: `case 1, 2, 3 -> ...`

```java
// 傳統式
switch (day) {
    case 1:
    case 2:
        System.out.println("Weekday");
        break;
    default:
        System.out.println("Unknown");
}

// 表達式 (Java 12+, 推薦)
String type = switch (day) {
    case 1, 2, 3, 4, 5 -> "Weekday";
    case 6, 7 -> "Weekend";
    default -> {
        if (day < 0) yield "Invalid"; // 使用 yield 返回複雜邏輯結果
        else yield "Unknown";
    }
};
```

#### 2. JavaScript
JS 的 `switch` 行為與 C/Java 傳統語法相似，但在現代開發中，常使用 **Object Literal** 或 **Map** 來替代複雜的分支。

*   **標準語法**: 需手動 `break`。
*   **常用變化**: 使用物件查找。

```javascript
// 標準語法
switch (status) {
    case 'success':
        doSuccess();
        break;
    case 'error':
    case 'fatal':
        handleError();
        break;
    default:
        handleDefault();
}

// 物件查找替代方案 (更簡潔)
const actions = {
    'success': () => console.log('OK'),
    'error': () => console.log('Error'),
};
(actions[status] || actions['default'])();
```

#### 3. Go
Go 的 `switch` 非常靈活且安全性高，是該語言的特色之一。

*   **隱式 Break**: 默認執行完 `case` 就結束，不需要寫 `break`。若要繼續執行下一行需顯式使用 `fallthrough`。
*   **多值匹配**: `case "apple", "banana":`
*   **無條件 Switch**: `switch` 後面不接變數，直接在 `case` 寫布林表達式，是 `if-else if` 的優雅替代方案。

```go
// 基本用法
switch os := runtime.GOOS; os {
case "darwin":
    fmt.Println("macOS")
case "linux", "freebsd": // 多值
    fmt.Println("Unix-like")
default:
    fmt.Println(os)
}

// 作為 if-else 替代 (無條件 switch)
score := 85
switch {
case score >= 90:
    fmt.Println("A")
case score >= 80:
    fmt.Println("B")
default:
    fmt.Println("C")
}
```

#### 4. Python
Python 長期沒有 `switch`，直到 3.10 版本引入了強大的 **Structural Pattern Matching** (`match...case`)。

*   **Python 3.10+**: `match...case` 語句。
*   **舊版方案**: 使用 `if-elif-else` 或 `dict.get()`。

```python
# Python 3.10+ match...case
status = 404
match status:
    case 200:
        print("OK")
    case 400 | 404 | 405:  # 使用 | 進行多值匹配 (OR)
        print("Not Found/Bad Request")
    case _:  # 相當於 default
        print("Something else")

# 模式匹配 (進階用法)
point = (0, 10)
match point:
    case (0, 0):
        print("Origin")
    case (0, y):
        print(f"On Y axis at {y}")
    case (x, y) if x == y: # 帶 guard 的匹配
        print("On diagonal")
```

---

### 特性對比表

| 特性 | Java | JavaScript | Go | Python (3.10+) |
| :--- | :--- | :--- | :--- | :--- |
| **關鍵字** | `switch` | `switch` | `switch` | `match` |
| **預設中斷** | 否 (需 `break`) | 否 (需 `break`) | **是** (免 `break`) | **是** |
| **多值匹配** | `case 1, 2` | 疊加 `case` | `case 1, 2` | `case 1 \| 2` |
| **支持類型** | 基本類型、String、Enum | 任何類型 | 任何類型 | 任何類型 + 結構匹配 |
| **表達式支持** | 是 (Java 12+) | 否 | 否 (可用匿名函數模擬) | 否 |

---

### 總結建議
- **LeetCode 刷題**: Go 的 `switch { case ... }` 在處理區間問題（如評分、範圍判斷）時比 `if-else` 更整潔。
- **類型安全**: Java 的 `switch` 表達式強制要求處理所有 `Enum` 分支，能減少漏寫 Bug。
- **現代風格**: 在 Python 中，`match` 不僅是 `switch`，還能解構元組、列表和字典，功能最為強大。
