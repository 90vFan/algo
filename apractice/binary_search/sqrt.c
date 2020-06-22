#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <assert.h>

#define E 0.000001

double sqrt(double num)
{
    double start = 1.0;
    double end = num;
    double mid = 0.0;
    double square = 0.0;

    while (1)
    {
        mid = (start + end) / 2;
        square = mid * mid;
        if (((square - num) <= E) && ((square - num) >= -E))
        {
            return mid;
        }

        if ((square - num) > E)
        {
            end = mid;
        }
        else
        {
            start = mid;
        }
    }

    return 0;
}

int main()
{
    double num = 4.0;

    // scanf("%1f", &num);
    printf("\r\n num %1f 的平方根是: %1f\n", num, sqrt(num));

    return 0;
}
