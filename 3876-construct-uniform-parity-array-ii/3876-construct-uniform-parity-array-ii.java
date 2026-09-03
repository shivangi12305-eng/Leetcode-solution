class Solution {
    public boolean uniformArray(int[] nums1) {
        int minVal = Integer.MAX_VALUE;
        boolean hasOdd = false;
        for (int x : nums1) {
            if (x < minVal) {
                minVal = x;
            }
            if (x % 2 != 0) {
                hasOdd = true;
            }
        }
        if (minVal % 2 != 0) {
            return true;
        }
        return !hasOdd;
    }
}