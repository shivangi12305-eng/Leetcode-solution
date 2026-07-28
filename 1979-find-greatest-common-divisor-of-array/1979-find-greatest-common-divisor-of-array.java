import java.util.Arrays;
import java.math.BigInteger;
class Solution {
    public int findGCD(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        for (int num:nums){
            if (num < min) min = num;
            if (num > max) max = num;
        }
        return BigInteger.valueOf(min).gcd(BigInteger.valueOf(max)).intValue();
    }
}