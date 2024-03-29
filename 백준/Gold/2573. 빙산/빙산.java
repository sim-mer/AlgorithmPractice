import java.io.*;
import java.util.*;

class Main {
    static int n, m;
    static int[][] map;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visited;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        map = new int[n][m];
        
        
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        
        boolean flag = false;
        int answer = 0;
        while(true){
            answer++;
            passYear();
            visited = new boolean[n][m];
            int count = 0;
            for(int i = 1; i < n-1; i++){
                for(int j = 1; j < m-1; j++){
                    if(!visited[i][j] && map[i][j] > 0) {
                        bfs(i, j);
                        count++;
                    }
                }
            }
            
            if(count >= 2) {
                System.out.println(answer);
                return;
            }
            if(count == 0) break;
        }
        
        System.out.println(0);
        
        br.close();
    }
    
    static void passYear(){
        int[][] minus = new int[n][m];
        for(int i = 1; i < n-1; i++){
            for(int j = 1; j < m-1; j++){
                if(map[i][j] > 0){
                    for(int k = 0; k < 4; k++){
                        int dx = i + dir[k][0];
                        int dy = j + dir[k][1];
                        if(map[dx][dy] == 0) minus[i][j]++;
                    }
                }
            }
        }
        
        for(int i = 1; i < n-1; i++){
            for(int j = 1; j < m-1; j++){
                int temp = map[i][j] - minus[i][j];
                if(temp >= 0) map[i][j] = temp;
                else map[i][j] = 0;
            }
        }
    }
    
    static void bfs(int x, int y) {
        int cnt = 0;
        
        Deque<Node> nodeQ = new ArrayDeque<>();
        
        nodeQ.addLast(new Node(x, y));
        visited[x][y] = true;
        
        while(!nodeQ.isEmpty()){
            Node current = nodeQ.pollFirst();
            
            for(int i = 0; i < 4; i++){
                int dx = current.x + dir[i][0];
                int dy = current.y + dir[i][1];
                
                if(!visited[dx][dy] && map[dx][dy] > 0){
                    visited[dx][dy] = true;
                    nodeQ.addLast(new Node(dx, dy));
                }
            }
        }
    }
}

class Node {
    int x;
    int y;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}