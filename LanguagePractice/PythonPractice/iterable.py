"""
可迭代資料型態
字串, 列表, 集合, 字典
"""

for item in {"a": 6, "h": 7, "sss": 44}:
    print(item)

result = max([10, 2, 60, 9])
print(f"max = {result}")

result = max("xyz")
print(f"max words = {result}")

result = max({"x":3, "a":4}) #字典作為iterable看的是 key 不是 value
print(f"max dict = {result}")

result = sorted("cba")
print(f"sorted string = {result}")
"""
教學:
https://www.youtube.com/watch?v=VEQ4UBfLbdc
"""