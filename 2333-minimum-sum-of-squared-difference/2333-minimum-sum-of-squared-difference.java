class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n=nums1.length;
        int[] diff=new int[n];
        int max=0;
        for(int i=0;i<n;i++){
            diff[i]=Math.abs(nums1[i]-nums2[i]);
            max=Math.max(max,diff[i]);
        }
        long k=(long)k1+k2;
        int[] freq=new int[max+1];
        for(int d:diff) freq[d]++;
        for(int i=max;i>0&&k>0;i--){
            long move=Math.min(k,(long)freq[i]);
            freq[i]-=move;
            freq[i-1]+=move;
            k-=move;
        }
        long ans=0;
        for(int i=0;i<=max;i++) ans+=(long)i*i*freq[i];
        return ans;
    }
}