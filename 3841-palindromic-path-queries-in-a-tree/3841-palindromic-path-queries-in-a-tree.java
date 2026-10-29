import java.util.*;
class Solution{
public List<Boolean> palindromePath(int n,int[][] edges,String s,String[] queries){
List<Integer>[] g=new ArrayList[n];
for(int i=0;i<n;i++)g[i]=new ArrayList<>();
for(int[] e:edges){g[e[0]].add(e[1]);g[e[1]].add(e[0]);}
int LOG=1;
while((1<<LOG)<=n)LOG++;
int[][] up=new int[LOG][n];
int[] parent=new int[n],depth=new int[n],tin=new int[n],tout=new int[n],mask=new int[n],bit=new int[n];
Arrays.fill(parent,-1);
int timer=0;
ArrayDeque<int[]> st=new ArrayDeque<>();
st.push(new int[]{0,-1,0});
while(!st.isEmpty()){
int[] cur=st.pop();
int u=cur[0],p=cur[1],state=cur[2];
if(state==1){tout[u]=timer-1;continue;}
parent[u]=p==-1?u:p;
tin[u]=timer++;
bit[u]=1<<(s.charAt(u)-'a');
mask[u]=(p==-1?0:mask[p])^bit[u];
st.push(new int[]{u,p,1});
for(int i=g[u].size()-1;i>=0;i--){
int v=g[u].get(i);
if(v==p)continue;
depth[v]=depth[u]+1;
st.push(new int[]{v,u,0});
}
}
for(int i=0;i<n;i++)up[0][i]=parent[i];
for(int j=1;j<LOG;j++)
for(int i=0;i<n;i++)up[j][i]=up[j-1][up[j-1][i]];
int[] fw=new int[n+2];
List<Boolean> ans=new ArrayList<>();
for(String q:queries){
String[] a=q.split(" ");
int u=Integer.parseInt(a[1]);
if(a[0].equals("update")){
int b=1<<(a[2].charAt(0)-'a');
int delta=bit[u]^b;
if(delta!=0){
add(fw,tin[u]+1,delta);
add(fw,tout[u]+2,delta);
bit[u]=b;
}
}else{
int v=Integer.parseInt(a[2]);
int w=lca(u,v,up,depth);
int x=(mask[u]^get(fw,tin[u]+1))^(mask[v]^get(fw,tin[v]+1))^bit[w];
ans.add((x&(x-1))==0);
}
}
return ans;
}
void add(int[] fw,int i,int v){
while(i<fw.length){fw[i]^=v;i+=i&-i;}
}
int get(int[] fw,int i){
int x=0;
while(i>0){x^=fw[i];i-=i&-i;}
return x;
}
int lca(int u,int v,int[][] up,int[] depth){
if(depth[u]<depth[v]){int t=u;u=v;v=t;}
int d=depth[u]-depth[v];
for(int j=0;j<up.length;j++)if((d&(1<<j))!=0)u=up[j][u];
if(u==v)return u;
for(int j=up.length-1;j>=0;j--){
if(up[j][u]!=up[j][v]){
u=up[j][u];
v=up[j][v];
}
}
return up[0][u];
}
}