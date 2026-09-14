class Solution {
    public int removeBoxes(int[] boxes) {
        int n=boxes.length;
        int[][][] dp=new int[n][n][n];
        return calculatePoints(boxes,dp,0,n-1,0);
    }
    private int calculatePoints(int[] boxes,int[][][] dp,int l,int r,int k) {
        if(l>r) return 0;
        while(l+1<=r&&boxes[l]==boxes[l+1]) {
            l++;
            k++;
        }
        if(dp[l][r][k]>0) return dp[l][r][k];
        int res=(k+1)*(k+1)+calculatePoints(boxes,dp,l+1,r,0);
        for(int m=l+1;m<=r;m++) {
            if(boxes[m]==boxes[l]) {
                res=Math.max(res,calculatePoints(boxes,dp,l+1,m-1,0)+calculatePoints(boxes,dp,m,r,k+1));
            }
        }
        dp[l][r][k]=res;
        return res;
    }
}