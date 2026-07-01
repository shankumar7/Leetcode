class Solution {
    static final long MOD = 1000000007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        if (n == 1) {
            return m;
        }

        long[] up = new long[m];
        long[] down = new long[m];

        long pref = 0;
        for (int i = 0; i < m; i++) {
            up[i] = pref;
            pref++;
        }

        long suff = 0;
        for (int i = m - 1; i >= 0; i--) {
            down[i] = suff;
            suff++;
        }

        for (int len = 3; len <= n; len++) {
            long[] newUp = new long[m];
            long[] newDown = new long[m];

            long[] prefixDown = new long[m + 1];
            for (int i = 0; i < m; i++) {
                prefixDown[i + 1] = (prefixDown[i] + down[i]) % MOD;
            }

            long[] suffixUp = new long[m + 1];
            for (int i = m - 1; i >= 0; i--) {
                suffixUp[i] = (suffixUp[i + 1] + up[i]) % MOD;
            }

            for (int i = 0; i < m; i++) {
                newUp[i] = prefixDown[i];
                newDown[i] = suffixUp[i + 1];
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;
        for (int i = 0; i < m; i++) {
            ans = (ans + up[i] + down[i]) % MOD;
        }

        return (int) ans;
    }
}