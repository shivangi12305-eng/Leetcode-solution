class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        
        int[][] sorted = new int[n][4];
        for (int i = 0; i < n; i++) {
            sorted[i][0] = intervals.get(i).get(0);
            sorted[i][1] = intervals.get(i).get(1);
            sorted[i][2] = intervals.get(i).get(2);
            sorted[i][3] = i;
        }
        
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[1], b[1]));
        
        long[][] dpWeight = new long[5][n + 1];
        List<Integer>[][] dpIndices = new ArrayList[5][n + 1];
        
        for (int k = 0; k <= 4; k++) {
            for (int i = 0; i <= n; i++) {
                dpIndices[k][i] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= n; i++) {
            int l = sorted[i - 1][0];
            int r = sorted[i - 1][1];
            int w = sorted[i - 1][2];
            int origIdx = sorted[i - 1][3];
            
            int prev = binarySearch(sorted, i - 1, l);

            for (int k = 1; k <= 4; k++) {
                long weightNoTake = dpWeight[k][i - 1];
                List<Integer> indicesNoTake = dpIndices[k][i - 1];

                long weightTake = dpWeight[k - 1][prev] + w;
                List<Integer> indicesTake = new ArrayList<>(dpIndices[k - 1][prev]);
                indicesTake.add(origIdx);
                Collections.sort(indicesTake);

                if (weightTake > weightNoTake) {
                    dpWeight[k][i] = weightTake;
                    dpIndices[k][i] = indicesTake;
                } else if (weightNoTake > weightTake) {
                    dpWeight[k][i] = weightNoTake;
                    dpIndices[k][i] = indicesNoTake;
                } else {
                    if (isLexicographicallySmaller(indicesTake, indicesNoTake)) {
                        dpWeight[k][i] = weightTake;
                        dpIndices[k][i] = indicesTake;
                    } else {
                        dpWeight[k][i] = weightNoTake;
                        dpIndices[k][i] = indicesNoTake;
                    }
                }
            }
        }

        long maxScore = -1;
        List<Integer> bestIndices = new ArrayList<>();

        for (int k = 1; k <= 4; k++) {
            if (dpWeight[k][n] > maxScore) {
                maxScore = dpWeight[k][n];
                bestIndices = dpIndices[k][n];
            } else if (dpWeight[k][n] == maxScore) {
                if (isLexicographicallySmaller(dpIndices[k][n], bestIndices)) {
                    bestIndices = dpIndices[k][n];
                }
            }
        }

        int[] result = new int[bestIndices.size()];
        for (int i = 0; i < bestIndices.size(); i++) {
            result[i] = bestIndices.get(i);
        }
        return result;
    }

    private int binarySearch(int[][] sorted, int rightBound, int targetL) {
        int low = 0, high = rightBound - 1;
        int ans = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (sorted[mid][1] < targetL) {
                ans = mid + 1;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean isLexicographicallySmaller(List<Integer> a, List<Integer> b) {
        if (a.isEmpty()) return false;
        if (b.isEmpty()) return true;
        int minLen = Math.min(a.size(), b.size());
        for (int i = 0; i < minLen; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }
        return a.size() < b.size();
    }
}