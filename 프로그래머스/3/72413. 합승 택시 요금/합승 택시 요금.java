import java.util.ArrayList;
import java.util.PriorityQueue;

class Solution {
    int[][] costBoard;
    ArrayList<Node>[] graph;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        costBoard = new int[n + 1][n + 1];
        graph = new ArrayList[n + 1];

        int answer = Integer.MAX_VALUE;

        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            for(int j = 1; j <= n; j++) {
                costBoard[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int cost = fare[2];

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        for(int i = 1; i <= n; i++) {
            dijkstra(i);
        }

        for(int i = 1; i <= n; i++) {
            answer = Math.min(answer, costBoard[s][i] + costBoard[i][a] + costBoard[i][b]);
        }

        return answer;
    }

    private void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        boolean[] visited = new boolean[graph.length];

        pq.add(new Node(start, 0));
        costBoard[start][start] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int vertex = node.vertex;
            int cost = node.cost;

            if(visited[vertex]) continue;
            visited[vertex] = true;

            for(Node next : graph[vertex]) {
                int nextVertex = next.vertex;
                int nextCost = next.cost;

                if(costBoard[start][nextVertex] > cost + nextCost) {
                    costBoard[start][nextVertex] = cost + nextCost;
                    pq.add(new Node(nextVertex, cost + nextCost));
                }
            }
        }

    }

    class Node {
        int vertex;
        int cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
}