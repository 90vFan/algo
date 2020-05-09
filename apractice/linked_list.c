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

void test_reverse()
{
    printf("test_reverse ...\n");
    SinglyLinkedNode* head = NULL;
    insertNode(&head, 3);
    insertNode(&head, 2);
    insertNode(&head, 1);

    printLinkedList(head);
    reverse(&head);
    printLinkedList(head);
}

/**
 * 检测单链表是否有环
 */
int checkCircle(SinglyLinkedNode** head_ref)
{
    if (*head_ref == NULL) return 0;
    SinglyLinkedNode *slow = *head_ref, *fast = *head_ref;
    while (fast != NULL && fast->next != NULL) {
        fast = fast->next->next;
        slow = slow->next;
        if (slow == fast) return 1;
    }
}

void test_checkCircle()
{
    printf("test_checkCircle ...\n");
    SinglyLinkedNode* head = NULL;
    insertNode(&head, 3);
    insertNode(&head, 2);
    insertNode(&head, 1);

    int result1 = checkCircle(&head);
    printf("has circle: %d\n", result1);

    // make circle linklist
    SinglyLinkedNode* current = malloc(sizeof(SinglyLinkedNode));
    current->data = 0;
    SinglyLinkedNode* h = current;
    int i = 1;
    for (i = 1; i < 4; ++i) {
        SinglyLinkedNode* node = malloc(sizeof(SinglyLinkedNode));
        node->data = i;
        current->next = node;
        //reset current node
        current = node;
    }
    current->next = h;

    int result2 = checkCircle(&h);
    printf("has circle: %d\n", result2);
}

/**
 * 有序链表合并
 */
void moveNode(SinglyLinkedNode** dest_ref, SinglyLinkedNode** src_ref);

SinglyLinkedNode* mergeSortedLinkedList(SinglyLinkedNode* la, SinglyLinkedNode* lb)
{
    // 辅助节点， next 指针持有合并后的有序链表
    SinglyLinkedNode dummy;
    SinglyLinkedNode* tail = &dummy;

    while (1) {
        // 如果有一个链表为空，直接与另一个链表接起来
        if (!la) {
            tail->next = lb;
            break;
        } else if (!lb) {
            tail->next = la;
            break;
        }

        // 将头较小的优先添加到 tail
        if (la->data <= lb->data) {
            moveNode(&(tail->next), &la);
        } else {
            moveNode(&(tail->next), &lb);
        }
        tail = tail->next;
    }
    return dummy.next;
}

/**
 * 将 src_ref 的头结点(head_ref)，添加到 dest_ref 的头部
 */
void moveNode(SinglyLinkedNode** dest_ref, SinglyLinkedNode** src_ref)
{
    if (*src_ref == NULL) return;
    SinglyLinkedNode* new_node = *src_ref;

    *src_ref = new_node->next;
    new_node->next = *dest_ref;
    *dest_ref = new_node;
}

void test_mergeSortedLinkedList() {
    printf("test mergeSOrtedLinkedList ...\n");
    SinglyLinkedNode* a = NULL;
    insertNode(&a, 10);
    insertNode(&a, 5);
    insertNode(&a, 0);

    SinglyLinkedNode* b = NULL;
    insertNode(&b, 8);
    insertNode(&b, 6);
    insertNode(&b, 3);

    SinglyLinkedNode* result = mergeSortedLinkedList(a, b);
    printLinkedList(result);

    SinglyLinkedNode* result2 = mergeSortedLinkedList(a, NULL);
    printLinkedList(result2);
}

/**
 * 删除倒数第 K 个结点
 *
 * 快指针 fast 向前移动 k-1
 * 然后同时移动快指针 fast 和慢指针 slow , 知道 fast 指向链表结尾
 */
void deleteLastKth(SinglyLinkedNode** head_ref, int k)
{
    if (*head_ref == NULL || k == 0) return;

    // 快指针向前移动 k-1
    SinglyLinkedNode* fast = *head_ref;
    int i = 1;
    while (i < k && fast != NULL) {
        fast = fast->next;
        ++i;
    }

    // 如果快指针为空，说明节点个数小于 k
    if (fast == NULL) return;

    SinglyLinkedNode* slow = *head_ref;
    SinglyLinkedNode* prev = NULL;
    // fast ll[k-1] -> ... -> ll[-1] -> NULL
    // slow ll[0]   -> ... -> ll[-k]
    while (fast->next != NULL) {
        fast = fast->next;
        prev = slow;
        slow = slow->next;
    }

    // 如果 prev 为空，头结点刚好是第 k 个结点
    if (!prev) {
        (*head_ref) = (*head_ref)->next;
    } else {
        prev->next = slow->next;
    }
    free(slow);
}

void test_deleteLastKth() {
    printf("test_deleteLastKth ...\n");
    SinglyLinkedNode* head = NULL;
    insertNode(&head, 1);
    insertNode(&head, 2);
    insertNode(&head, 3);
    insertNode(&head, 4);
    insertNode(&head, 5);

    printf("oringinal link list:\n");
    printLinkedList(head);

    // 1. 删除头结点
    deleteLastKth(&head, 5);
    printf("delete last 5th:\n");
    printLinkedList(head);

    // 2. 删除中间结点
    deleteLastKth(&head, 2);
    printf("delete last 2th:\n");
    printLinkedList(head);
}

/**
 * 求中间节点
 */
SinglyLinkedNode* findMiddleNode(SinglyLinkedNode* head)
{
    if (!head) return NULL;
    SinglyLinkedNode* slow = head;
    SinglyLinkedNode* fast = head;

    // 慢指针走一步，快指针走两步
    while (fast->next != NULL && fast->next->next != NULL) {
        slow = slow->next;
        fast = fast->next->next;
    }
    return slow;
}

void test_findMiddleNode() {
    printf("test_findMiddleNode ...\n");
    SinglyLinkedNode* head = NULL;
    insertNode(&head, 1);
    insertNode(&head, 2);
    insertNode(&head, 3);
    insertNode(&head, 4);
    insertNode(&head, 5);

    SinglyLinkedNode* middleNode = findMiddleNode(head);
    printf("Middle: %d\n", middleNode->data);
    printLinkedList(head);
}

int main() {
    test_reverse();
    test_checkCircle();
    test_mergeSortedLinkedList();
    test_deleteLastKth();
    test_findMiddleNode();
}
