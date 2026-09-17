class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n=arr.length;
        int[] best=new int[n];
        int[] dp=new int[n+1];
        for(int i=0;i<=n;i++) dp[i]=1000000;
        int left=0,sum=0,ans=1000000,bestLen=1000000;
        for(int right=0;right<n;right++){
            sum+=arr[right];
            while(sum>target) sum-=arr[left++];
            if(sum==target){
                int len=right-left+1;
                if(left>0&&best[left-1]<1000000)
                    ans=Math.min(ans,len+best[left-1]);
                bestLen=Math.min(bestLen,len);
            }
            best[right]=bestLen;
        }
        return ans==1000000?-1:ans;
    }
}