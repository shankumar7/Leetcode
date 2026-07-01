class Solution {
    public int maxIceCream(int[] costs, int coins) {
        int s=0,sum=0;
        Arrays.sort(costs);
        for(int i: costs){
            if((sum+i)>coins){
                break;
            }else{
                s++;
                sum+=i;
            }
        }
        return s;
    }
}