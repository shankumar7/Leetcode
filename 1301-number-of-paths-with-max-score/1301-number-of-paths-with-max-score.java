class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1000000007;

        int[][] score = new int[n][n];
        int[][] ways = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(score[i], -1);
        }

        score[n - 1][n - 1] = 0;
        ways[n - 1][n - 1] = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (board.get(i).charAt(j) == 'X' || (i == n - 1 && j == n - 1)) {
                    continue;
                }

                int maxScore = -1;
                int count = 0;

                int[][] dirs = {{1, 0}, {0, 1}, {1, 1}};

                for (int[] d : dirs) {
                    int ni = i + d[0];
                    int nj = j + d[1];

                    if (ni >= n || nj >= n || score[ni][nj] == -1) {
                        continue;
                    }

                    if (score[ni][nj] > maxScore) {
                        maxScore = score[ni][nj];
                        count = ways[ni][nj];
                    } else if (score[ni][nj] == maxScore) {
                        count = (count + ways[ni][nj]) % MOD;
                    }
                }

                if (maxScore == -1) {
                    continue;
                }

                char c = board.get(i).charAt(j);
                if (c >= '1' && c <= '9') {
                    maxScore += c - '0';
                }

                score[i][j] = maxScore;
                ways[i][j] = count;
            }
        }

        if (ways[0][0] == 0) {
            return new int[]{0, 0};
        }

        return new int[]{score[0][0], ways[0][0]};
    }
}