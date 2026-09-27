### 哨兵型態最大值與最小值表示法比較

在演算法中，我們經常需要使用「無窮大」或「無窮小」作為初始化值或邊界條件。以下為您整理 Java、Python、Go、JavaScript 四種語言的表示方式與注意事項：

#### 1. Java
Java 嚴格區分整數與浮點數類型。

*   **整數 (Integer/Long):** 使用封裝類別的靜態常數。
    *   最大值：`Integer.MAX_VALUE`, `Long.MAX_VALUE`
    *   最小值：`Integer.MIN_VALUE`, `Long.MIN_VALUE`
*   **浮點數 (Double/Float):** 使用 `Double` 類別的靜態常數。
    *   無窮大：`Double.POSITIVE_INFINITY`
    *   無窮小：`Double.NEGATIVE_INFINITY`

> **注意：** 整數的 `MIN_VALUE` 是絕對值最大的負數，並非負無窮。如果需要邏輯上的「無窮」，在浮點數運算中使用 `Double` 類型的無窮大比較適合。

#### 2. Python
Python 的整數（`int`）具有任意精度（Arbitrary Precision），因此沒有固定的最大或最小整數限制。

*   **通用無窮大 (推薦):** `float('inf')` 與 `float('-inf')`。
    *   這在 Python 中非常實用，因為它能與任何數值進行比較運算（`x < float('inf')` 永遠為真）。
*   **模組輔助:** `import math; math.inf`。

> **提示：** 在處理整數演算法時，使用 `float('inf')` 通常能順利與整數進行大小比較，無需擔心轉型問題。

#### 3. Go
Go 語言提供了非常明確的數學常數。

*   **整數 (int):** 使用 `math` 套件。
    *   最大值：`math.MaxInt` (系統架構相關，32位元或64位元)
    *   最小值：`math.MinInt`
*   **浮點數 (float64):**
    *   無窮大：`math.Inf(1)`
    *   無窮小：`math.Inf(-1)`

#### 4. JavaScript
JavaScript 的 `Number` 類型遵循 IEEE 754 標準，處理無窮大與數字邊界時需要特別小心。

*   **無窮大:** 直接使用全域變數 `Infinity` 與 `-Infinity`。
*   **安全範圍 (最常使用):**
    *   最大安全整數：`Number.MAX_SAFE_INTEGER`
    *   最小安全整數：`Number.MIN_SAFE_INTEGER`
*   **浮點數極限 (避坑指南):**
    *   `Number.MAX_VALUE` 是能表示的最大正數。
    *   **重要！** `Number.MIN_VALUE` **不是**最小的負數，而是 **最接近 0 的正數**。若要表示負數邊界，請使用 `-Number.MAX_VALUE` 或 `Number.MIN_SAFE_INTEGER`。

### 總結比較表

| 語言 | 概念上的「無窮」 (Float/通用) | 整數最大值 | 整數最小值 |
| :--- | :--- | :--- | :--- |
| **Java** | `Double.POSITIVE_INFINITY` | `Integer.MAX_VALUE` | `Integer.MIN_VALUE` |
| **Python** | `float('inf')` | N/A (任意精度) | N/A (任意精度) |
| **Go** | `math.Inf(1)` | `math.MaxInt` | `math.MinInt` |
| **JS** | `Infinity` | `Number.MAX_SAFE_INTEGER` | `Number.MIN_SAFE_INTEGER` |

在撰寫演算法（如二元搜尋樹的範圍檢查）時，**Python 的 `float('inf')`** 與 **JavaScript 的 `Infinity`** 是最方便的選擇，因為它們不需要考慮底層整數的位元寬限制。