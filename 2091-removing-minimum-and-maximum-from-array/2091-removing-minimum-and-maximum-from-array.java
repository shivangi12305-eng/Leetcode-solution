class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }

        int minIdx = 0;
        int maxIdx = 0;

        for (int k = 1; k < n; k++) {
            if (nums[k] < nums[minIdx]) {
                minIdx = k;
            }
            if (nums[k] > nums[maxIdx]) {
                maxIdx = k;
            }
        }

        int i = Math.min(minIdx, maxIdx);
        int j = Math.max(minIdx, maxIdx);

        int removeBothFront = j + 1;
        int removeBothBack = n - i;
        int removeFromBothEnds = (i + 1) + (n - j);

        return Math.min(removeBothFront, Math.min(removeBothBack, removeFromBothEnds));
    }
}