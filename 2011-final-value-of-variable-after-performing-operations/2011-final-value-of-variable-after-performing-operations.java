class Solution {
    public int finalValueAfterOperations(String[] operations) {
        long x=0;
        for(String sr: operations){
            if(sr.equals("++X")) ++x;
            if(sr.equals("X++")) x++;
            if(sr.equals("X--")) x--;
            if(sr.equals("--X")) --x;
        }
        return (int) x;
    }
}