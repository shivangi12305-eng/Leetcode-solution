class Solution {
    boolean isPairMatch(char opening, char closing){
        return(opening=='('&& closing==')')
        ||(opening=='['&& closing==']')
        ||(opening=='{'&& closing=='}');
    }
    public boolean isValid(String s) {
      //  java.util.*
        Stack<Character> stack=new Stack<>();
        for(char bracket: s.toCharArray()){
            if (bracket=='('
            || bracket=='['||
            bracket=='{'){
                stack.push(bracket);

            }   
        //closing coming
            else if(stack.isEmpty()
            || !isPairMatch(stack.pop(),bracket)){
                return false;
            }
                 }
                 return stack.isEmpty();
    }
}