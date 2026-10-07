class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0, rightRem = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) {
                    leftRem--;
                } else {
                    rightRem++;
                }
            }
        }
        Set<String> result = new HashSet<>();
        backtrack(s, 0, 0, 0, leftRem, rightRem, new StringBuilder(), result);
        return new ArrayList<>(result);
    }
    private void backtrack(String s, int index, int openCount, int closeCount,
                           int leftRem, int rightRem, StringBuilder expression,
                           Set<String> result) {
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0) {
                result.add(expression.toString());
            }
            return;
        }
        char currentChar = s.charAt(index);
        int length = expression.length();
        if (currentChar == '(' && leftRem > 0) {
            backtrack(s, index + 1, openCount, closeCount,
                    leftRem - 1, rightRem, expression, result);
        } else if (currentChar == ')' && rightRem > 0) {
            backtrack(s, index + 1, openCount, closeCount,
                    leftRem, rightRem - 1, expression, result);
        }
        expression.append(currentChar);
        if (currentChar != '(' && currentChar != ')') {
            backtrack(s, index + 1, openCount, closeCount,
                    leftRem, rightRem, expression, result);
        } else if (currentChar == '(') {
            backtrack(s, index + 1, openCount + 1, closeCount,
                    leftRem, rightRem, expression, result);
        } else if (openCount > closeCount) {
            backtrack(s, index + 1, openCount, closeCount + 1,
                    leftRem, rightRem, expression, result);
        }
        expression.setLength(length);
    }
}