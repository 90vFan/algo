#include <stdio.h>
#include <stdlib.h>

/**
 * 1) 单链表反转
 * 2）链表中环的检测
 * 3）两个有序的链表合并
 * 4）删除链表倒数第 n 节点
 * 5）求链表的中间节点
 */

typedef struct SinglyLinkedNode {
    int data;
    struct SinglyLinkedNode* next;
} SinglyLinkedNode;

void insertNode(SinglyLinkedNode** head_ref, int data);
void printLinkedList(SinglyLinkedNode* head);

void printLinkedList(SinglyLinkedNode* node)
{
    printf("--- start ---\n");
    int idx = 0;
    while (node) {
        printf("[%2d] % 8d\n", idx, node->data);
        node = node->next;
        idx++;
    }
    printf("---- end ----\n");
}

void insertNode(SinglyLinkedNode** head_ref, int data)
{
    SinglyLinkedNode* new_node = malloc(sizeof(SinglyLinkedNode));
    new_node->data = data;
    new_node->next = *head_ref;
    *head_ref = new_node;
}

/**
 * 反转单链表
 */
void reverse(SinglyLinkedNode** head_ref)
{
    if (*head_ref == NULL) return;

    SinglyLinkedNode *prev = NULL;
    SinglyLinkedNode *current = *head_ref;

    while (current) {
        SinglyLinkedNode *next = current->next;
        if (!next) {
            *head_ref = current;
        }
        current->next = prev;
        prev = current;
        current = next;
    }
}

void test_reverse() {
    SinglyLinkedNode* head = NULL;
    insertNode(&head, 3);
    insertNode(&head, 2);
    insertNode(&head, 1);

    printLinkedList(head);
    reverse(&head);
    printLinkedList(head);
}

int main() {
    test_reverse();
}
