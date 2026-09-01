class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startR = -1, startC = -1;
        int litterCount = 0;
        int[][] litterIndex = new int[m][n];
        for (int[] row : litterIndex) {
            Arrays.fill(row, -1);
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') {
                    startR = i;
                    startC = j;
                } else if (ch == 'L') {
                    litterIndex[i][j] = litterCount++;
                }
            }
        }
        
        if (litterCount == 0) return 0;
        
        int targetMask = (1 << litterCount) - 1;
        int[][][] bestEnergy = new int[m][n][1 << litterCount];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startR, startC, 0, energy, 0});
        bestEnergy[startR][startC][0] = energy;
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], mask = curr[2], e = curr[3], steps = curr[4];
            
            if (e == 0) continue;
            
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (classroom[nr].charAt(nc) == 'X') continue;
                
                int ne = e - 1;
                char cell = classroom[nr].charAt(nc);
                if (cell == 'R') {
                    ne = energy;
                }
                
                int nmask = mask;
                if (cell == 'L' && litterIndex[nr][nc] != -1) {
                    nmask |= (1 << litterIndex[nr][nc]);
                }
                
                if (nmask == targetMask) {
                    return steps + 1;
                }
                
                if (ne > bestEnergy[nr][nc][nmask]) {
                    bestEnergy[nr][nc][nmask] = ne;
                    queue.offer(new int[]{nr, nc, nmask, ne, steps + 1});
                }
            }
        }
        
        return -1;
    }
}