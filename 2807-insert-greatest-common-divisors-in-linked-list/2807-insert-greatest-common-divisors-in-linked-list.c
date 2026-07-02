/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
 int gcd(int a, int b){
    if (b == 0)
        return a;
    return gcd(b, a % b);

}
struct ListNode* insertGreatestCommonDivisors(struct ListNode* head) {
    struct ListNode* temp=head;
    while(temp->next!=NULL){
        struct ListNode * ne=(struct ListNode*)malloc(sizeof(struct ListNode));
        int g=gcd(temp->val,temp->next->val);
        ne->val=g;
        ne->next=temp->next;
        temp->next=ne;
        temp=ne->next;
    }
    return head;
}