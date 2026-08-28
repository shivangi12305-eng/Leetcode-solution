class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Check if a palindromic permutation is possible
        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }

        if (oddCount > 1 || (oddCount == 1 && n % 2 == 0)) {
            return "";
        }

        int m = n / 2;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        // Case 1: Try forming the exact first half of target
        int[] curCount = halfCount.clone();
        boolean canMatchTargetPrefix = true;
        for (int i = 0; i < m; i++) {
            int ch = target.charAt(i) - 'a';
            if (curCount[ch] > 0) {
                curCount[ch]--;
            } else {
                canMatchTargetPrefix = false;
                break;
            }
        }

        if (canMatchTargetPrefix) {
            String exactCandidate = buildPalindrome(target.substring(0, m), n % 2 != 0, midChar);
            if (exactCandidate.compareTo(target) > 0) {
                return exactCandidate;
            }
        }

        // Case 2: Find the largest prefix index i where we can increment target.charAt(i)
        // Check how far target's prefix can be matched
        int[] prefixCount = halfCount.clone();
        int maxMatch = 0;
        while (maxMatch < m && prefixCount[target.charAt(maxMatch) - 'a'] > 0) {
            prefixCount[target.charAt(maxMatch) - 'a']--;
            maxMatch++;
        }

        for (int i = maxMatch; i >= 0; i--) {
            // Recompute the remaining counts for prefix target[0..i-1]
            int[] rem = halfCount.clone();
            for (int j = 0; j < i; j++) {
                rem[target.charAt(j) - 'a']--;
            }

            if (i < m) {
                int targetChar = target.charAt(i) - 'a';
                // Try to find the smallest char strictly greater than target[i]
                for (int c = targetChar + 1; c < 26; c++) {
                    if (rem[c] > 0) {
                        StringBuilder firstHalf = new StringBuilder();
                        for (int j = 0; j < i; j++) {
                            firstHalf.append(target.charAt(j));
                        }
                        firstHalf.append((char) ('a' + c));
                        rem[c]--;

                        // Fill the rest with smallest available characters
                        for (int k = 0; k < 26; k++) {
                            while (rem[k] > 0) {
                                firstHalf.append((char) ('a' + k));
                                rem[k]--;
                            }
                        }

                        return buildPalindrome(firstHalf.toString(), n % 2 != 0, midChar);
                    }
                }
            }
        }

        return "";
    }

    private String buildPalindrome(String firstHalf, boolean hasOddMid, char midChar) {
        StringBuilder sb = new StringBuilder(firstHalf);
        if (hasOddMid) {
            sb.append(midChar);
        }
        for (int i = firstHalf.length() - 1; i >= 0; i--) {
            sb.append(firstHalf.charAt(i));
        }
        return sb.toString();
    }
}