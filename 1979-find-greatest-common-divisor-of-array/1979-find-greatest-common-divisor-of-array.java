class Solution {
    public int findGCD(int[] nums) {
        int s=nums[0];
        int l=nums[0];
        for(int i:nums){
            if(s>i) s=i;
            if(l<i) l=i;
        }
        for(int j=s;j>0;j--){
            if(l%j==0 &&s%j==0) return j;
        }
         return 1;
    }
   
}