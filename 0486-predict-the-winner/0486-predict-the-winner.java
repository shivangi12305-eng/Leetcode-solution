// class Solution {
//     public boolean predictTheWinner(int[] nums) {
//         int n = nums.length;
//         int [][] dp= new int[n][n];
//         for(int i=0;i<n;i++){
//             dp[i][i]=nums[i];
//         }
//         for(int len =2; len<=n;len++){
//             for(int i=0;i<n-len;i++){
//                 int j= i+len-1;
//               dp[i][j] = Math.max(
//                     nums[i] - dp[i + 1][j],
//                     nums[j] - dp[i][j - 1]
//                 );
//         }
//     }
//     return dp[0][n-1]>=0;
// }
// }
class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        if (n % 2 == 0) return true;
        
        int[] dp = nums.clone();
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }
        return dp[n - 1] >= 0;
    }
}
