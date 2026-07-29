class Solution {

    private long comb(long n, long r, long limit) {
        if (r > n) return 0;
        r = Math.min(r, n - r);

        long ans = 1;
        for (long i = 1; i <= r; i++) {
            ans = (ans * (n - i + 1)) / i;
            if (ans > limit)
                return limit + 1;
        }
        return ans;
    }

    private long countWays(int[] cnt, long limit) {
        int rem = 0;
        for (int x : cnt)
            rem += x;

        long ways = 1;

        for (int i = 0; i < 26; i++) {
            if (cnt[i] == 0)
                continue;

            ways *= comb(rem, cnt[i], limit);

            if (ways > limit)
                return limit + 1;

            rem -= cnt[i];
        }

        return ways;
    }

    public String smallestPalindrome(String s, long k) {

        int[] freq = new int[26];

        for (char c : s.toCharArray())
            freq[c - 'a']++;

        int[] half = new int[26];
        int len = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            len += half[i];

            if ((freq[i] & 1) == 1)
                mid = (char) ('a' + i);
        }

        StringBuilder left = new StringBuilder();
        long current = 1;

        while (len > 0) {
            boolean found = false;

            for (int c = 0; c < 26; c++) {
                if (half[c] == 0)
                    continue;

                half[c]--;

                long ways = countWays(half, k);

                if (current + ways > k) {
                    left.append((char) ('a' + c));
                    len--;
                    found = true;
                    break;
                }

                current += ways;
                half[c]++;
            }

            if (!found)
                return "";
        }

        StringBuilder ans = new StringBuilder(left);

        if (mid != 0)
            ans.append(mid);

        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }
}