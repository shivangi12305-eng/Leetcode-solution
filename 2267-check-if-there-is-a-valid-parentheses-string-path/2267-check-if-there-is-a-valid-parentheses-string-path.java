class Solution {
    int m,n;
    Boolean[][][] dp;
    public boolean hasValidPath(char[][] grid) {
        m=grid.length;
        n=grid[0].length;
        if((m+n-1)%2!=0||grid[0][0]==')'||grid[m-1][n-1]=='(') return false;
        dp=new Boolean[m][n][(m+n)/2+1];
        return dfs(grid,0,0,0);
    }
    private boolean dfs(char[][] grid,int r,int c,int open) {
        open+=grid[r][c]=='('?1:-1;
        if(open<0||open>(m+n)/2) return false;
        if(r==m-1&&c==n-1) return open==0;
        if(dp[r][c][open]!=null) return dp[r][c][open];
        boolean found=false;
        if(r+1<m) found=dfs(grid,r+1,c,open);
        if(!found&&c+1<n) found=dfs(grid,r,c+1,open);
        return dp[r][c][open]=found;
    }
}

