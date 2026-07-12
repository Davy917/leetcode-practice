require()（CommonJS 規範）與 import（ES6 模組規範）最核心的差異在於執行時間點與載入方式。
------------------------------
## 核心差異對比## 1. 執行時間與機制

* require() 屬於動態同步載入：在程式碼「執行到該行」時才去讀取檔案。
* import 屬於靜態非同步編譯：在程式碼「執行前」的編譯階段就先解析並建立模組關聯。

## 2. 語法結構

* require() 可以在任何地方呼叫：例如放入 if 判斷式中，實現條件載入。
* import 必須寫在檔案最頂層：不能放在 function 或 if 區塊內（除非使用動態 import() 函式）。

## 3. 資料傳遞機制

* require() 複製（Copy）值：匯出的是值的複本。模組內部後續修改該值，外部不會更新。
* import 綁定（Binding）參照：匯出的是唯讀的記憶體參照。模組內部修改該值，外部會同步更新。

------------------------------
## 語法對照表

| 功能 | require() 語法 (CommonJS) | import 語法 (ES6) |
|---|---|---|
| 預設匯出 | module.exports = myFunction; | export default myFunction; |
| 預設匯入 | const myFunc = require('./file'); | import myFunc from './file'; |
| 具名匯出 | exports.myVar = 123; | export const myVar = 123; |
| 具名匯入 | const { myVar } = require('./file'); | import { myVar } from './file'; |

------------------------------
## 該選用哪一個？

* Node.js 舊專案：預設使用 require()。
* 前端框架（Vue, React 等）或新版 Node.js 專案：強烈建議使用 import。它是現代 JavaScript 的標準規範，並支援 Tree Shaking（自動刪除未使用的程式碼以縮減檔案大小）。

若要將 `datastructure/Tree/TreeNode.js` 匯入並在 `094-inorder-traversal/solution.js` 中使用，您需要依照 Node.js 的 CommonJS 模組化規範來處理。

以下是具體的步驟：

### 1. 在 `datastructure/Tree/TreeNode.js` 中匯出 `TreeNode`
您需要在該檔案的末尾添加 `module.exports`，讓其他檔案可以存取此類別。請在檔案的最後一行加入：

```javascript
module.exports = TreeNode;
```

---

### 2. 在 `094-inorder-traversal/solution.js` 中匯入 `TreeNode`
接著，在 `solution.js` 的檔案最上方，使用 `require` 來引入剛才匯出的模組。因為 `solution.js` 與 `datastructure` 資料夾位於同層目錄，您需要使用相對路徑：

```javascript
const TreeNode = require('../datastructure/Tree/TreeNode');

/**
 * @param {TreeNode} root
 * @return {number[]}
 */
var inorderTraversal = function(root) {
    // 您的程式碼實作
};
```

### 重要說明
*   **相對路徑**：`../` 的作用是從 `094-inorder-traversal` 資料夾往上一層回到專案根目錄，再正確地路徑指引至 `datastructure/Tree/TreeNode.js`。
*   **模組系統**：上述方案使用的是 Node.js 預設的 CommonJS 系統。如果您的專案有在 `package.json` 設定 `"type": "module"`，則必須使用 ES Modules 的語法（即 `export` 與 `import`），但若無特殊設定，使用上述的 CommonJS 寫法即可。
