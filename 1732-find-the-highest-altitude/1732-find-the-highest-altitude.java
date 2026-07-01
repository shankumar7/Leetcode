class Solution {
    public int largestAltitude(int[] gain) {
        int result=0,sum=0;
        for(int i : gain){
            sum+=i;
            result=(sum>result)?sum:result;
        }
        return result;
    }
}