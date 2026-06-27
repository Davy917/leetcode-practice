"""
__init__.py 介紹請看
LanguagePractice/PythonPractice/__init__.md
"""
# 當有人讀取 Tree 時，自動把 TreeNode 類別拉進來
# 關聯檔案 datastructure/__init__.py
from .TreeNode import TreeNode
from .TreeDebugger import TreeDebugger