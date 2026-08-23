class Solution {
    public boolean sumGame(String num) {
        int n = num.length(), c1 = 0, c2 = 0, s1 = 0, s2 = 0;

        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') c1++;
            else s1 += num.charAt(i) - '0';
        }

        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') c2++;
            else s2 += num.charAt(i) - '0';
        }

        return (c1 + c2) % 2 == 1 || s1 - s2 != 9 * (c2 - c1) / 2;
    }
}