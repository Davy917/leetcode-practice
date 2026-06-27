"""
這邊是在介紹python中的__init__.py該如何使用, 底下全部都是拿 datastructure/Tree 當作範例

在 Python 3.3 之前，任何資料夾如果沒有 __init__.py，Python 會完全不認它是一個「套件（Package）」，你就無法從中匯入任何模組。  

但是從 Python 3.3 開始，Python 引入了 「命名空間套件（Namespace Packages）」（PEP 420）的概念。  

只要你是用 Python 3.3 以上的版本，即使資料夾裡沒有 __init__.py，Python 也會自動將它視為一個套件。
這就是為什麼你現在不寫它，程式依然能跑得好好的。

那為什麼我們現在還要寫 __init__.py？
雖然它現在是「選配」，但在實際開發（特別是寫開放原始碼套件、大型專案）中，__init__.py 依然扮演著非常關鍵的角色。主要有以下幾個原因：  

原因一：簡化匯入路徑（最實用的功能！✨）
這剛好可以解決你剛才遇到的問題！ 如果你不加 __init__.py，別人要用你的程式碼，就必須寫得很長：  
from Tree.TreeNode import TreeNode

如果你在 Tree 資料夾下建立一個 __init__.py，並在裡面寫上一行程式碼：  
# Tree/__init__.py 的內容：
from .TreeNode import TreeNode

這行程式碼的意思是：「當有人匯入 Tree 時，自動幫他把 TreeNode 類別也拉進來。」  

這樣一來，你在主程式（main.py）中，就可以寫出非常乾淨、直覺的匯入方式了：  

# 現在這樣寫也可以通了！
from Tree import TreeNode  
這對於設計 API、讓其他開發者方便使用你的套件非常重要。  

原因二：進行套件的初始化設定
__init__.py 是這個套件被匯入時，第一個會被執行的檔案。 如果你希望使用者在 import Tree 的時候，自動執行某些初始化工作，就可以寫在裡面。例如：  
    1.設定日誌（Logging）
    2.檢查系統環境或依賴套件版本
    3.初始化全域變數


原因三：控制「萬用匯入」 from Tree import *
如果你不希望使用者用 import * 把你資料夾下所有亂七八糟的暫存檔或私有模組都匯入進來，你可以在 __init__.py 中定義 __all__ 變數（白名單）：  

# Tree/__init__.py 的內容：
__all__ = ['TreeNode'] # 限制只有 TreeNode 會被匯入

當使用者寫 from Tree import * 時，就只會匯入 TreeNode，這能保持命名空間的乾淨。  
"""