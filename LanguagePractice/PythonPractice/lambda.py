#一般函式
def normal(n1, n2):
    return n1 + n2
def normal_2():
    return 5 * 2
def normal_3(*ns):
    return max(ns) * 2

#lambda aka 匿名函式
#lambda 參數列表:回傳值

lambda_example = lambda n1, n2: n1 + n2
lambda_example2 = lambda: 5 * 2
lambda_example3 = lambda *ns:max(ns) * 2

print(normal(3, 4))
print(normal_2())
print(normal_3(-1, -10, 0, 2))
print(lambda_example(3, 4))
print(lambda_example2())
print(lambda_example3(-1, -10, 0, 2))

#搭配filter, 教學影片12:00
#語法 filter(函式, 列表)
data = [1, 5, -2, 10, -5]
filter_data = filter(lambda n:n>0, data)
filter_list = list(filter_data)
print(filter_list)

#搭配map, 教學影片15:30
#語法 filter(函式, 列表)
data = [1, 5, -2, 10, -5]
map_data = map(lambda n:n*2, data)
map_list = list(map_data)
print(map_list)

"""
影片教學:
https://www.youtube.com/watch?v=AToQgdOQV2c

執行方式:
python3.12 LanguagePractice/PythonPractice/lambda.py
"""