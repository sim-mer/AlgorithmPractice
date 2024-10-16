class Solution {
    public int[] solution(int n, int s) {
        if(n > s) return new int[]{-1};
        
        int[] answer = new int[n];
        int mod = s % n;
        int q = s / n;
        for(int i = 0; i < n; i++) {
            answer[i] = q;
        }
        if(mod > 0) {
            for(int i = n - mod; i < n; i++) {
                answer[i]++;
            }
        }
        
        return answer;
    }
}