# 🎯 LeetCode Solutions

我的 LeetCode 刷題記錄與解答

## 📚 支援語言
- ☕ Java
- 🐍 Python

## 📁 專案結構
每題都有獨立資料夾，按題號和題目名稱分類：
003-length-of-longest-substring/ ├── Solution1122.java └── solution.py

020-is-valid/ ├── Solution1122.java └── solution.py

## 📊 統計
- 總題數：60+ 題
- 涵蓋 Easy、Medium、Hard 難度
- 每題提供 Java 和/或 Python 實作

## 💡 命名規則
- 資料夾：`{題號}-{題目名稱}`（例如：`003-length-of-longest-substring/`）
- Java 文件：`Solution1122.java`
- Python 文件：`solution.py`

## 📂 額外資料夾
- `practice/` - 練習和實作的數據結構（HashMap, HashSet 等）

---

## ⚙️ 常見問題與解決方案

### 問題一：橘色咖啡杯圖示（Source Root 未設定）

#### 問題描述
手動創建新資料夾並添加 Java 文件後，發現：
- ❌ Java 文件圖示顯示為**橘色咖啡杯**（應為藍色）
- ❌ 可能出現編譯錯誤
- ❌ IDE 無法正確識別和運行代碼

#### 🔍 Root Cause（根本原因）
在 IntelliJ IDEA 中，手動創建的資料夾**不會自動被標記為 Source Root**，導致 IDE 不認為該資料夾包含源碼。

#### ✅ 解決方案

**方法一：使用 IntelliJ IDEA 介面（推薦）**
1. 在新建的資料夾上**右鍵點擊**
2. 選擇 `Mark Directory as` → `Sources Root`
3. ✅ 圖示應變為**藍色資料夾**，Java 文件變為**藍色咖啡杯**

**方法二：手動編輯 `.iml` 文件**
1. 打開 `leetcode-solutions.iml` 文件
2. 在 `<content url="file://$MODULE_DIR$">` 區塊中新增：
   ```xml
   <sourceFolder url="file://$MODULE_DIR$/XXX-problem-name" isTestSource="false" />
   ```
3. 保存後，在 IDE 中右鍵點擊專案 → `Reload from Disk`

**正確的新增題目步驟：**
1. 在專案根目錄上右鍵 → `New` → `Directory`
2. 輸入資料夾名稱（例如：`234-is-palindrome`）
3. 在新建的資料夾上右鍵 → `Mark Directory as` → `Sources Root`
4. 在該資料夾中創建 `Solution1122.java` 文件

**如何確認設定正確：**
- ✅ 資料夾圖示應為**藍色**
- ✅ Java 文件圖示應為**藍色咖啡杯**
- ✅ `.iml` 文件中包含該資料夾的 `<sourceFolder>` 標籤

---

### 問題二：重複類名衝突

### 問題描述
即使每題都在不同的資料夾中，仍可能出現以下錯誤：
```
java: duplicate class: ListNode
java: duplicate class: Solution1122
在文件 'XXX.java' 中找到重复类
```

### 🔍 Root Cause（根本原因）
在 IntelliJ IDEA 中，**所有被標記為 Source Root 的資料夾都共享同一個默認包（default package）**！

雖然每題在不同資料夾，但在 Java 編譯器眼中：
```
leetcode-solutions/
├── 203-remove-elements/     [Source Root] ─┐
├── 234-is-palindrome/       [Source Root]  ├─→ 都在同一個 default package！
├── 707-my-linked-list/      [Source Root] ─┘
```

因此，`ListNode` 和 `Solution1122` 在不同資料夾中，Java 仍認為它們在**同一個包**中 → **類名衝突**！

### ✅ 解決方案

#### **方法一：為每個題目添加 package 聲明（最推薦）**

在每個 Java 文件的**第一行**添加唯一的包名：

```java
// 234-is-palindrome/Solution1122.java
package p234;  // ← 添加這行

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class Solution1122 {
    // ...
}
```

**包名建議格式**：`p{題號}` （例如：`p234`, `p707`, `p203`）

#### **方法二：使用內部類（適用於 helper 類）**

將 `ListNode` 作為**內部靜態類**定義：

```java
public class Solution1122 {
    // 內部類，不會與其他文件衝突
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
    
    ListNode head;
    // ...其他方法
}
```

#### **方法三：使用唯一的類名**

給每個類加上題號後綴：

```java
class ListNode234 {  // ← 加上題號
    // ...
}

public class Solution234 {  // ← 加上題號
    // ...
}
```

### 📋 推薦做法總結

| 情況 | 推薦方法 | 說明 |
|------|---------|------|
| 新建題目 | **方法一**（package） | 從一開始就規範化 |
| helper 類衝突 | **方法二**（內部類） | 快速修復，不改動太多 |
| 整體重構 | **方法一**（package） | 統一項目結構 |

## 🚀 持續更新中...

