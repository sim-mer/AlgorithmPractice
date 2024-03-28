import java.io.*;
import java.util.*;

class Main {
    static int[][] map;
    static int size = 2;
    static int remain = 2;
    static int n;
    static int[][] next = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static int answer = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        Node start = new Node(-1, -1, 0);

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 9) {
                    start = new Node(i, j, 0);
                    continue;
                }
                map[i][j] = temp;
            }
        }

        while(true){
            Node find = bfs(start);
            if(find == null) break;
            find.dis = 0;
            start = find;
        }

        System.out.println(answer);
        br.close();
    }

    private static Node bfs(Node start){
        Deque<Node> nodeQ = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];

        nodeQ.addLast(start);
        visited[start.x][start.y] = true;

        int ans = Integer.MAX_VALUE;
        Node temp = null;


        while(!nodeQ.isEmpty()){
            Node current = nodeQ.pollFirst();

            for(int i = 0; i < 4; i++){
                int dx = current.x + next[i][0];
                int dy = current.y + next[i][1];

                if(check(dx, dy) && !visited[dx][dy]){
                    if(current.dis + 1 > ans) {
                        remain--;
                        map[temp.x][temp.y] = 0;
                        if(remain == 0){
                            size += 1;
                            remain = size;
                        }
                        answer += ans;
                        return temp;
                    }
                    if(map[dx][dy] < size && map[dx][dy] != 0){
                        if(temp == null) {
                            temp = new Node(dx, dy, current.dis + 1);
                            ans = current.dis + 1;
                        }
                        else if(temp.x > dx){
                            temp = new Node(dx, dy, current.dis + 1);
                            ans = current.dis + 1;
                        }
                        else if(temp.x == dx && temp.y > dy){
                            temp = new Node(dx, dy, current.dis + 1);
                            ans = current.dis + 1;
                        }
                    }
                    visited[dx][dy] = true;
                    nodeQ.addLast(new Node(dx, dy, current.dis + 1));
                }
            }
        }
        if(temp == null) return null;
        remain--;
        map[temp.x][temp.y] = 0;
        if(remain == 0){
            size += 1;
            remain = size;
        }
        answer += ans;
        return temp;
    }

    private static boolean check(int dx, int dy){
        return dx >= 0 && dx < n && dy >= 0 && dy < n && map[dx][dy] <= size;
    }

}

class Node{
    int x;
    int y;
    int dis;
    public Node(int x, int y, int dis){
        this.x = x;
        this.y = y;
        this.dis = dis;
    }
}