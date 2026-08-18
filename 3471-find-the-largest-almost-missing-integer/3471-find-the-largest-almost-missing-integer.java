class Solution {
    public int largestInteger(int[] nums, int k) {

        int n = nums.length, ans = -1;
        int[] cnt = new int[51];

        for (int x : nums) cnt[x]++;

        if (k == n) {
            for (int x : nums) ans = Math.max(ans, x);
        } 
        else if (k == 1) {
            for (int x : nums)
                if (cnt[x] == 1) ans = Math.max(ans, x);
        } 
        else {
            if (cnt[nums[0]] == 1) ans = Math.max(ans, nums[0]);
            if (cnt[nums[n - 1]] == 1) ans = Math.max(ans, nums[n - 1]);
        }

        return ans;
    }
}
    