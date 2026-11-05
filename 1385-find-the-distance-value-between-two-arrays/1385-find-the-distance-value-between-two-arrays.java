class Solution{
public int findTheDistanceValue(int[] arr1,int[] arr2,int d){
int ans=0;
for(int x:arr1){
boolean flag=true;
for(int y:arr2){
if(Math.abs(x-y)<=d){
flag=false;
break;
}
}
if(flag)ans++;
}
return ans;
}
}