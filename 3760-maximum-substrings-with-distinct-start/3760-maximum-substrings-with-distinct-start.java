class Solution {
    public int maxDistinct(String s) {
        int count=0;
        boolean []arr=new boolean[26];
        for(char ch: s.toCharArray()){
            int x=ch-'a';
            if(!arr[x]){
                arr[x]=true;
                count++;
            }
        }
        return count++;
    }
}