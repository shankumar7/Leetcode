class Solution {
public:
    long long countMajoritySubarrays(vector<int>& nums, int target) {
        int n=nums.size();
        long ans=0;
       std::vector<int> pre(n * 2 + 1, 0);
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
};