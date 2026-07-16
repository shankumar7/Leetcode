class Solution {
    public int threeSumClosest(int[] nums, int target) {
         int n=nums.length;
         int nearest=nums[0]+nums[1]+nums[2];
        Arrays.sort(nums);
        for(int i=0;i<=n-3;i++){
            if(i>0 &&nums[i]==nums[i-1]) continue;
            int left =i+1;
            int right=n-1;
            while(left<right){
                int sum=nums[left]+nums[right]+nums[i];
                if(sum==target){
                   return sum;
                }
                else if(sum>target) right--;
                else left++;
                if(Math.abs(target-sum)<Math.abs(target-nearest)) nearest=sum;
            }
        }
        return nearest;
    }
}