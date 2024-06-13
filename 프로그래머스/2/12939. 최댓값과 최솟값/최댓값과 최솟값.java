import java.util.*;
class Solution {
    public String solution(String s) {
        String[] sArray = s.split(" ");
        
        int[] intArray = Arrays.stream(sArray)
            .mapToInt(Integer::parseInt)
            .toArray();
        
        Arrays.sort(intArray);
        
        StringBuilder sb = new StringBuilder();
        sb.append(intArray[0]);
        sb.append(" ");
        sb.append(intArray[intArray.length - 1]);
        
        return sb.toString();
    }
}