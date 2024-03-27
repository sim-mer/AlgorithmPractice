import java.util.*;
import java.io.*;

class Main {
    static int[][] map;
    static List<Virus> virusList = new ArrayList<>();
    static int maxSafeArea;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        map = new int[n][m];
        maxSafeArea = n * m;

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                int temp = Integer.parseInt(st.nextToken());
                map[i][j] = temp;
                if(temp == 1) maxSafeArea--;
                if(temp == 2) virusList.add(new Virus(i, j));
            }
        }
        
        wall(0);

        System.out.println(answer);
    }
    
    static void wall(int cnt) {
        if(cnt == 3) {
            int safeArea = spreadVirus();
            if(answer < safeArea) answer = safeArea;
            return;
        }

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j] == 0) {
                    map[i][j] = 1;
                    wall(cnt + 1);
                    map[i][j] = 0;
                }
            }
        }
    }
    
    static int spreadVirus(){
        Deque<Virus> virusQ = new ArrayDeque<>();
        int virusCnt = 0;
        boolean[][] visited = new boolean[map.length][map[0].length];
        for(Virus v : virusList) {
            virusQ.addLast(v);
            visited[v.x][v.y] = true;
            virusCnt++;
        }
        
        while(!virusQ.isEmpty()) {
            Virus v = virusQ.pollFirst();
            int x = v.x;
            int y = v.y;
            
            if(x - 1 >= 0 && map[x - 1][y] == 0 && !visited[x - 1][y]) {
                visited[x - 1][y] = true;
                virusCnt++;
                virusQ.addLast(new Virus(x - 1, y));
            }
            if(x + 1 < map.length && map[x + 1][y] == 0 && !visited[x + 1][y]) {
                visited[x + 1][y] = true;
                virusCnt++;
                virusQ.addLast(new Virus(x + 1, y));
            }
            if(y - 1 >= 0 && map[x][y - 1] == 0 && !visited[x][y - 1]) {
                visited[x][y - 1] = true;
                virusCnt++;
                virusQ.addLast(new Virus(x, y - 1));
            }
            if(y + 1 < map[0].length && map[x][y + 1] == 0 && !visited[x][y + 1]) {
                visited[x][y + 1] = true;
                virusCnt++;
                virusQ.addLast(new Virus(x, y + 1));
            }
        }
        
        return maxSafeArea - virusCnt - 3;
    }
}

class Virus {
    int x;
    int y;
    public Virus(int x, int y) {
        this.x = x;
        this.y = y;
    }
}