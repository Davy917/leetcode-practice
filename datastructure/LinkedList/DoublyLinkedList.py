from typing import Optional
class DoublyLinkedNode:
    def __init__(self, val):
        self.val = val
        self.next: Optional[DoublyLinkedNode] = None
        self.prev: Optional[DoublyLinkedNode] = None

class DoublyLinkedList:
    def __init__(self):
        self._head: Optional[DoublyLinkedNode] = None
        self._tail: Optional[DoublyLinkedNode] = None
        self._size = 0

    def append(self, val: int):
        node = DoublyLinkedNode(val)
        if self._head is None:
            self._head = node
            self._tail = node
        else:
            node.prev = self._tail
            self._tail.next = node
            self._tail = node
        self._size += 1

    def __iter__(self):
        curr = self._head
        while curr:
            yield curr.val
            curr = curr.next

    def __len__(self):
        return self._size

    def __repr__(self):
        values = []
        for val in self:
            values.append(str(val))
        to_print = "<->".join(values)
        return "[" + to_print + "]"
"""
FAQ:
_head, _tail前面都有加_ 這個符號, 是什麼意思??
這是 「慣例上的非公開」 標記（private-ish）。
    _head 表示：這是類別內部實作細節，外部不要直接動
    只是約定，不是強制（外部仍可存取）
    常用在資料結構中，提醒使用者用方法操作，不要直接改欄位
    
_這個符號加在變數前面, 比較像是Java的private
_var 很像 Java private 的「語意」，但不是語法層級的存取限制。
對照如下：
Java private：編譯器層級限制，類別外不能直接存取
Python _var：團隊慣例「請不要直接用」，但技術上仍可存取
如果想要更接近「避免外部直接碰」的效果，Python 會用 __var（前雙底線）做 name mangling，但也不是絕對私有，只是提高誤用門檻。

第二個問題是__xxxx__ 這樣的寫法是什麼意思??
這叫 dunder（double underscore）方法，是 Python 的「魔術方法 / 特殊方法」。
這些不是隨便命名，而是 Python 會在特定情境自動呼叫：
    __init__：建立物件時呼叫（建構子）
    __len__：len(obj) 時呼叫
    __iter__：for x in obj 時呼叫
    __repr__：print(obj) 或 REPL 顯示時用（偏 debug）
    
什麼是yield?
yield介紹
https://www.youtube.com/watch?v=x6MNOSRY5EM&t=68s

迭代器, 生成器
LanguagePractice/PythonPractice/generate.py
LanguagePractice/PythonPractice/iter.py

__iter__ & yield說明文件
LanguagePractice/PythonPractice/iter_yield_explained.md
"""