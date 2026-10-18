這四種語言在「建構子」的設計哲學上各有不同。以下為你整理的對照表與範例：

### 1. Java (傳統 OOP)
Java 的建構子名稱必須與**類別名稱完全相同**，且沒有回傳值型別。支援多載（Overloading）。

```java
public class Person {
    String name;
    // 建構子名稱與 Class 相同
    public Person(String name) {
        this.name = name;
    }
}
Person p = new Person("Tom");
```

---

### 2. Python (明確的 `self`)
Python 使用特殊的實例方法 `__init__` 作為建構子。第一個參數永遠是 `self`（代表實例本身）。

```python
class Person:
    def __init__(self, name):
        self.name = name # 初始化屬性

p = Person("Tom")
```

---

### 3. Go (工廠函式)
Go 沒有類別（Class），只有結構體（Struct）。它**沒有內建的建構子關鍵字**，慣例是寫一個 `New...` 開頭的工廠函式。

```go
type Person struct {
    Name string
}
// 慣例寫法：New + 結構名稱
func NewPerson(name string) *Person {
    return &Person{Name: name}
}

p := NewPerson("Tom")
```

---

### 4. JavaScript (分為 ES5 與 ES6)

#### ES5：構造函式 (Constructor Function)
在 ES5 時代，建構子就是一個首字母大寫的普通函式，配合 `new` 關鍵字使用。

```javascript
function Person(name) {
  this.name = name;
}
// 方法通常掛在 prototype 上
Person.prototype.sayHi = function() { console.log(this.name); };

var p = new Person("Tom");
```

#### ES6：`class` 語法糖
ES6 引入了 `class` 關鍵字，並規定建構子的名稱固定叫 `constructor`。

```javascript
class Person {
  constructor(name) {
    this.name = name;
  }
  sayHi() { console.log(this.name); }
}

const p = new Person("Tom");
```

---

### 綜合對比總結

| 語言 | 建構子名稱 | 關鍵字/特性 |
| :--- | :--- | :--- |
| **Java** | 與類別同名 | 支援多載 (Overloading) |
| **Python** | `__init__` | 必須帶 `self` 參數 |
| **Go** | 自訂 (如 `NewPerson`) | 沒有 Class，回傳結構指標 |
| **JS (ES5)** | 與函式同名 | 搭配 `prototype` 使用 |
| **JS (ES6)** | `constructor` | ES5 的語法糖，結構更清晰 |

在你的專案中，`LanguagePractice/JSPractice/MDLibrary/class and Prototype.md` 完整記錄了 JS 的演進細節；而 `GoPractice/` 下的範例則展現了 Go 如何利用 `struct` 取代傳統的類別結構。