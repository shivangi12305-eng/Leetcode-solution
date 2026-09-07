class Solution {
    public int distinctSubseqII(String s) {
        int MOD=1_000_000_007;
        long[] endsWith= new long[26];
        for (char ch: s.toCharArray()) {
            int idx=ch - 'a';
            long total= 0;
            for (int i= 0; i < 26; i++) {
                total=(total+endsWith[i]) % MOD;
            }
            endsWith[idx]=(total + 1) % MOD;
        }
        long result = 0;
        for (int i=0; i < 26; i++) {
            result=(result + endsWith[i]) % MOD;
        }
        return (int)result;
    }
}