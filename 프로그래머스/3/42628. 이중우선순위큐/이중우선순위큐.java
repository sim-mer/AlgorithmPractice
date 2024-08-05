import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        
        for(String op : operations) {
            String[] parseOp = op.split(" ");
            
            if(parseOp[0].equals("I")) {
                Integer v = Integer.valueOf(parseOp[1]);
                
                int c = treeMap.getOrDefault(v, 0) + 1;
                treeMap.put(v, c);
                continue;
            }
            
            if(treeMap.isEmpty()) {
                continue;
            }
            
            if(parseOp[1].equals("1")) {
                Integer maxKey = treeMap.lastKey();
                
                Integer c = treeMap.get(maxKey);
                if(c == 1){
                    treeMap.remove(maxKey);
                }
                else {
                    treeMap.put(maxKey, c - 1);
                }
                continue;
            }
            
            Integer minKey = treeMap.firstKey();
                
            Integer c = treeMap.get(minKey);
            if(c == 1){
                treeMap.remove(minKey);
            }
            else {
                treeMap.put(minKey, c - 1);
            }
        }
        
        if(treeMap.isEmpty()){
            return new int[]{0, 0};
        }
        
        Integer minKey = treeMap.firstKey();
        Integer maxKey = treeMap.lastKey();
        
        return new int[]{maxKey, minKey};
    }
}