class Solution {
    int[][] dp;
    int[] prefix;
    int[] a;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        a = stoneValue;
        dp = new int[n][n];
        prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + a[i];
        }

        return solve(0, n - 1);
    }

    private int solve(int l, int r) {
        if (l >= r) return 0;

        if (dp[l][r] != 0) return dp[l][r];

        int leftSum = 0;
        int rightSum = prefix[r + 1] - prefix[l];
        int ans = 0;

        for (int k = l; k < r; k++) {
            leftSum += a[k];
            rightSum -= a[k];

            if (leftSum < rightSum) {
                if (ans >= 2 * leftSum) continue;
                ans = Math.max(ans, leftSum + solve(l, k));
            } 
            else if (leftSum > rightSum) {
                if (ans >= 2 * rightSum) break;
                ans = Math.max(ans, rightSum + solve(k + 1, r));
            } 
            else {
                ans = Math.max(ans,
                        Math.max(leftSum + solve(l, k),
                                 rightSum + solve(k + 1, r)));
            }
        }

        return dp[l][r] = ans;
    }
}