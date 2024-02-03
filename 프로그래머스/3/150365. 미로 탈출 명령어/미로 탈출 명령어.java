class Solution {
    int n, m, x, y;
    final int[][] dir = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    final String[] dlruStr = {"d", "l", "r", "u"};

    StringBuilder answer = new StringBuilder();
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n; this.m = m;
        this.x = x; this.y = y;

        int[] dlru = new int[4];
        dlru[0] = Math.max((r - x), 0);
        dlru[3] = Math.max((x - r), 0);
        dlru[2] = Math.max((c - y), 0);
        dlru[1] = Math.max((y - c), 0);

        int dis = dlru[0] + dlru[1] + dlru[2] + dlru[3];
        int diff = k - dis;
        if (dis > k || diff % 2 == 1) return "impossible";

        diff /= 2;
        func(diff, dlru);

        for(int i = 0; i < 4; i++) {
            answer.append(dlruStr[i].repeat(dlru[i]));
        }
        return answer.toString();
    }

    private void func(int diff, int[] dlru) {
        if(diff == 0) return;
        for(int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if(!isPossible(nx, ny)) continue;

            x = nx; y = ny;
            answer.append(dlruStr[i]);

            if(dlru[i] > 0) {
                dlru[i]--;
                break;
            }
            diff--;
            dlru[3 - i]++;
            break;
        }
        func(diff, dlru);
    }

    private boolean isPossible(int x, int y) {
        return x > 0 && x <= n && y > 0 && y <= m;
    }
}