這四種語言對於「擴展運算子」（Spread/Rest Operators）的支援與實作方式差異很大。以下為您整理各語言的對應機制。

### ✅ JavaScript (Spread/Rest Operator)

JavaScript 擁有最直接且功能強大的擴展運算子 `...`。

1.  **擴展 (Spread) - 將陣列或物件拆解:**
    ```javascript
    // 陣列展開
    const arr1 = [1, 2];
    const arr2 = [...arr1, 3, 4]; // [1, 2, 3, 4]

    // 物件展開
    const obj1 = { a: 1 };
    const obj2 = { ...obj1, b: 2 }; // { a: 1, b: 2 }
    ```

2.  **其餘參數 (Rest) - 將參數收集為陣列:**
    ```javascript
    function sum(...numbers) {
      return numbers.reduce((a, b) => a + b, 0);
    }
    sum(1, 2, 3); // 6
    ```

---

### ✅ Go (Variadic Functions / Slices)

Go 沒有類似 JS 的「物件擴展」，但對於陣列/切片（Slice）有「可變參數」（Variadic）機制。

1.  **可變參數 (Variadic Parameters):**
    在函式簽名中使用 `...` 來接收多個參數。
    ```go
    func sum(numbers ...int) int {
        total := 0
        for _, n := range numbers {
            total += n
        }
        return total
    }
    ```

2.  **拆解切片傳遞:**
    若要將現有的 slice 傳入可變參數函式，必須在 slice 後加上 `...`。
    ```go
    nums := []int{1, 2, 3}
    sum(nums...) // 拆解 nums 為個別參數傳入
    ```

---

### ✅ Python (Unpacking / Args & Kwargs)

Python 使用 `*` 和 `**` 來處理類似的拆解與收集需求。

1.  **拆解 (Unpacking):**
    ```python
    # 列表拆解
    list1 = [1, 2]
    list2 = [*list1, 3, 4] # [1, 2, 3, 4]

    # 字典拆解
    dict1 = {'a': 1}
    dict2 = {**dict1, 'b': 2} # {'a': 1, 'b': 2}
    ```

2.  **收集 (Args & Kwargs):**
    ```python
    def func(*args, **kwargs):
        print(args)   # 元組 (1, 2, 3)
        print(kwargs) # 字典 {'name': 'Alice'}

    func(1, 2, 3, name='Alice')
    ```

---

### ✅ Java (Varargs)

Java 對擴展運算子的支援最少，僅有「可變參數」（Varargs）功能。

1.  **可變參數 (Varargs):**
    在方法參數中使用 `類型...`。在方法內部，該參數會被當作陣列（Array）處理。
    ```java
    public void printNumbers(int... numbers) {
        for (int n : numbers) {
            System.out.println(n);
        }
    }
    
    // 呼叫
    printNumbers(1, 2, 3);
    ```
    *注意：Java 不支援類似 JS 或 Python 的陣列/物件字面量展開語法（如 `[...arr]` 或 `{...obj}`），必須手動處理陣列合併或物件複製。*

---

### 💡 總結比較

| 語言 | 機制名稱 | 主要語法 |
| :--- | :--- | :--- |
| **JS** | Spread / Rest | `...` |
| **Go** | Variadic | `...` (僅用於函式參數或拆解 slice) |
| **Python**| Unpacking / Args | `*` (列表), `**` (字典) |
| **Java** | Varargs | `...` (僅用於函式參數，內部視為陣列) |
