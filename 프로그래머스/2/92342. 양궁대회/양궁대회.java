import java.util.Arrays;
class Solution {
    int[] info;
    int[] answer = new int[11];
    int diff = 0;
    public int[] solution(int n, int[] info) {
        this.info = info;

        dfs(n, new int[11]);

        if(diff == 0) {
            answer = new int[]{-1};
        }

        return answer;
    }

    private void dfs(int depth, int[] lion) {
        if(depth == 0) {
            calcScore(lion);
            return;
        }

        for(int i = 0; i < 11; i++) {
            if(i == 10){
                lion[i] = depth;
                dfs(0, lion);
                lion[i] = 0;
                continue;
            }
            if(lion[i] > info[i]) continue;
            if(depth < info[i] + 1) continue;
            lion[i] = info[i] + 1;
            dfs(depth - info[i] - 1, lion);
            lion[i] = 0;
        }
    }

    private void calcScore(int[] lion) {
        int lionScore = 0;
        int apeachScore = 0;

        for(int i = 0; i < 11; i++) {
            if(lion[i] == 0 && info[i] == 0) continue;
            if(lion[i] <= info[i]) {
                apeachScore += 10 - i;
                continue;
            }
            lionScore += 10 - i;
        }

        int d = lionScore - apeachScore;

        if(d < 0) return;
        if(d < diff) return;
        if(d > diff) {
            diff = lionScore - apeachScore;
            answer = Arrays.copyOf(lion, lion.length);
            return;
        }

        for(int i = 10; i >= 0; i--) {
            if(lion[i] == 0 && info[i] == 0) continue;
            if(lion[i] > answer[i]) {
                answer = Arrays.copyOf(lion, lion.length);
                return;
            }
            if(lion[i] < answer[i]) {
                return;
            }
        }
    }
}