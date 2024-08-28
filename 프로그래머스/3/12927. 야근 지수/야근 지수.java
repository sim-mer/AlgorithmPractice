import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        Arrays.sort(works);
        for (int i = 0; i < works.length / 2; i++) {
            int temp = works[i];
            works[i] = works[works.length - 1 - i];
            works[works.length - 1 - i] = temp;
        }
        
        int current = 0;
        
        while(true) {
            works[current]--;
            n--;
            
            if(n == 0) break;
            
            if(current == works.length - 1) {
                if(works[current] == 0) return 0;
                current = 0;
                continue;
            }
            
            if(works[current] < works[current + 1]) {
                current++;
                continue;
            }
            
            current = 0;
        }
        
        for(int w : works) {
            answer += (int)Math.pow(w, 2);
        }
        
        return answer;
    }
}