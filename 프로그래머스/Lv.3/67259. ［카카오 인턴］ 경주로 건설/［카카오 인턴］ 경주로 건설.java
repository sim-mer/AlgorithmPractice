import java.util.*;
class Solution {
    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};

    int[][] board;
    int[][][] costBoard;

    public int solution(int[][] board) {
        this.board = board;
        costBoard = new int[board.length][board.length][4];

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                for(int k = 0; k < 4; k++) {
                    costBoard[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        bfs();

        return Arrays.stream(costBoard[board.length - 1][board.length - 1]).min().getAsInt();
    }


    private void bfs() {
        Deque<Node> queue = new ArrayDeque<>();

        queue.add(new Node(0, 0, -1));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;
            int dir = node.dir;

            if(x == board.length - 1 && y == board.length - 1) continue;

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= board.length || ny < 0 || ny >= board.length) continue;
                if(board[nx][ny] == 1) continue;

                int newCost;
                if(dir == -1) {
                    newCost = 100;
                } else if(dir == i) {
                    newCost = costBoard[x][y][dir] + 100;
                } else {
                    newCost = costBoard[x][y][dir] + 600;
                }

                if(costBoard[nx][ny][i] < newCost) continue;
                costBoard[nx][ny][i] = newCost;
                queue.add(new Node(nx, ny, i));
            }
        }
    }

    class Node {
        int x;
        int y;
        int dir;

        public Node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}