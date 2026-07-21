class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int ones = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') ones++;
        }

        java.util.ArrayList<Character> chars = new java.util.ArrayList<>();
        java.util.ArrayList<Integer> lens = new java.util.ArrayList<>();

        int i = 0;
        while (i < n) {
            char c = s.charAt(i);
            int j = i;
            while (j < n && s.charAt(j) == c) j++;
            chars.add(c);
            lens.add(j - i);
            i = j;
        }

        int ans = ones;
        int m = chars.size();

        for (i = 1; i < m - 1; i++) {
            if (chars.get(i) == '1' && chars.get(i - 1) == '0' && chars.get(i + 1) == '0') {
                ans = Math.max(ans, ones + lens.get(i - 1) + lens.get(i + 1));
            }
        }

        return ans;
    }
}