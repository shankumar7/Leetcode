class Solution {
    static final long MOD = 1_000_000_007L;

    class Node {
        long val;
        int cnt;
        Node() {}
        Node(long v, int c) {
            val = v;
            cnt = c;
        }
    }

    Node[] tree;
    long[] pow10;
    char[] arr;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        arr = s.toCharArray();

        pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++)
            pow10[i] = (pow10[i - 1] * 10) % MOD;

        tree = new Node[4 * n];
        build(1, 0, n - 1);

        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++)
            prefix[i + 1] = prefix[i] + (arr[i] - '0');

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            Node res = query(1, 0, n - 1, l, r);
            long sum = prefix[r + 1] - prefix[l];

            ans[i] = (int) ((res.val * (sum % MOD)) % MOD);
        }

        return ans;
    }

    private void build(int idx, int l, int r) {
        if (l == r) {
            int d = arr[l] - '0';
            if (d == 0)
                tree[idx] = new Node(0, 0);
            else
                tree[idx] = new Node(d, 1);
            return;
        }

        int mid = (l + r) / 2;
        build(idx * 2, l, mid);
        build(idx * 2 + 1, mid + 1, r);

        tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
    }

    private Node query(int idx, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr)
            return tree[idx];

        if (r < ql || l > qr)
            return new Node(0, 0);

        int mid = (l + r) / 2;

        if (qr <= mid)
            return query(idx * 2, l, mid, ql, qr);

        if (ql > mid)
            return query(idx * 2 + 1, mid + 1, r, ql, qr);

        return merge(
                query(idx * 2, l, mid, ql, qr),
                query(idx * 2 + 1, mid + 1, r, ql, qr));
    }

    private Node merge(Node left, Node right) {
        Node res = new Node();
        res.cnt = left.cnt + right.cnt;
        res.val = (left.val * pow10[right.cnt] + right.val) % MOD;
        return res;
    }
}