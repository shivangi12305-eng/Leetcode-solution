class Solution {
    class SegmentTree {
        int n;
        int[] maxLen;
        int[] prefLen;
        int[] suffLen;
        char[] leftChar;
        char[] rightChar;

        public SegmentTree(String s) {
            this.n = s.length();
            maxLen = new int[4 * n];
            prefLen = new int[4 * n];
            suffLen = new int[4 * n];
            leftChar = new char[4 * n];
            rightChar = new char[4 * n];
            build(0, 0, n - 1, s);
        }

        private void merge(int node, int l, int r) {
            int mid = l + (r - l) / 2;
            int leftChild = 2 * node + 1;
            int rightChild = 2 * node + 2;

            leftChar[node] = leftChar[leftChild];
            rightChar[node] = rightChar[rightChild];

            maxLen[node] = Math.max(maxLen[leftChild], maxLen[rightChild]);
            prefLen[node] = prefLen[leftChild];
            suffLen[node] = suffLen[rightChild];

            int leftLen = mid - l + 1;
            int rightLen = r - mid;

            if (rightChar[leftChild] == leftChar[rightChild]) {
                maxLen[node] = Math.max(maxLen[node], suffLen[leftChild] + prefLen[rightChild]);

                if (prefLen[leftChild] == leftLen) {
                    prefLen[node] = leftLen + prefLen[rightChild];
                }
                if (suffLen[rightChild] == rightLen) {
                    suffLen[node] = rightLen + suffLen[leftChild];
                }
            }
        }

        private void build(int node, int l, int r, String s) {
            if (l == r) {
                maxLen[node] = 1;
                prefLen[node] = 1;
                suffLen[node] = 1;
                leftChar[node] = s.charAt(l);
                rightChar[node] = s.charAt(l);
                return;
            }
            int mid = l + (r - l) / 2;
            build(2 * node + 1, l, mid, s);
            build(2 * node + 2, mid + 1, r, s);
            merge(node, l, r);
        }

        public void update(int node, int l, int r, int idx, char ch) {
            if (l == r) {
                leftChar[node] = ch;
                rightChar[node] = ch;
                return;
            }
            int mid = l + (r - l) / 2;
            if (idx <= mid) {
                update(2 * node + 1, l, mid, idx, ch);
            } else {
                update(2 * node + 2, mid + 1, r, idx, ch);
            }
            merge(node, l, r);
        }
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int k = queryIndices.length;
        int[] ans = new int[k];
        SegmentTree tree = new SegmentTree(s);

        for (int i = 0; i < k; i++) {
            tree.update(0, 0, s.length() - 1, queryIndices[i], queryCharacters.charAt(i));
            ans[i] = tree.maxLen[0];
        }

        return ans;
    }
}