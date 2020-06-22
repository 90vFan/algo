#include <stdio.h>
#include <stdlib.h>
#include <string.h>


/*
 *  爬楼梯问题，一次允许爬1步或2步
 *            _  F(n)
 *          _|         _   top 回首，最后一次爬1步
 *        _| F(n-1) + |     1 step
 *      _|
 *    _|                 _ top 回首，最后一次爬2步
 *  _|       F(n-2) +  _|   2 step
 *                    |
 *
 *  F(n) = F(n-1) + F(n-2)   最优子结构，状态转移方程
 *  n = 1, F(1) = 1          边界
 *  n = 2, F(2) = 2
 */


/*
 * 爬楼梯问题，解决重复计算，采用数据采样方法
 * value: list, cache of caculated value[n]
 */
int climb(int n, int *value)
{
    if (value[n] != 0)
    {
        return value[n];
    }

    value[n] = climb(n - 1, value) + climb(n - 2, value);

    return value[n];
}

int climbStairs(int n)
{
    int *value = NULL;
    int res = 0;

    value = (int *)malloc(sizeof(int) * (n+1));
    if (value == NULL)
    {
        return -1;
    }

    memset(value, 0, sizeof(int) * (n+1));

    value[0] = 0;
    value[1] = 1;
    value[2] = 2;

    res = climb(n, value);
    free(value);

    return res;
}

int main()
{
	printf("\r\nTotal %d, %d", 1,climbStairs(1));
	printf("\r\nTotal %d, %d", 2,climbStairs(2));
	printf("\r\nTotal %d, %d", 3,climbStairs(3));
	printf("\r\nTotal %d, %d", 4,climbStairs(4));
	printf("\r\nTotal %d, %d", 5,climbStairs(5));
	printf("\r\nTotal %d, %d", 6,climbStairs(6));
	printf("\r\nTotal %d, %d", 7,climbStairs(7));
	return 0;
}
