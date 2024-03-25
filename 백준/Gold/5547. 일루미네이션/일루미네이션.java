import java.io.*;
import java.util.*;

class Main {
    static boolean[][] map;
    static boolean[][] visited;
    static int[][] odd = {{0, -1}, {1, -1}, {-1, 0}, {1, 0}, {0, 1}, {1, 1}};
    static int[][] even = {{-1, -1}, {0, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}};
    static int count = 0;
    static int w, h;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        w = Integer.parseInt(s[0]);
        h = Integer.parseInt(s[1]);
        
        map = new boolean[h+2][w+2];
        visited = new boolean[h+2][w+2];
        
        for(int i = 1; i < h + 1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j < w + 1; j++){
                String temp = st.nextToken();
                if(temp.equals("1")) map[i][j] = true;
            }
        }
        
        dfs(0, 0);
        System.out.println(count);
    }
    
    private static void dfs(int x, int y) {
        if(visited[y][x]) return;
        
        visited[y][x] = true;
        
        if(y % 2 == 0){
            for(int[] next : even) {
                int nextX = x + next[0];
                int nextY = y + next[1];
                if(check(nextX, nextY)) continue;
                if(map[nextY][nextX]) {
                    count += 1;
                    continue;
                }
                dfs(nextX, nextY);
            }
        }
        else {
            for(int[] next : odd) {
                int nextX = x + next[0];
                int nextY = y + next[1];
                if(check(nextX, nextY)) continue;
                if(map[nextY][nextX]) {
                    count += 1;
                    continue;
                }
                dfs(nextX, nextY);
            }
        }
    }
    
    private static boolean check(int x, int y){
        return x < 0 || y < 0 || x >= w+2 || y >= h+2;
    }
}