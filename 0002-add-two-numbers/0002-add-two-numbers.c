/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2) {
    struct ListNode temp;
    temp.val=0;
    temp.next=NULL;
    struct ListNode* curr=&temp;

    int carry=0;
    while(l1!=NULL || l2!=NULL || carry!=0){
        int sum = carry + (l1 ? l1->val : 0) + (l2 ? l2->val : 0);
        carry = sum / 10;
        
        struct ListNode* newNode = (struct ListNode*)malloc(sizeof(struct ListNode));
        newNode->val = sum % 10;
        newNode->next = NULL;
        
        curr->next = newNode;
        curr = curr->next;
        
        if (l1) l1 = l1->next;
        if (l2) l2 = l2->next;
    }
    return temp.next;

}