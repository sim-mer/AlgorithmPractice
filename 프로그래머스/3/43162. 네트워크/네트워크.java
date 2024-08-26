import java.util.*;
class Solution {    
    boolean[] visited;

    public int solution(int n, int[][] computers) {
        List<Integer>[] graph = new ArrayList[n];
        visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }        
        
        for (int i = 0; i < computers.length; i++) {
            for (int j = 0; j < n; j++) {
                if(computers[i][j] == 1 && i != j) {
                    graph[i].add(j);
                }
            }
        }
        
        
        int answer = 0;
        for(int i = 0; i < n; i++){
            if(visited[i]) continue;
            dfs(i, graph);
            answer++;
        }
        return answer;
    }
    
    private void dfs(Integer start, List<Integer>[] graph) {
        if(visited[start]) return;
        
        visited[start] = true;
        
        for(Integer node : graph[start]) {
            if(start == node) continue;
            dfs(node, graph);
        }
        
    }
}