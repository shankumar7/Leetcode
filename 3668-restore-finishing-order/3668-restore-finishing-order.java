class Solution {
    public int[] recoverOrder(int[] order, int[] friends) {
        Set<Integer> fr=new HashSet<>();
        for(int f:friends){
            fr.add(f);
        }

        List<Integer> result=new ArrayList<>();
        for(int i:order){
            if(fr.contains(i)){
                result.add(i);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}