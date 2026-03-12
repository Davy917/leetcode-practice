from typing import Optional
class ListNode:
    def __init__(self, name: str, prev , next):
        self.channel_name = name
        self.prev = prev
        self.next = next

class BrowserHistory:

    def __init__(self, homepage: str):

        self.homepage = ListNode(homepage, None, None)
        self.lastpage = self.homepage
        self.cur = self.lastpage
        print("homepage = ", self.homepage.channel_name)

    def visit(self, url: str) -> None:
        newNode = ListNode(url, None, None)
        newNode.prev = self.cur
        self.cur.next = newNode
        self.cur = newNode
        self.lastpage = self.cur
        print("lastpage = ", self.lastpage.channel_name)
        
    def back(self, steps: int) -> str:
        for i in range (0, steps):
            if self.cur.prev is None:
                print("You can't move anymore, you are at ", self.cur.channel_name)
                return self.cur.channel_name
            self.cur = self.cur.prev
        print("cur = ", self.cur.channel_name)
        return self.cur.channel_name

    def forward(self, steps: int) -> str:
        for i in range (0, steps):
            if self.cur.next is None:
                print("You can't move anymore, you are at ", self.cur.channel_name)
                return self.cur.channel_name
            self.cur = self.cur.next
        print("cur = ", self.cur.channel_name)
        return self.cur.channel_name
    
    def printList(self, head : Optional[ListNode]):
        if head is None:
            return
        mainList = []
        mainList.append(head.channel_name)
        visitor = head
        while visitor.next is not None:
            visitor = visitor.next
            mainList.append(visitor.channel_name)
        print(mainList)

if __name__ == "__main__":
    browserHistory = BrowserHistory("leetcode.com")
    browserHistory.visit("google.com");       # 你原本在浏览 "leetcode.com" 。访问 "google.com"
    browserHistory.visit("facebook.com");     # 你原本在浏览 "google.com" 。访问 "facebook.com"
    browserHistory.visit("youtube.com");      # 你原本在浏览 "facebook.com" 。访问 "youtube.com"
    browserHistory.back(1);                   # 你原本在浏览 "youtube.com" ，后退到 "facebook.com" 并返回 "facebook.com"
    browserHistory.back(1);                   # 你原本在浏览 "facebook.com" ，后退到 "google.com" 并返回 "google.com"
    browserHistory.forward(1);                # 你原本在浏览 "google.com" ，前进到 "facebook.com" 并返回 "facebook.com"
    browserHistory.visit("linkedin.com");     # 你原本在浏览 "facebook.com" 。 访问 "linkedin.com"
    browserHistory.forward(2);                # 你原本在浏览 "linkedin.com" ，你无法前进任何步数。
    browserHistory.back(2);                   # 你原本在浏览 "linkedin.com" ，后退两步依次先到 "facebook.com" ，然后到 "google.com" ，并返回 "google.com"
    browserHistory.back(7);                   # 你原本在浏览 "google.com"， 你只能后退一步到 "leetcode.com" ，并返回 "leetcode.com"
    browserHistory.printList(browserHistory.homepage)