class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCount=new int[30];
        for(int num:nums){
            for(int i=0;i<30;i++){
                if((num&(1<<i))!=0) bitCount[i]++;
            }
        }
        long mod=1_000_000_007;
        long totalSum=0;
        for(int i=0;i<k;i++){
            long currentNum=0;
            for(int bit=0;bit<30;bit++){
                if(bitCount[bit]>0){
                    currentNum|=(1L<<bit);
                    bitCount[bit]--;
                }
            }
            totalSum=(totalSum+(currentNum*currentNum)%mod)%mod;
        }
        return (int)totalSum;
    }
}