class Solution{
public String maxSumOfSquares(int num,int sum){
if((long)num*9<sum||sum<1)return "";
StringBuilder sb=new StringBuilder();
for(int i=0;i<num;i++){
int currentDigit=Math.min(9,sum);
sb.append(currentDigit);
sum-=currentDigit;
}
return sb.toString();
}
}