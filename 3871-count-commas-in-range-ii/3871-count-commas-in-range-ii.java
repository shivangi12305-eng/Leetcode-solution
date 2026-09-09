class Solution {
    public long countCommas(long n) {
        long comma=1;
        long st=1000;
        long ans=0;
        while(st<=n){
            long end=Math.min(n,st*1000-1);
            ans+=(end-st+1)*comma;
            comma++;
            st*=1000;
        }
        return ans;
    }
}