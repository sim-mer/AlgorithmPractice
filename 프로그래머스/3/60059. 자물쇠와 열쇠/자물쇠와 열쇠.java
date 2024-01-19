import java.util.Arrays;
class Solution {
    boolean[][] check;
    int M, N;
    public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;
        check = new boolean[2 * M + N - 2][2 * M + N - 2];

        for(int i = 0; i < lock.length; i++) {
            for(int j = 0; j < lock.length; j++) {
                int k = M - 1;
                if(lock[i][j] == 1) check[i + k][j + k] = true;
            }
        }

        for(int r = 0; r < 4; r++) {
            for(int i = 0; i < M + N - 1; i++) {
                for(int j = 0; j < M + N - 1; j++) {
                    if(insert(i, j, key)) return true; //로테이트 추가
                }
            }
            rotate(key);
        }
        return false;
    }

    private boolean insert(int a, int b, int[][] key) {
        int rows = check.length;
        boolean[][] copyCheck = new boolean[rows][];

        for(int i = 0; i < rows; i++) {
            copyCheck[i] = Arrays.copyOf(check[i], check[i].length);
        }

        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                if(key[i][j] == 1 && copyCheck[i + a][j + b]) return false;
                if(key[i][j] == 0 && !copyCheck[i + a][j + b]) continue;
                copyCheck[i + a][j + b] = true;
            }
        }

        return checkCheck(copyCheck);
    }

    private boolean checkCheck(boolean[][] copyCheck) {
        for(int i = M - 1; i < N + M - 1; i++) {
            for(int j = M - 1; j < N + M - 1; j++) {
                if(copyCheck[i][j]) continue;
                return false;
            }
        }
        return true;
    }

    private void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }
}