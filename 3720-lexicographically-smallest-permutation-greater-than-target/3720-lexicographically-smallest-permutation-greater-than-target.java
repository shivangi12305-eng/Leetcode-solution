class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] totalCount = new int[26];
        for (char c : s.toCharArray()) {
            totalCount[c - 'a']++;
        }

        for (int i = n - 1; i >= 0; i--) {
            int[] prefixCount = new int[26];
            boolean possiblePrefix = true;

            for (int j = 0; j < i; j++) {
                int idx = target.charAt(j) - 'a';
                prefixCount[idx]++;
                if (prefixCount[idx] > totalCount[idx]) {
                    possiblePrefix = false;
                    break;
                }
            }

            if (!possiblePrefix) {
                continue;
            }

            int[] remCount = new int[26];
            for (int k = 0; k < 26; k++) {
                remCount[k] = totalCount[k] - prefixCount[k];
            }

            int targetChar = target.charAt(i) - 'a';
            int chosenChar = -1;

            for (int c = targetChar + 1; c < 26; c++) {
                if (remCount[c] > 0) {
                    chosenChar = c;
                    break;
                }
            }

            if (chosenChar != -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(target, 0, i);
                sb.append((char) ('a' + chosenChar));
                remCount[chosenChar]--;

                for (int c = 0; c < 26; c++) {
                    while (remCount[c] > 0) {
                        sb.append((char) ('a' + c));
                        remCount[c]--;
                    }
                }

                return sb.toString();
            }
        }

        return "";
    }
}