import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        
        for(String op : operations) {
            String[] parseOp = op.split(" ");
            String operation = parseOp[0];
            String num = parseOp[1];
            
            if(operation.equals("I")) {
                Integer v = Integer.valueOf(num);
                
                int c = treeMap.getOrDefault(v, 0) + 1;
                treeMap.put(v, c);
                continue;
            }
            
            if(treeMap.isEmpty()) {
                continue;
            }
            
            if(num.equals("1")) {
                removeMap(treeMap, treeMap.lastKey());
            }
            else {
                removeMap(treeMap, treeMap.firstKey());
            }
        }
        
        if(treeMap.isEmpty()){
            return new int[]{0, 0};
        }
        
        Integer minKey = treeMap.firstKey();
        Integer maxKey = treeMap.lastKey();
        
        return new int[]{maxKey, minKey};
    }
    
    private void removeMap(TreeMap<Integer, Integer> treeMap, Integer key) {
        Integer c = treeMap.get(key);
        if (c == 1) {
            treeMap.remove(key);
            return;
        }
        treeMap.put(key, c - 1);
    }
}