class Solution {
    public String reverseParentheses(String s) {
        Deque<Character> stack=new ArrayDeque<>();
        for(char c:s.toCharArray()){
            if(c==')'){
                StringBuilder sb=new StringBuilder();
                while(!stack.isEmpty()&&stack.peek()!='(')
                    sb.append(stack.pop());
                if(!stack.isEmpty()) stack.pop();
                for(int i=0;i<sb.length();i++)
                    stack.push(sb.charAt(i));
            }else{
                stack.push(c);
            }
        }
        StringBuilder result=new StringBuilder();
        while(!stack.isEmpty())
            result.append(stack.pop());
        return result.reverse().toString();
    }
}