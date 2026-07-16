class Solution {
    public int max(int[] nums){
        int m=nums[0];
        for(int i:nums){
            if(m<i) m=i;
        }
        return m;
    }
    public int GCD(int a,int b){
        if(b==0) return a;
        return GCD(b,a%b);
    }
    public long gcdSum(int[] nums) {
        long ans=0;
        int[] prefixGcd = new int[nums.length];
        int runningMax = nums[0]; 
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > runningMax) {
                runningMax = nums[i];
            }
            prefixGcd[i] = GCD(nums[i], runningMax);
        }

        Arrays.sort(prefixGcd);
        for(int i=0;i<nums.length/2;i++){
            ans+=GCD(prefixGcd[i],prefixGcd[(nums.length)-i-1]);
        }
        
        return ans;
    }
}