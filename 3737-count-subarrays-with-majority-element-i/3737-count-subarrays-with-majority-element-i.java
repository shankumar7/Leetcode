class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int ans=0;
        int n=nums.length;
        for(int i=0;i<n;i++){
            int c=0;
            for(int j=i;j<n;j++){
                c+=(target==nums[j] ? 1:-1);
                if(c>0) ++ans;
            }
        }

        return ans;
    }
}