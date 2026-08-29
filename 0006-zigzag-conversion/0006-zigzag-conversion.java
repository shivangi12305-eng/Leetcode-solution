// time Comp.- O(n)
// space comp.-O(n)
// A full zigzag "cycle" consists of cycleLen = 2 * numRows - 2 characters (going down and diagonally up).
// First row ($r = 0$) & Last row ($r = \text{numRows} - 1$): Exactly one character per cycle at index j + r.
// Middle rows ($0 < r < \text{numRows} - 1$): Two characters per cycle:Downward path: j + rUpward diagonal path: j + cycleLen - r
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows[currRow].append(c);
            if (currRow == 0 || currRow == numRows - 1) {
                goingDown = !goingDown;
            }
            currRow += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }
}