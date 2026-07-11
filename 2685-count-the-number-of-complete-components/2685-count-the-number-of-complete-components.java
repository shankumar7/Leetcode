import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        boolean[] vis = new boolean[n];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (vis[i]) continue;

            List<Integer> comp = new ArrayList<>();
            Queue<Integer> q = new LinkedList<>();
            q.offer(i);
            vis[i] = true;

            while (!q.isEmpty()) {
                int u = q.poll();
                comp.add(u);
                for (int v : graph[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        q.offer(v);
                    }
                }
            }
            int size = comp.size();
            boolean ok = true;
            for (int node : comp) {
                if (graph[node].size() != size - 1) {
                    ok = false;
                    break;
                }
            }

            if (ok) ans++;
        }
        return ans;
    }
}