def quickSort(alist):
    quickSortHelper(alist,0,len(alist)-1)


def quickSortHelper(alist, first, last):
    if first >= last:
        return

    splitpoint = partition(alist, first, last)
    quickSortHelper(alist, first, splitpoint-1)
    quickSortHelper(alist, splitpoint+1, last)


def partition(alist, first, last):
    pivotvalue = alist[last]

    leftmark = first
    rightmark = last - 1

    done = False
    while not done:
        while leftmark <= rightmark and alist[leftmark] <= pivotvalue:
            leftmark = leftmark + 1

        while leftmark <= rightmark and alist[rightmark] >= pivotvalue:
            rightmark = rightmark -1

        if rightmark < leftmark:
            done = True
        else:
            temp = alist[leftmark]
            alist[leftmark] = alist[rightmark]
            alist[rightmark] = temp

    temp = alist[last]
    alist[last] = alist[leftmark]
    alist[leftmark] = temp

    return leftmark

alist = [54,26,93,17,77,31,44,55,20,44]
quickSort(alist)
print(alist)
