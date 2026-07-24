import java.util.*;

class Group {
    int start;
    int length;

    Group(int start, int length) {
        this.start = start;
        this.length = length;
    }
}

class ZeroData {
    List<Group> groups;
    int[] index;

    ZeroData(List<Group> groups, int[] index) {
        this.groups = groups;
        this.index = index;
    }
}

class IntPair {
    int first;
    int second;

    IntPair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class SparseTable {
    private final int[][] st;

    SparseTable(int[] nums) {
        int n = nums.length;
        st = new int[bitLength(Math.max(1, n)) + 1][Math.max(1, n)];
        if (n > 0) {
            System.arraycopy(nums, 0, st[0], 0, n);
            for (int i = 1; i < st.length; i++) {
                for (int j = 0; j + (1 << i) <= n; j++) {
                    st[i][j] = Math.max(st[i - 1][j], st[i - 1][j + (1 << (i - 1))]);
                }
            }
        }
    }

    int query(int l, int r) {
        int k = bitLength(r - l + 1) - 1;
        return Math.max(st[k][l], st[k][r - (1 << k) + 1]);
    }

    private int bitLength(int x) {
        return Integer.SIZE - Integer.numberOfLeadingZeros(x);
    }
}

class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') ones++;
        }

        ZeroData info = getZeroGroups(s);
        List<Group> zeroGroups = info.groups;
        int[] zeroGroupIndex = info.index;

        List<Integer> ans = new ArrayList<>();

        if (zeroGroups.isEmpty()) {
            for (int i = 0; i < queries.length; i++) ans.add(ones);
            return ans;
        }

        SparseTable st = new SparseTable(getZeroMergeLengths(zeroGroups));

        for (int[] q : queries) {
            int l = q[0], r = q[1];

            int left = zeroGroupIndex[l] == -1
                    ? -1
                    : zeroGroups.get(zeroGroupIndex[l]).length
                            - (l - zeroGroups.get(zeroGroupIndex[l]).start);

            int right = zeroGroupIndex[r] == -1
                    ? -1
                    : r - zeroGroups.get(zeroGroupIndex[r]).start + 1;

            IntPair p = mapToAdjacentGroupIndices(
                    zeroGroupIndex[l] + 1,
                    s.charAt(r) == '1' ? zeroGroupIndex[r] : zeroGroupIndex[r] - 1);

            int start = p.first;
            int end = p.second;

            int cur = ones;

            if (s.charAt(l) == '0'
                    && s.charAt(r) == '0'
                    && zeroGroupIndex[l] + 1 == zeroGroupIndex[r]) {
                cur = Math.max(cur, ones + left + right);
            } else if (start <= end) {
                cur = Math.max(cur, ones + st.query(start, end));
            }

            if (s.charAt(l) == '0'
                    && zeroGroupIndex[l] + 1
                            <= (s.charAt(r) == '1'
                                    ? zeroGroupIndex[r]
                                    : zeroGroupIndex[r] - 1)) {
                cur = Math.max(
                        cur,
                        ones + left + zeroGroups.get(zeroGroupIndex[l] + 1).length);
            }

            if (s.charAt(r) == '0'
                    && zeroGroupIndex[l] < zeroGroupIndex[r] - 1) {
                cur = Math.max(
                        cur,
                        ones + right + zeroGroups.get(zeroGroupIndex[r] - 1).length);
            }

            ans.add(cur);
        }

        return ans;
    }

    private ZeroData getZeroGroups(String s) {
        List<Group> groups = new ArrayList<>();
        int[] idx = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    groups.get(groups.size() - 1).length++;
                } else {
                    groups.add(new Group(i, 1));
                }
            }
            idx[i] = groups.size() - 1;
        }

        return new ZeroData(groups, idx);
    }

    private int[] getZeroMergeLengths(List<Group> groups) {
        int[] res = new int[Math.max(0, groups.size() - 1)];
        for (int i = 0; i + 1 < groups.size(); i++) {
            res[i] = groups.get(i).length + groups.get(i + 1).length;
        }
        return res;
    }

    private IntPair mapToAdjacentGroupIndices(int a, int b) {
        return new IntPair(a, b - 1);
    }

    private int bitLength(int x) {
        return Integer.SIZE - Integer.numberOfLeadingZeros(x);
    }
}