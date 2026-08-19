import java.util.*;
class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMasks = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            if (col >= 2 && col <= 9) {
                rowMasks.put(row, rowMasks.getOrDefault(row, 0) | (1 << col));
            }
        }
        
        int ans = (n - rowMasks.size()) * 2;
        
        int leftMask = 60;
        int rightMask = 960;
        int midMask = 240;
        
        for (int mask : rowMasks.values()) {
            boolean leftValid = (mask & leftMask) == 0;
            boolean rightValid = (mask & rightMask) == 0;
            
            if (leftValid && rightValid) {
                ans += 2;
            } else if (leftValid || rightValid || ((mask & midMask) == 0)) {
                ans += 1;
            }
            
        }
        
        return ans;
    }
}