import java.util.*;
class Solution{
public long minOperations(int[] nums,int x,int k){
int n=nums.length;
TreeMap<Integer,Integer> lo=new TreeMap<>(),hi=new TreeMap<>();
long[] cost=new long[n-x+1];
long[] sums=new long[2];
int[] sizes=new int[2];
for(int i=0;i<n;i++){
add(nums[i],lo,hi,sums,sizes);
if(i>=x)remove(nums[i-x],lo,hi,sums,sizes);
if(i>=x-1){
int m=i-x+1;
long med=lo.lastKey();
cost[m]=med*sizes[0]-sums[0]+sums[1]-med*sizes[1];
}
}
long inf=Long.MAX_VALUE/4;
long[][] dp=new long[k+1][n+1];
for(long[] row:dp)Arrays.fill(row,inf);
dp[0][0]=0;
for(int j=0;j<=k;j++){
for(int i=0;i<=n;i++){
if(dp[j][i]==inf)continue;
if(i<n)dp[j][i+1]=Math.min(dp[j][i+1],dp[j][i]);
if(j<k&&i+x<=n)dp[j+1][i+x]=Math.min(dp[j+1][i+x],dp[j][i]+cost[i]);
}
}
return dp[k][n];
}
void add(int v,TreeMap<Integer,Integer> lo,TreeMap<Integer,Integer> hi,long[] s,int[] z){
if(lo.isEmpty()||v<=lo.lastKey()){
lo.merge(v,1,Integer::sum);s[0]+=v;z[0]++;
}else{
hi.merge(v,1,Integer::sum);s[1]+=v;z[1]++;
}
balance(lo,hi,s,z);
}
void remove(int v,TreeMap<Integer,Integer> lo,TreeMap<Integer,Integer> hi,long[] s,int[] z){
if(lo.containsKey(v)){
del(lo,v);s[0]-=v;z[0]--;
}else{
del(hi,v);s[1]-=v;z[1]--;
}
balance(lo,hi,s,z);
}
void del(TreeMap<Integer,Integer> m,int v){
if(m.get(v)==1)m.remove(v);
else m.put(v,m.get(v)-1);
}
void balance(TreeMap<Integer,Integer> lo,TreeMap<Integer,Integer> hi,long[] s,int[] z){
while(z[0]<z[1]){
int v=hi.firstKey();del(hi,v);lo.merge(v,1,Integer::sum);
s[1]-=v;s[0]+=v;z[1]--;z[0]++;
}
while(z[0]>z[1]+1){
int v=lo.lastKey();del(lo,v);hi.merge(v,1,Integer::sum);
s[0]-=v;s[1]+=v;z[0]--;z[1]++;
}
}
}