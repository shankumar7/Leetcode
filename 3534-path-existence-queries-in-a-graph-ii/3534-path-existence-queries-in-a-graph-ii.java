import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] ans = new int[queries.length];
        int[] indexMap = new int[n];
        int[] sortedNums = new int[n];

        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }

        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));

        for (int i = 0; i < n; i++) {
            sortedNums[i] = pairs[i][0];
            indexMap[pairs[i][1]] = i;
        }

        int maxLevel = 32 - Integer.numberOfLeadingZeros(n) + 1;
        int[][] jump = new int[n][maxLevel];

        int right = 0;
        for (int i = 0; i < n; i++) {
            while (right + 1 < n && sortedNums[right + 1] - sortedNums[i] <= maxDiff)
                right++;
            jump[i][0] = right;
        }

        for (int k = 1; k < maxLevel; k++) {
            for (int i = 0; i < n; i++) {
                jump[i][k] = jump[jump[i][k - 1]][k - 1];
            }
        }

        for (int i = 0; i < queries.length; i++) {
            int u = indexMap[queries[i][0]];
            int v = indexMap[queries[i][1]];
            int l = Math.min(u, v);
            int r = Math.max(u, v);
            int res = solve(jump, l, r, maxLevel - 1);
            ans[i] = res == Integer.MAX_VALUE ? -1 : res;
        }

        return ans;
    }

    private int solve(int[][] jump, int start, int end, int level) {
        if (start == end) return 0;
        if (jump[start][0] >= end) return 1;
        if (jump[start][level] < end) return Integer.MAX_VALUE;

        int j = level;
        while (j >= 0 && jump[start][j] >= end) j--;
        return (1 << j) + solve(jump, jump[start][j], end, j);
    }
}