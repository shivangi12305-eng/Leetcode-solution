class Solution {
    public int[] validSequence(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[] last = new int[n + 1];
        last[n] = m;
        
        int ptr = m - 1;
        for (int j = n - 1; j >= 0; j--) {
            while (ptr >= 0 && word1.charAt(ptr) != word2.charAt(j)) {
                ptr--;
            }
            last[j] = ptr;
            if (ptr >= 0) {
                ptr--;
            }
        }
        
        int[] ans = new int[n];
        int j = 0;
        boolean used = false;
        for (int i = 0; i < m && j < n; i++) {
            if (word1.charAt(i) == word2.charAt(j)) {
                if (!used || last[j + 1] > i) {
                    ans[j] = i;
                    j++;
                }
            } else {
                if (!used && last[j + 1] > i) {
                    ans[j] = i;
                    j++;
                    used = true; // Consumed the single allowed character mismatch
                }
            }
        }
        
        return j == n ? ans : new int[0];
    }
}