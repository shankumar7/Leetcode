class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;

        long[] pairs = new long[max + 1];
        int[] count = new int[max + 1];

        for (int d = 1; d <= max; d++) {
            int c = 0;
            for (int m = d; m <= max; m += d) {
                c += freq[m];
            }
            count[d] = c;
        }

        for (int d = max; d >= 1; d--) {
            long total = (long) count[d] * (count[d] - 1) / 2;
            for (int m = d + d; m <= max; m += d) {
                total -= pairs[m];
            }
            pairs[d] = total;
        }

        long[] prefix = new long[max + 1];
        for (int d = 1; d <= max; d++) {
            prefix[d] = prefix[d - 1] + pairs[d];
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long q = queries[i];
            int l = 1, r = max;
            while (l < r) {
                int mid = (l + r) >>> 1;
                if (prefix[mid] > q) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            ans[i] = l;
        }

        return ans;
    }
}