class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        String ans = "";
        int ones = 0, l = 0, n = s.length();

        for (int r = 0; r < n; r++) {
            if (s.charAt(r) == '1') ones++;

            while (ones == k) {
                String sub = s.substring(l, r + 1);
                if (ans.isEmpty() || sub.length() < ans.length() || 
                   (sub.length() == ans.length() && sub.compareTo(ans) < 0)) {
                    ans = sub;
                }
                
                if (s.charAt(l) == '1') ones--;
                l++;
            }
        }
        return ans;
    }
}