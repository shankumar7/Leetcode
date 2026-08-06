class Solution {
    public String frequencySort(String s) {
        ArrayList<Character> arr = new ArrayList<>();
        int count[] = new int[150];
        for(char ch : s.toCharArray()){
            arr.add(ch);
            count[ch]++;
        }
        Collections.sort(arr , (a,b) -> {
            if(count[a] == count[b])
                return a - b;
            else
                return count[b] - count[a];
        });
        StringBuilder sb = new StringBuilder();
        for(char ch : arr)  sb.append(ch);
        return sb.toString();
    }
}