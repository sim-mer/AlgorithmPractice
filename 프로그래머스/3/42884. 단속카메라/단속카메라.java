import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes, (arr1, arr2) -> arr1[1] - arr2[1]);
        
        int cam = Integer.MIN_VALUE;
        
        for(int[] route : routes) {
            if(route[0] > cam) {
                answer++;
                cam = route[1];
            }
        }
        
        return answer;
    }
}