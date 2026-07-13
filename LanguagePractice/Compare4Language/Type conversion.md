以下為您整理 Java、JavaScript、Go 和 Python 四種語言中常見的型別轉換方式。

### ✅ Java (強型別、靜態型別)

Java 的型別轉換分為**自動轉換 (隱式)** 和 **強制轉換 (顯式)**。

1.  **自動轉換 (Implicit / Widening):**
    由小範圍轉向大範圍 (例如 `int` 轉 `double`)，編譯器會自動處理。
    ```java
    int i = 10;
    double d = i; // 自動轉換
    ```

2.  **強制轉換 (Explicit / Narrowing):**
    由大範圍轉向小範圍，或不相容型別，需使用 `(型別)` 語法。
    ```java
    double d = 10.5;
    int i = (int) d; // 強制轉換，會捨去小數點
    ```

3.  **字串與數值轉換 (Wrapper Classes):**
    使用包裝類別的方法。
    ```java
    // 數值轉字串
    String s = String.valueOf(10);
    // 字串轉數值
    int i = Integer.parseInt("10");
    double d = Double.parseDouble("10.5");
    ```

---

### ✅ JavaScript (弱型別、動態型別)

JS 具有極高的型別彈性，常發生隱式轉換，但建議使用顯式轉換以提高代碼可讀性。

1.  **顯式轉換 (Explicit):**
    使用全域函數或物件方法。
    ```javascript
    // 轉數字
    let n1 = Number("123");  // 123
    let n2 = parseInt("123.45"); // 123
    let n3 = parseFloat("123.45"); // 123.45
    // 轉字串
    let s1 = String(123);
    let s2 = (123).toString();
    // 轉布林
    let b = Boolean(1); // true
    ```

2.  **隱式轉換 (Implicit):**
    透過運算子觸發，需謹慎使用。
    ```javascript
    let n = "10" - 5; // 5 (字串減數字，字串隱式轉為數字)
    let s = "Hello" + 10; // "Hello10" (數字隱式轉為字串)
    ```

---

### ✅ Go (強型別、靜態型別)

型別轉換 vs 型別斷言

508-find-frequent-tree-sum/solution.go

Go 的型別轉換非常嚴格，不支援隱式轉換，所有轉換都必須顯式進行。

1.  **基礎型別轉換:**
    使用 `型別(值)` 的語法。
    ```go
    var f float64 = 3.14
    var i int = int(f) // 顯式轉換
    ```

2.  **複雜型別與字串轉換 (`strconv` 套件):**
    對於字串與數值的轉換，Go 使用標準庫 `strconv`。
    ```go
    import "strconv"

    // 數值轉字串
    s1 := strconv.Itoa(100) // 整數轉字串
    s2 := strconv.FormatFloat(3.14, 'f', -1, 64)

    // 字串轉數值
    i, err := strconv.Atoi("100") // 字串轉整數
    f, err := strconv.ParseFloat("3.14", 64)
    ```

---

### ✅ Python (強型別、動態型別)

Python 使用類別建構子來進行顯式型別轉換，語法簡單明瞭。

1.  **顯式轉換:**
    ```python
    # 轉整數
    i = int("10")
    # 轉浮點數
    f = float("3.14")
    # 轉字串
    s = str(100)
    # 轉列表/元組
    l = list((1, 2, 3))
    t = tuple([1, 2, 3])
    ```

---

### 💡 總結比較

| 語言 | 型別轉換特性 | 關鍵語法 |
| :--- | :--- | :--- |
| **Java** | 強型別，明確區分自動與強制 | `(type)var`, `Integer.parseInt()` |
| **JS** | 弱型別，彈性高但容易有怪異行為 | `Number()`, `String()`, `parseInt()` |
| **Go** | 強型別，**絕對嚴格**，無隱式轉換 | `Type(var)`, `strconv` 套件 |
| **Python**| 強型別，透過建構子轉換 | `int()`, `float()`, `str()` |
