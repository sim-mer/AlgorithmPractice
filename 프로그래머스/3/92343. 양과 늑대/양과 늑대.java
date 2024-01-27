import java.util.*;
class Solution {
    int answer = 0;
    int[][] edges;
    Node[] nodes;

    public int solution(int[] info, int[][] edges) {
        nodes = new Node[info.length];
        this.edges = edges;

        for(int i = 0; i < info.length; i++) {
            nodes[i] = new Node(i, info[i]);
        }

        for(int[] edge : edges) {
            nodes[edge[0]].childList.add(nodes[edge[1]]);
        }

        a(nodes[0], 0, 0, new boolean[info.length]);
        return answer;
    }

    private void a (Node root, int sheep, int wolf, boolean[] visited) {
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
                a(nodes[edge[1]], sheep, wolf, visited);
                visited[edge[1]] = false;
            }
        }
    }

    class Node {
        int vertex;
        int info;
        List<Node> childList;
        public Node(int vertex, int info) {
            this.vertex = vertex;
            this.info = info;
            this.childList = new ArrayList<>();
        }
    }
}