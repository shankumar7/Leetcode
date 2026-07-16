class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        int n=nums.length;
        Arrays.sort(nums);
        for(int i=0;i<=n-3;i++){
            if(i>0 &&nums[i]==nums[i-1]) continue;
            int left =i+1;
            int right=n-1;
            while(left<right){
                int sum=nums[left]+nums[right]+nums[i];
                if(sum==0){
                    ans.add(new ArrayList<>(List.of(nums[i],nums[left],nums[right])));
                    while (left<right &&nums[left]==nums[left+1]) left++;
                    while(left<right &&nums[right]==nums[right-1]) right--;
                    left++;
                    right--;
                }
                else if(sum>0) right--;
                else left++;
            }
        }
        return ans;
    }
}