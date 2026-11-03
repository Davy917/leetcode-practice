def test():
    print("stage1")
    yield 5
    print("stage2")
    yield 10
gen = test()
print(gen)
for val in gen:
    print(val)
"""
17:05開始
https://www.youtube.com/watch?v=x6MNOSRY5EM&t=68s
"""

def generateEven(max: int):
    num = 2
    while num <= max:
        yield num
        num += 2
evenGenerator = generateEven(10)
print("what is evenGenerator", evenGenerator)
for val in evenGenerator:
    print(f"{val} ")

"""
23:50開始
https://www.youtube.com/watch?v=x6MNOSRY5EM&t=68s
"""
"""
執行方式
python3.12 LanguagePractice/PythonPractice/generate.py
"""