import java.util.*;
class Solution{
public int shortestPath(int n,int[][] edges,String labels,int k){
List<int[]>[] g=new ArrayList[n];
for(int i=0;i<n;i++)g[i]=new ArrayList<>();
for(int[] e:edges)g[e[0]].add(new int[]{e[1],e[2]});
long[][] dist=new long[n][k+1];
for(long[] d:dist)Arrays.fill(d,Long.MAX_VALUE);
PriorityQueue<long[]> pq=new PriorityQueue<>((a,b)->Long.compare(a[0],b[0]));
dist[0][1]=0;
pq.offer(new long[]{0,0,1});
while(!pq.isEmpty()){
long[] cur=pq.poll();
long cost=cur[0];
int u=(int)cur[1],cnt=(int)cur[2];
if(cost!=dist[u][cnt])continue;
if(u==n-1)return (int)cost;
for(int[] e:g[u]){
int v=e[0],nc=labels.charAt(u)==labels.charAt(v)?cnt+1:1;
if(nc>k)continue;
long nd=cost+e[1];
if(nd<dist[v][nc]){
dist[v][nc]=nd;
pq.offer(new long[]{nd,v,nc});
}
}
}
return -1;
}
}