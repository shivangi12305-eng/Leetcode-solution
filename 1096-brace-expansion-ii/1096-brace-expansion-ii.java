import java.util.*;
class Solution {
    public List<String> braceExpansionII(String expression) {
        Stack<List<Set<String>>> stack=new Stack<>();
        List<Set<String>> current=new ArrayList<>();
        Set<String> set=new HashSet<>();
        set.add("");
        current.add(set);

        for(int i=0;i<expression.length();i++){
            char c=expression.charAt(i);
            if(Character.isLetter(c)){
                Set<String> next=new HashSet<>();
                for(String s:current.get(current.size()-1))
                    next.add(s+c);
                current.set(current.size()-1,next);
            }else if(c=='{'){
                stack.push(current);
                current=new ArrayList<>();
                Set<String> init=new HashSet<>();
                init.add("");
                current.add(init);
            }else if(c=='}'){
                Set<String> union=new HashSet<>();
                for(Set<String> s:current)
                    union.addAll(s);
                current=stack.pop();
                Set<String> next=new HashSet<>();
                for(String s1:current.get(current.size()-1))
                    for(String s2:union)
                        next.add(s1+s2);
                current.set(current.size()-1,next);
            }else if(c==','){
                Set<String> init=new HashSet<>();
                init.add("");
                current.add(init);
            }
        }

        Set<String> resultSet=new HashSet<>();
        for(Set<String> set2:current)
            resultSet.addAll(set2);
        List<String> result=new ArrayList<>(resultSet);
        Collections.sort(result);
        return result;
    }
}