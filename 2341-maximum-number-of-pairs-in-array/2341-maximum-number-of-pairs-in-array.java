class Solution {
    public int[] numberOfPairs(int[] nums) {
        HashMap<Integer,Integer> mp=new HashMap<>();
        for(int val:nums){
            mp.put(val,mp.getOrDefault(val,0)+1);
        }
        int pairs=0,left=0;
        for(int key: mp.keySet()){
            pairs+=mp.get(key)/2;
             if(mp.get(key)%2 != 0){
                left++;
            }
        }
        return new int[]{pairs,left};
    }
}