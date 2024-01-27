import java.util.*;
class Solution {
    int answer = 0;
    int[][] edges;
    Node[] nodes;
    boolean[] visited;

    public int solution(int[] info, int[][] edges) {
        nodes = new Node[info.length];
        this.edges = edges;
        visited = new boolean[info.length];

        for(int i = 0; i < info.length; i++) {
            nodes[i] = new Node(i, info[i]);
        }

        dfs(nodes[0], 0, 0);
        return answer;
    }

    private void dfs(Node root, int sheep, int wolf) {
        if(root.info == 0) {
            sheep++;
            answer = Math.max(answer, sheep);
        } else {
            wolf++;
        }

        if(wolf >= sheep) return;

        visited[root.vertex] = true;

        for(int[] edge : edges) {
            if(visited[edge[0]] && !visited[edge[1]]) {
                dfs(nodes[edge[1]], sheep, wolf);
                visited[edge[1]] = false;
            }
        }
    }

    class Node {
        int vertex;
        int info;
        public Node(int vertex, int info) {
            this.vertex = vertex;
            this.info = info;
        }
    }
}