import java.util.ArrayList;

class Solution {
    public char processStr(String s, long k) {

        int n = s.length();
        long[] len = new long[n];

        long currLen = 0;
        long LIMIT = (long) 1e18;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                currLen++;
            }
            else if (ch == '*') {
                if (currLen > 0) {
                    currLen--;
                }
            }
            else if (ch == '#') {
                currLen = Math.min(currLen * 2, LIMIT);
            }
            else if (ch == '%') {
            }

            len[i] = currLen;
        }


        if (k >= currLen) {
            return '.';
        }

        for (int i = n - 1; i >= 0; i--) {

            char ch = s.charAt(i);
            long prevLen = (i == 0) ? 0 : len[i - 1];

            if (ch >= 'a' && ch <= 'z') {
                if (k == prevLen) {
                    return ch;
                }

            }
            else if (ch == '#') {
                if (prevLen > 0) {
                    k %= prevLen;
                }

            }
            else if (ch == '%') {
                k = prevLen - 1 - k;

            }
            else if (ch == '*') {

            }
        }

        return '.';
    }
}