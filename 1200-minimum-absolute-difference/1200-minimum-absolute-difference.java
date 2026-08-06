class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        for(int i = 1 ; i < arr.length ; i++){
            minDiff = Math.min(minDiff, Math.abs(arr[i] - arr[i-1]));
        }
        for(int i = 1 ; i < arr.length ; i++){
            if(Math.abs(arr[i] - arr[i-1]) == minDiff){
                res.add(Arrays.asList(arr[i-1] , arr[i]));
            }
        }
        return res;
    }
}