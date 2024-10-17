class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        int idx = 1;
        int range = w * 2 + 1;
        
        for(int station : stations) {
            int start = station - w;
            
            if(idx < start) {
                int empty = start - idx;
                answer += empty / range;
                if(empty % range != 0) {
                    answer += 1;
                }
            }
            idx = station + w + 1;
        }
        
        if(idx <= n) {
            int empty = n - idx + 1;
            answer += empty / range;
            if(empty % range != 0) {
                answer += 1;
            }
        }

        return answer;
    }
}