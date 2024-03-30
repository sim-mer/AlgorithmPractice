import java.io.*;
import java.util.*;

class Main{
    static int[][] map = new int[9][9];
    static int[][] sector = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
    static List<Node> zeroList = new ArrayList<>();
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 9; i++){
            String s = br.readLine().trim();
            for(int j = 0; j < 9; j++){
                int temp = s.charAt(j) - '0';
                map[i][j] = temp;
                if(temp == 0) zeroList.add(new Node(i, j));
            }
        }
        size = zeroList.size();

        check(0);

        int a = -1, b = -1;
        while(a++ < 8){
            StringBuilder sb = new StringBuilder();
            while(b++ < 8){
                sb.append(map[a][b]);
            }
            b = -1;
            System.out.println(sb);
        }

        br.close();
    }

    static boolean check(int depth){
        if(depth == size) return true;
        Node node = zeroList.get(depth);
        Map<Integer, Integer> count = new HashMap<>();

        int a, b;

        for(int i = 0; i < 9; i++){
            count.put(map[node.x][i], count.getOrDefault(map[node.x][i], 0) + 1);
            count.put(map[i][node.y], count.getOrDefault(map[i][node.y], 0) + 1);
        }

        if(node.x < 3) a = 0;
        else if (node.x < 6) a = 1;
        else a = 2;

        if(node.y < 3) b = 0;
        else if (node.y < 6) b = 1;
        else b = 2;

        for(int n : sector[a]){
            for(int m : sector[b]){
                count.put(map[n][m], count.getOrDefault(map[n][m], 0) + 1);
            }
        }

        count.remove(0);

        for(int i = 1; i <= 9; i++){
            if(count.containsKey(i)) continue;
            map[node.x][node.y] = i;
            if(check(depth + 1)) return true;
            map[node.x][node.y] = 0;
        }

        return false;
    }
}

class Node {
    int x;
    int y;
    public Node (int x, int y){
        this.x = x;
        this.y = y;
    }
}