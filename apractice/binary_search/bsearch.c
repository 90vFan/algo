#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

int mybsearch(int a[], int size, int value)
{
    int mid = 0;
    int left = 0;
    int right = size - 1;

    while (left <= right)
    {
        /* 房子数量太大时，left+right 数据翻转 */
        mid = left + ((right - left)>>1);

        if (a[mid] == value)
        {
            return mid;
        }
        else if (a[mid] < value)
        {
            left = mid + 1;
        }
        else
        {
            right = mid - 1;
        }
    }

    return -1;
}

int helper(int a[], int left, int right, int value)
{
    int mid = 0;

    if (left > right)
    {
        return -1;
    }

    mid = left + ((right - left)>>1);
    if (a[mid] == value)
    {
        return mid;
    }
    else if (a[mid] < value)
    {
        return helper(a, mid + 1, right, value);
    }
    else
    {
        return helper(a, left, mid - 1, value);
    }

    return -1;
}

int mybsearch2(int a[], int size, int value)
{
    return helper(a, 0, size-1, value);
}

int main()
{
	int a[12] = {5,6,8,8,8,9,10,11,23,42,53,123};
    int data = 0;
	int res = 0;

	printf("\r\n输入一个整数");
	scanf("%d",&data);
    res = mybsearch(a,12,data);
	printf("data[%d] %s 在数据中，下标是%d\n", data, (res != -1) ? "":"不",res);

    printf("\r\n输入一个整数");
	scanf("%d",&data);
    res = mybsearch2(a,12,data);
	printf("data[%d] %s 在数据中，下标是%d\n", data, (res != -1) ? "":"不",res);
	return;
}
