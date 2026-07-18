在物件導向程式設計中，將物件轉換為字串表示（String Representation）是非常常見的需求。Java 使用 `toString()` 方法來達成此目的。以下為您介紹 Go、Python、JavaScript 中對標的機制及其比較。

### 1. 各語言對標方式介紹

#### Go：實作 `fmt.Stringer` 介面
在 Go 中，並沒有像 Java 那樣的繼承體系。要自定義型別的字串輸出，慣用方式是實作 `fmt.Stringer` 介面，該介面僅包含一個 `String() string` 方法。當使用 `fmt` 套件（如 `fmt.Println` 或 `fmt.Printf` 的 `%v` 格式）輸出該型別時，Go 會自動呼叫此方法。

```go
type Person struct {
    Name string
}

// 實作 String() string 方法
func (p Person) String() string {
    return "Person: " + p.Name
}
```

#### Python：`__str__` 與 `__repr__` 魔術方法
Python 使用「魔術方法」（Magic Methods）來實現此功能。
*   `__str__(self)`：目標是返回一個「對用戶友好」的字串，通常在呼叫 `str(obj)` 或 `print(obj)` 時呼叫。
*   `__repr__(self)`：目標是返回一個「對開發者友好」、通常包含足夠資訊以便重建該物件的字串。在直譯器中直接輸入物件名稱時會呼叫此方法。
建議兩者都實作。

```python
class Person:
    def __init__(self, name):
        self.name = name
    
    def __str__(self):
        return f"Person(name={self.name})" # 用戶友好
    
    def __repr__(self):
        return f"Person('{self.name}')" # 開發者友好，可重建
```

#### JavaScript：覆寫 `toString()` 方法
JavaScript 物件繼承自 `Object.prototype`，後者內建 `toString()` 方法。開發者可以直接在物件上或物件的原型鏈（prototype chain）上覆寫此方法，以改變該物件被轉換為字串時的表現（例如在字串拼接時）。

```javascript
class Person {
    constructor(name) {
        this.name = name;
    }
    
    // 覆寫 toString 方法
    toString() {
        return `Person: ${this.name}`;
    }
}
```

---

### 2. 四種語言綜合比較

| 語言 | 機制 | 關鍵特性 |
| :--- | :--- | :--- |
| **Java** | `toString()` | 基於類別繼承，所有物件預設繼承 `Object.toString()`。 |
| **Go** | `fmt.Stringer` 介面 | 透過介面實現（Duck Typing），非侵入式設計，顯示呼叫 `fmt` 套件時生效。 |
| **Python** | `__str__`, `__repr__` | 魔術方法。區分使用者與開發者視角，設計最細膩。 |
| **JS** | `toString()` | 覆寫原型方法，在物件進行型別轉換（強制轉型）時生效。 |

### 總結
*   **Java 與 JS** 最為相似，都是直接定義或覆寫一個 `toString()` 方法。
*   **Go** 的設計最為輕量且解耦，只要型別具備 `String()` 方法就自動符合條件。
*   **Python** 提供了兩套機制（`str` vs `repr`），在開發調試與終端使用者呈現之間取得了很好的平衡。