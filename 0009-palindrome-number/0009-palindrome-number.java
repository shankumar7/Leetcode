class Solution {
    public boolean isPalindrome(int x) {
        int temp=x;
    long rev=0;
    if(x<0){
        return false;
    }else{
    while(temp!=0){
        rev=(rev*10)+(temp%10);
        temp/=10;
    }
    }
    return (x==rev);
    }
}