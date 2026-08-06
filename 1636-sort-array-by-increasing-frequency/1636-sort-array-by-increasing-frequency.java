class Solution {
    public int[] frequencySort(int[] nums) {
        ArrayList<Integer> arr = new ArrayList<>();
        HashMap<Integer, Integer> mp = new HashMap<>();

        for (int val : nums) {
            arr.add(val);
            mp.put(val, mp.getOrDefault(val, 0) + 1);
        }
        Collections.sort(arr, (a, b) -> {
            if (!mp.get(a).equals(mp.get(b))) {
                return mp.get(a) - mp.get(b);   
            }
            return b - a;                      
        });

        for (int i = 0; i < nums.length; i++) {
            nums[i] = arr.get(i);
        }

        return nums;
    }
}