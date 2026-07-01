/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
struct ListNode* deleteMiddle(struct ListNode* head) {
    if (head == NULL || head->next == NULL) {
        return NULL;
    }
    struct ListNode* temp=head;
    struct ListNode* s=head;
    int n=0;
    while(temp!=NULL){
        n++;
        temp=temp->next;
    }
    for(int i=0;i<((n/2)-1);i++){
        s=s->next;
    }
    s->next=s->next->next;
    return head;
}