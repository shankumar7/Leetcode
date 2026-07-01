class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] arr = new int[5]; // b, a, l, o, n

        for (char ch : text.toCharArray()) {
            if (ch == 'b') arr[0]++;
            else if (ch == 'a') arr[1]++;
            else if (ch == 'l') arr[2]++;
            else if (ch == 'o') arr[3]++;
            else if (ch == 'n') arr[4]++;
        }

        return Math.min(
                Math.min(arr[0], arr[1]),
                Math.min(arr[4], Math.min(arr[2] / 2, arr[3] / 2))
        );
    }
}