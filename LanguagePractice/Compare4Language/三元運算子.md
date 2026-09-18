# 四種語言的三元運算子比較

這四種語言在「三元運算子（Ternary Operator）」或「條件表達式（Conditional Expression）」的語法上很有代表性：

- Java 與 JavaScript：語法幾乎相同
- Python：使用更接近英文語序的條件表達式
- Go：刻意不提供三元運算子

---

## 1. Java

Java 使用經典的 `? :` 語法。

語法：`條件 ? 成立時的值 : 不成立時的值`

```java
int age = 20;
String status = (age >= 18) ? "成年" : "未成年";
System.out.println(status); // 輸出: 成年
```

## 2. JavaScript (JS)

JavaScript 與 Java 相同，也是使用 `? :`。

語法：`條件 ? 成立時的值 : 不成立時的值`

```javascript
const age = 20;
const status = age >= 18 ? "成年" : "未成年";
console.log(status); // 輸出: 成年
```

## 3. Python

Python 沒有 `? :`，而是使用條件表達式（Conditional Expression）。

語法：`成立時的值 if 條件 else 不成立時的值`

```python
age = 20
status = "成年" if age >= 18 else "未成年"
print(status)  # 輸出: 成年
```

> 注意：Python 的順序是「結果在前、條件在後」，和 Java/JS 相反。

## 4. Go (Golang)

Go **不支援**三元運算子。這是語言設計上的刻意選擇，目標是保持程式風格一致與可讀性，避免過度複雜的單行表達式。

在 Go 中，請使用標準 `if-else`：

```go
package main

import "fmt"

func main() {
    age := 20
    var status string

    if age >= 18 {
        status = "成年"
    } else {
        status = "未成年"
    }

    fmt.Println(status) // 輸出: 成年
}
```

---

## 快速總結對照表

| 語言 | 語法結構 | 範例 |
| --- | --- | --- |
| Java | `條件 ? A : B` | `status = age >= 18 ? "成年" : "未成年";` |
| JavaScript | `條件 ? A : B` | `const status = age >= 18 ? "成年" : "未成年";` |
| Python | `A if 條件 else B` | `status = "成年" if age >= 18 else "未成年"` |
| Go | 不支援 | 必須使用 `if-else` 寫成多行 |