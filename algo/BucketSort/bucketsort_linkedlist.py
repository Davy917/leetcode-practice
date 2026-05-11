from datastructure.LinkedList.doubly_linked_list import DoublyLinkedList
from InsertSort.insertsort import InsertSort
class BucketSort:
    def __init__(self):
        self.bucketAmount = 10
    def bucket_sort_linkedlist(self, arr: list[int]) -> list[int]:
        max_num = arr[0]
        min_num = arr[0]
        for val in arr:
            if val > max_num:
                max_num = val
            elif val < min_num:
                min_num = val
        true_range = max_num - min_num
        if true_range == 0:
            return arr[:]

        gap = max((1, (true_range + 1) // self.bucketAmount)) #gap只能是整數, 而且必須大於0
        buckets: dict[int, DoublyLinkedList] = {}

        for val in arr:
            index = (val - min_num) // gap
            if index >= self.bucketAmount:
                index = self.bucketAmount - 1
            if index not in buckets:
                buckets[index] = DoublyLinkedList()
            buckets[index].append(val)
        print(buckets)

        result: list[int] = []
        for key in range(self.bucketAmount):
            if key not in buckets:
                continue
            print(buckets[key]) #buckets[key] 類型是<class 'datastructure.LinkedList.DoublyLinkedList.DoublyLinkedList'>
            sorted_bucket = InsertSort.sort(list(buckets[key]))
            result.extend(sorted_bucket)
        return result
if __name__ == "__main__":
    nums = [55, 12, 80, 22, 14, 60, 18, 90, 16, 40, 5, 70, 30, 17, 0, 99]
    bs = BucketSort()
    print(bs.bucket_sort_linkedlist(nums))

"""
gap aka bucketWidth
bucket[0] 0 ~ 9
bucket[1] 10 ~ 19
bucket[2] 20 ~ 29
bucket[3] 30 ~ 39
bucket[4] 40 ~ 49
bucket[5] 50 ~ 59
bucket[6] 60 ~ 69
bucket[7] 70 ~ 79
bucket[8] 80 ~ 89
bucket[9] 90 ~ 99
"""