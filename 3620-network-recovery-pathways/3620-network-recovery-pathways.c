#include <stdlib.h>
#include <string.h>
#include <limits.h>

typedef long long ll;

typedef struct Edge {
    int to;
    int w;
    int next;
} Edge;

static Edge *g;
static int *head;
static int edgeCnt;
static int *indeg;
static int *queue;
static int *topo;
static ll *dp;

static int n;

int check(int **edges, int edgesSize, bool *online, long long k, int limit) {

    memset(head, -1, sizeof(int) * n);
    memset(indeg, 0, sizeof(int) * n);
    edgeCnt = 0;

    for (int i = 0; i < edgesSize; i++) {

        int u = edges[i][0];
        int v = edges[i][1];
        int w = edges[i][2];

        if (w < limit)
            continue;

        if (u != 0 && u != n - 1 && !online[u])
            continue;

        if (v != 0 && v != n - 1 && !online[v])
            continue;

        g[edgeCnt].to = v;
        g[edgeCnt].w = w;
        g[edgeCnt].next = head[u];
        head[u] = edgeCnt++;

        indeg[v]++;
    }

    int front = 0, back = 0;

    for (int i = 0; i < n; i++)
        if (indeg[i] == 0)
            queue[back++] = i;

    int idx = 0;

    while (front < back) {
        int u = queue[front++];
        topo[idx++] = u;

        for (int e = head[u]; e != -1; e = g[e].next) {
            int v = g[e].to;
            if (--indeg[v] == 0)
                queue[back++] = v;
        }
    }

    const ll INF = (1LL << 60);

    for (int i = 0; i < n; i++)
        dp[i] = INF;

    dp[0] = 0;

    for (int i = 0; i < idx; i++) {

        int u = topo[i];

        if (dp[u] == INF)
            continue;

        for (int e = head[u]; e != -1; e = g[e].next) {

            int v = g[e].to;
            ll nd = dp[u] + g[e].w;

            if (nd < dp[v])
                dp[v] = nd;
        }
    }

    return dp[n - 1] <= k;
}

int findMaxPathScore(int** edges, int edgesSize, int* edgesColSize,
                     bool* online, int onlineSize, long long k) {

    n = onlineSize;

    head = (int *)malloc(sizeof(int) * n);
    indeg = (int *)malloc(sizeof(int) * n);
    queue = (int *)malloc(sizeof(int) * n);
    topo = (int *)malloc(sizeof(int) * n);
    dp = (ll *)malloc(sizeof(ll) * n);

    g = (Edge *)malloc(sizeof(Edge) * (edgesSize + 5));

    int hi = 0;

    for (int i = 0; i < edgesSize; i++)
        if (edges[i][2] > hi)
            hi = edges[i][2];

    int lo = 0;
    int ans = -1;

    while (lo <= hi) {

        int mid = lo + (hi - lo) / 2;

        if (check(edges, edgesSize, online, k, mid)) {
            ans = mid;
            lo = mid + 1;
        } else {
            hi = mid - 1;
        }
    }

    free(head);
    free(indeg);
    free(queue);
    free(topo);
    free(dp);
    free(g);

    return ans;
}