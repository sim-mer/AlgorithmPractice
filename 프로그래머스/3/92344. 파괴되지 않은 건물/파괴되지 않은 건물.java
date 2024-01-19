class Solution {
    public int solution(int[][] board, int[][] skill) {
        int[][] map = new int[board.length][board[0].length];

        for(int[] s : skill) {
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int degree = s[5];

            boolean R = r2 + 1 < map.length;
            boolean C = c2 + 1 < map[0].length;

            if(s[0] == 1) {
                map[r1][c1] -= degree;
                if(R) map[r2 + 1][c1] += degree;
                if(C) map[r1][c2 + 1] += degree;
                if(R && C) map[r2 + 1][c2 + 1] -= degree;
                continue;
            }
            map[r1][c1] += degree;
            if(R) map[r2 + 1][c1] -= degree;
            if(C) map[r1][c2 + 1] -= degree;
            if(R && C) map[r2 + 1][c2 + 1] += degree;
        }

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(i - 1 >= 0) map[i][j] += map[i - 1][j];
            }
        }

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(j - 1 >= 0) map[i][j] += map[i][j - 1];
            }
        }

        int answer = 0;

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j] + board[i][j] > 0) answer++;
            }
        }
        return answer;
    }
}