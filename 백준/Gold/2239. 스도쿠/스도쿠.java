import java.io.*;

class Main {
    static int[][] map = new int[9][9];
    static int[][] sector = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
    static int[][] zero = new int[81][2];
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            String s = br.readLine().trim();
            for (int j = 0; j < 9; j++) {
                int temp = s.charAt(j) - '0';
                map[i][j] = temp;
                if (temp == 0) {
                    zero[count][0] = i;
                    zero[count++][1] = j;
                }
            }
        }

        check(0);

        int a = -1, b = -1;
        while (a++ < 8) {
            StringBuilder sb = new StringBuilder();
            while (b++ < 8) {
                sb.append(map[a][b]);
            }
            b = -1;
            System.out.println(sb);
        }

        br.close();
    }

    static boolean check(int depth) {
        if (depth == count) return true;
        int x = zero[depth][0];
        int y = zero[depth][1];
        int[] count = new int[10];

        int a, b;

        for (int i = 0; i < 9; i++) {
            count[map[x][i]]++;
            count[map[i][y]]++;
        }

        if (x < 3) a = 0;
        else if (x < 6) a = 1;
        else a = 2;

        if (y < 3) b = 0;
        else if (y < 6) b = 1;
        else b = 2;

        for (int n : sector[a]) {
            for (int m : sector[b]) {
                count[map[n][m]]++;
            }
        }

        for (int i = 1; i <= 9; i++) {
            if (count[i] > 0) continue;
            map[x][y] = i;
            if (check(depth + 1)) return true;
            map[x][y] = 0;
        }

        return false;
    }
}