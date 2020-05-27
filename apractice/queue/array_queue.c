#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "./array_queue.h"

array_queue * create(int size)
{
    array_queue * queue = NULL;

    queue = (array_queue*)malloc(sizeof(array_queue));
    if (queue == NULL) {
        return NULL;
    }
    queue->array = (int *)malloc(sizeof(int)*size);
    if (queue->array == NULL) {
        free(queue);
        return NULL;
    }
    queue->size = size;
    queue->num = 0;
    queue->head = 0;
    queue->tail = 0;

    return queue;
}

void destroy(array_queue *queue)
{
    if (queue = NULL) {
        return;
    }

    if (queue->array != NULL) {
        free(queue->array);
    }

    free(queue);
    return;
}

/*入队列 */
int enqueue(array_queue *queue, int data)
{
    printf("\r\n enqueue %d", data);
    if ((queue == NULL) || (array_queue_is_full(queue))) {
        return -1;
    }

    queue->num++;
    queue->array[queue->tail] = data;
    queue->tail = (queue->tail + 1) % queue->size;

    return 0;
}

/* 出队列 */
int dequeue(array_queue * queue, int *data)
{
    if ((queue == NULL) || (data == NULL) || (array_queue_is_empty(queue))) {
        return -1;
    }

    *data = queue->array[queue->head];
    queue->num--;
    queue->head = (queue->head + 1) % queue->size;

    printf("\r\n queue dequeue %d.", *data);
}

void dump(array_queue *queue)
{
    int i = 0;
    int pos = 0;

    if ((queue == NULL) || (array_queue_is_empty(queue))) {
        printf("\r\n queue is empty");
        return;
    }

    printf("\n-----------------------");
    printf("\nsize: %d, num: %d, head: %d, tail: %d",
           queue->size, queue->num, queue->head, queue->tail);

    for (i = 0; i < queue->num; i++) {
        pos = (queue->head + i) % queue->size;
        printf("\r\n array[%d] = %d", pos, queue->array[pos]);
    }
    printf("\n-----------------------\n");
    return;
}

int main()
{
	int i = 0;
	int ret = 0;
	int data = 0;
	array_queue * queue = NULL;

	queue = create(4);
	if (queue == NULL)
	{
		printf("\r\n queue is create failed.");
		return 0;
	}
    dump(queue);

	/*队列时空时，出队返回错误*/
	ret = dequeue(queue, &data);
	if (ret != 0)
	{
		printf("\r\n queue %d dequeue failed.",ret);
	}

	/*队列大小是4，入队5个，最后一个报错*/
	for (i = 0; i < 5; i++)
	{
		ret = enqueue(queue,i);
		if (ret != 0)
		{
		    printf("\r\n queue enqueue %d failed.",i);
		}
	}

	dump(queue);

	ret = dequeue(queue, &data);
	if (ret != 0)
	{
		printf("\r\n queue dequeue %d failed.",i);
	}
	dump(queue);

	data = 5;
	printf("\r\n queue enqueue %d.", data);
	ret = enqueue(queue,data);
	if (ret != 0)
	{
		printf("\r\n queue enqueue %d failed.",data);
    }
	dump(queue);

	destroy(queue);
	return 0;

}
