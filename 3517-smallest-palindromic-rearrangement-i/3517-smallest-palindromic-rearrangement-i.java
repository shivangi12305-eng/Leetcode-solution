class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        int halfLen = n/2;
        char[] half = s.substring(0, halfLen).toCharArray();
        Arrays.sort(half);
        String firstHalf = new String(half);
        String secondHalf = new StringBuilder(firstHalf).reverse().toString();
        if(n%2!=0){
            return firstHalf + s.charAt(halfLen)+  secondHalf;
        }else {
            return firstHalf + secondHalf;
        }
    }
}