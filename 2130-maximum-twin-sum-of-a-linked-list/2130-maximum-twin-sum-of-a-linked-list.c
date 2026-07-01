/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
int getsize(struct ListNode* head){
    int count=0;
    struct ListNode* current = head;
    while(current!=NULL){
        count++;
        current=current->next;
    }
    return count;
}
int pairSum(struct ListNode* head) {
    int size=getsize(head);
    int max=0;
    if(size%2==0){
        int arr[size];
        struct ListNode* temp=head;
        for(int i=0;i<size;i++){
            arr[i]=temp->val;
            temp=temp->next;
        }
        for(int i=0;i<size/2;i++){
            if(max<(arr[i]+arr[size-1-i])){
                max=arr[i]+arr[size-i-1];
            }
        }
    }
    return max;
}