class Solution {
    public boolean detectCapitalUse(String word) {
        // intialize variable for counting
        int uppercase = 0;
        //traverse the string  only 1 time from starting to end 
        //and check of chaptipal char
        for(int i=0; i<word.length();i++){
            if(Character.isUpperCase(word.charAt(i))){
                //inc by 1 
                uppercase++;

            }
        }
        // take decision on the basis of the approach
        return uppercase == word.length()
        || uppercase ==0 
        || (uppercase==1 && Character.isUpperCase(word.charAt(0)));
    }
}