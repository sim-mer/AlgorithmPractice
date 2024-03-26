import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());

            boolean[][] map = new boolean[n+2][n+2];
            boolean[][] appleMap = new boolean[n+2][n+2];
            String[][] directionMap = new String[n+2][n+2];
            directionMap[1][1] = "R";

            for(int i = 0; i < n+2; i++){
                if(i == 0 || i == n+1){
                    for(int j = 0; j < n+2; j++) map[i][j] = true;
                    continue;
                }
                map[i][0] = true; map[i][n+1] = true;
            }

            int k = Integer.parseInt(br.readLine());

            while(k-- > 0){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                appleMap[x][y] = true;
            }

            Map<Integer, String> change = new HashMap<>();

            int l = Integer.parseInt(br.readLine());

            while(l-- > 0){
                StringTokenizer st = new StringTokenizer(br.readLine());
                Integer time = Integer.valueOf(st.nextToken());
                String direction = st.nextToken();
                change.put(time, direction);
            }

            int answer = 0;
            int[] head = {1, 1};
            int[] tail = {1, 1};
            map[1][1] = true;
            String direction = "R";

            while(true){
                if(change.containsKey(answer)){//방향전환 여부 체크
                    direction = nextDirection(direction, change.get(answer));
                    directionMap[head[1]][head[0]] = direction;
                }
                answer++; //현재 시간
                next(head, direction); //헤드를 다음좌표로 옮김

                if(map[head[1]][head[0]]) break;

                directionMap[head[1]][head[0]] = direction;
                map[head[1]][head[0]] = true;

                if(appleMap[head[1]][head[0]]) {
                    appleMap[head[1]][head[0]] = false;
                    continue;
                } //사과가 있으면 꼬리 안움직임

                map[tail[1]][tail[0]] = false;
                next(tail, directionMap[tail[1]][tail[0]]);
            }

            System.out.println(answer);
            br.close();
            }

    private static void next(int[] current, String direction){
            if(direction.equals("R")) {
                current[0] += 1;
                return;
            }
            if(direction.equals("L")) {
                current[0] -= 1;
                return;
            }
            if(direction.equals("U")) {
                current[1] -= 1;
                return;
            }
            if(direction.equals("D")) {
                current[1] += 1;
            }
    }

    private static String nextDirection(String preDirection, String C){
        if(C.equals("L")){
            if(preDirection.equals("R")) return "U";
            if(preDirection.equals("L")) return "D";
            if(preDirection.equals("U")) return "L";
            if(preDirection.equals("D")) return "R";
        }
        if(preDirection.equals("R")) return "D";
        if(preDirection.equals("L")) return "U";
        if(preDirection.equals("U")) return "R";
        if(preDirection.equals("D")) return "L";
        return preDirection;
    }
}