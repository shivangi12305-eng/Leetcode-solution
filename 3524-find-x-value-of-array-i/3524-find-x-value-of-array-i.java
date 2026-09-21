class Solution {
    public long[] resultArray(int[] nums,int k) {
        int n=nums.length;
        long[][] dp=new long[n][k];
        dp[0][nums[0]%k]=1;
        for(int i=1;i<n;i++){
            dp[i][nums[i]%k]++;
            for(int j=0;j<k;j++){
                if(dp[i-1][j]>0){
                    int x=(j*(nums[i]%k))%k;
                    dp[i][x]+=dp[i-1][j];
                }
            }
        }
        long[] ans=new long[k];
        for(int i=0;i<n;i++){
            for(int j=0;j<k;j++){
                ans[j]+=dp[i][j];
            }
        }
        return ans;
    }
}