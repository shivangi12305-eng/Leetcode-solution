class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n=img1.length;
        List<int[]> p1=new ArrayList<>();
        List<int[]> p2=new ArrayList<>();
        for (int r=0;r < n;r++) {
            for (int c=0;c<n;c++) {
                if (img1[r][c]==1) p1.add(new int[]{r, c});
                if (img2[r][c]==1) p2.add(new int[]{r, c});
            }
        }
        Map<String, Integer> count=new HashMap<>();
        int maxOverlap=0;

        for (int[] a:p1) {
            for (int[] b:p2) {
                String key =(b[0] - a[0]) + "," + (b[1] - a[1]);
                int current =count.getOrDefault(key, 0) + 1;
                count.put(key, current);
                maxOverlap=Math.max(maxOverlap, current);
            }
        }
        return maxOverlap;
    }
}