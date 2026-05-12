#一般寫法
square = lambda x : x * x
list = []
for i in range(1, 11):
    list.append(square(i))
print(list)

#列表推倒式
#[元素 for 變數 in 可迭代物件]
squares = [i * i for i in range(1, 11)]
print(squares)

#列表推倒式 + lambda
double_val = lambda x : x * 2
result = [double_val(i) for i in range(1, 11)]
print(result)

#列表推倒式 + if - else
grades = [100, 99, 66, 80, 46, 29, 74]
passed_grades = [grade for grade in grades if grade >= 60]
print(passed_grades)
"""
教學:
https://www.youtube.com/watch?v=pGhMxGZYRPU

執行方式
python3.12 LanguagePractice/PythonPractice/list_comprehension.py
"""