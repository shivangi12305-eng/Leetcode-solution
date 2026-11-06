class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        
        java.util.Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        java.util.Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        int[] ans = new int[m];
        int[] serverCount = new int[n + 1];
        int activeServers = 0;
        int left = 0, right = 0;
        int logsLen = logs.length;
        
        for (int i = 0; i < m; i++) {
            int qVal = sortedQueries[i][0];
            int qIdx = sortedQueries[i][1];
            int startTime = qVal - x;
            
            while (right < logsLen && logs[right][1] <= qVal) {
                int sId = logs[right][0];
                if (serverCount[sId] == 0) {
                    activeServers++;
                }
                serverCount[sId]++;
                right++;
            }
            
            while (left < logsLen && logs[left][1] < startTime) {
                int sId = logs[left][0];
                serverCount[sId]--;
                if (serverCount[sId] == 0) {
                    activeServers--;
                }
                left++;
            }
            
            ans[qIdx] = n - activeServers;
        }
        
        return ans;
    }
}