import java.util.*;
class Solution {    
    boolean[] visited;

    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        
        int answer = 0;
        for(int i = 0; i < n; i++){
            if(visited[i]) continue;
            dfs(i, computers);
            answer++;
        }
        return answer;
    }
    
    private void dfs(int start, int[][] computers) {
        if(visited[start]) return;
        
        visited[start] = true;
        
        for(int i = 0; i < computers.length; i++) {
            if(i == start) continue;
            if(computers[start][i] == 1) {
                dfs(i, computers);
            }
        }
        
    }
}