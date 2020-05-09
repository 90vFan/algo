#ifndef ARRAY_STACJ_H
#define ARRAY_STACJ_H

typedef struct _array_stack
{
    int size;  /* size of stack*/
    int pos;   /* 当前存储元素的个数　*/
    int *array; /* 数据存储区 */
} stArrayStack;

#define arrayStack_size(arrayStack) (arrayStack->size)
#define arrayStack_is_empty(arrayStack) (arrayStack->pos == -1)
#define arrayStack_is_full(arrayStack) (arrayStack->pos == (arrayStack->size-1))

#endif