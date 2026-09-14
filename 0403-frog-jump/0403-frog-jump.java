class Solution {
    public boolean canCross(int[] stones) {
        int n=stones.length;
        Map<Integer,Set<Integer>> map=new HashMap<>();
        for(int stone:stones) map.put(stone,new HashSet<>());
        map.get(0).add(0);
        for(int i=0;i<n;i++){
            int currentStone=stones[i];
            Set<Integer> jumps=map.get(currentStone);
            for(int k:jumps){
                for(int step=k-1;step<=k+1;step++){
                    if(step>0&&map.containsKey(currentStone+step))
                        map.get(currentStone+step).add(step);
                }
            }
        }
        return !map.get(stones[n-1]).isEmpty();
    }
}