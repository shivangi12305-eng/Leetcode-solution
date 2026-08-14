class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int totalOnes = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                totalOnes++;
            }
        }
        // Split the string into consecutive blocks of identical characters
        java.util.List<int[]> blocks = new java.util.ArrayList<>();
        int n = s.length();
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            // {character: 0 or 1, length}
            blocks.add(new int[]{s.charAt(i) - '0', j - i});
            i = j;
        }
        int maxGain = 0;
        // Check every 1-block that is surrounded by two 0-blocks
        for (int k = 1; k < blocks.size() - 1; k++) {
            if (blocks.get(k)[0] == 1 && blocks.get(k - 1)[0] == 0 && blocks.get(k + 1)[0] == 0) {
                int zerosMerged = blocks.get(k - 1)[1] + blocks.get(k + 1)[1];
                maxGain = Math.max(maxGain, zerosMerged);
            }
        }
        return totalOnes + maxGain;
    }
}