class Solution{
static final int MOD=1000000007;
int[] sum,sq,lazy;
int n;
void update(int p,int l,int r,int ql,int qr){
if(ql>r||qr<l)return;
if(ql<=l&&r<=qr){
sq[p]=(int)((sq[p]+2L*sum[p]+(r-l+1L))%MOD);
sum[p]=(int)((sum[p]+r-l+1L)%MOD);
lazy[p]++;
return;
}
push(p,l,r);
int m=(l+r)/2;
update(p*2,l,m,ql,qr);
update(p*2+1,m+1,r,ql,qr);
sum[p]=(sum[p*2]+sum[p*2+1])%MOD;
sq[p]=(int)(((long)sq[p*2]+sq[p*2+1])%MOD);
}
void push(int p,int l,int r){
if(lazy[p]==0||l==r)return;
int m=(l+r)/2,v=lazy[p];
apply(p*2,l,m,v);
apply(p*2+1,m+1,r,v);
lazy[p]=0;
}
void apply(int p,int l,int r,int v){
long len=r-l+1L;
sq[p]=(int)((sq[p]+2L*v*sum[p]+len*v*v)%MOD);
sum[p]=(int)((sum[p]+len*v)%MOD);
lazy[p]+=v;
}
public int sumCounts(int[] nums){
n=nums.length;
sum=new int[4*n];
sq=new int[4*n];
lazy=new int[4*n];
int[] last=new int[100001];
long ans=0;
for(int i=0;i<n;i++){
int prev=last[nums[i]];
update(1,0,n-1,prev,i);
last[nums[i]]=i+1;
ans=(ans+sq[1])%MOD;
}
return (int)ans;
}
}