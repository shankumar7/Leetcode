class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n=nums.length;
        long ans=0;
         int[] pre = new int[n * 2 + 1];
        pre[n] = 1;
        int c = n;
        long presum = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == target) {
                presum += pre[c];
                ++c;
                ++pre[c];
            } else {
                --c;
                presum -= pre[c];
                ++pre[c];
            }
            ans += presum;
        }
        return ans;
    }
}