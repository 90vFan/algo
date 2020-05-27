#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "./list_queue.h"

list_queue *create()
{
    list_queue * queue = NULL;

    queue = (list_queue *)malloc(sizeof(list_queue));
    if (queue == NULL) {
        return NULL;
    }

    queue->num = 0;
    queue->head = NULL;
    queue->tail = NULL;

    return queue;
}

void destroy(list_queue *queue)
{
    int i = 0;
    int data = 0;

    if ((queue == NULL) || (list_queue_is_empty(queue))) {
        return;
    }

    while (!list_queue_is_empty(queue)) {
        (void)dequeue(queue, &data);
    }

    free(queue);
    return;
}

/* 入队 */
int enqueue(list_queue *queue, int data)
{
    queue_node *ptmp = NULL;

    if (queue == NULL)
    {
        return -1;
    }

    ptmp = (queue_node *)malloc(sizeof(queue_node));
    if (ptmp == NULL)
    {
        return -1;
    }

    ptmp->data = data;
    ptmp->next = NULL;
    if (queue->head == NULL)
    {
        queue->head = ptmp;
    }
    else
    {
        queue->tail->next = ptmp;
    }
    queue->tail = ptmp;
    queue->num++;

    return 0;
}

/* 出队 */
int dequeue(list_queue *queue, int *data)
{
    queue_node * ptmp = NULL;

    if ((queue == NULL) || (data == NULL) || list_queue_is_empty(queue))
    {
        return -1;
    }

    *data = queue->head->data;
    ptmp = queue->head;
    queue->head = queue->head->next;
    queue->num--;

    if (queue->head == NULL)
    {
        queue->tail = NULL;
    }

    free(ptmp);

    return 0;
}

void dump(list_queue *queue)
{
    int i = 0;
    queue_node *ptmp = NULL;

    if ((queue == NULL) || (list_queue_is_empty(queue)))
    {
        return;
    }

    ptmp = queue->head;

    printf("\n---dump queue num=%d -----", queue->num);
    while (ptmp != NULL)
    {
        printf("\r\nnode[%d] = %d", i, ptmp->data);
        i++;
        ptmp = ptmp->next;
    }
    printf("\n----------------------------\n");

    return;
}


int main()
{
	int i = 0;
	int data = 0;
	int ret = 0;
	list_queue * queue;

	queue = create();
	if (queue == NULL)
	{
		printf("\r\nlist queue create falied..");
		return 0;
	}

	for (i = 0; i < 5; i++)
	{
		(void)enqueue(queue,i);
	}
	dump(queue);

    ret = dequeue(queue,&data);
	if(ret != 0)
	{
		printf("\r\nlist queue dequeue %d falied.",data);
	}
	printf("\r\nlist queue dequeue %d",data);
	dump(queue);


    ret = dequeue(queue,&data);
	if(ret != 0)
	{
		printf("\r\nlist queue dequeue %d failed.",data);
	}
    printf("\r\nlist queue dequeue %d",data);
	dump(queue);

	printf("\r\nlist queue enqueue %d",data);
	(void)enqueue(queue,data);
	dump(queue);

    destroy(queue);
	return 0;
}
