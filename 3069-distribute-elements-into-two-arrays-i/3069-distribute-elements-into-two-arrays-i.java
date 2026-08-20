class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] a = new int[n];
        int[] b = new int[n];

        int i = 0, j = 0;
        a[i] = nums[0];
        b[j] = nums[1];

        for (int k = 2; k < n; k++) {
            if (a[i] > b[j])
                a[++i] = nums[k];
            else
                b[++j] = nums[k];
        }

        for (int k = 0; k <= j; k++)
            a[++i] = b[k];

        return a;
    }
}