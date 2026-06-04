str1 = "hello"
str2= "world"


#__add__
new_str = str1 + str2
print(new_str)
new_str = str1.__add__(str2)
print(new_str)

#__len__
n = new_str.__len__()
print(n)

class counter:
    def __init__(self):
        self.value = 1
    def count_up(self):
        self.value += 1
    def count_down(self):
        self.value -= 1
    #__str__ 使得當前物件, 使用者友善的打印
    def __str__(self):
        return f"count = {self.value}"
    def __add__(self, other):
        if isinstance(other, counter): #isinstance() 是 Python 的内建函数，主要用于检查一个对象是否属于特定的数据类型。它不仅支持基础类型检查，还会考虑面向对象的继承关系
            return self.value + other.value
        raise Exception("Invalid type")
    
class car:
    def __init__(self, make, model, year):
        self.make = make
        self.model = model
        self.year = year
        
    def __str__(self):
        return f"{self.year} {self.make} {self.model}"
    
    # representation method 開發者友善的打印, 常用來debug
    def __repr__(self):
        return f"car(make = '{self.make}', model = '{self.model}, year = '{self.year}')"

class countdown:
    def __init__(self, start):
        self.current = start
    def __iter__(self):
        return self
    def __next__(self):
        if self.current > 0:
            value = self.current
            self.current -= 1
            return value
        else:
            raise StopIteration
    
if __name__ == "__main__":
    counter1 = counter()
    counter2 = counter()
    counter1.count_up()
    counter2.count_up()
    # print(counter1, counter2)
    # print(counter1+2)

    my_car = car('Toyota', 'Corolla', '2025')
    # print(str(my_car))
    # print(repr(my_car))

    for num in countdown(5):
        print(num)

"""
教學影片:
12:50 之後的 code 沒有打上來, 後半部認真看影片即可
https://www.youtube.com/watch?v=qqp6QN20CpE&list=LL&index=1&t=2s
"""