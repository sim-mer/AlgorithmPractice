class Solution {
    int n, m;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        StringBuilder answer = new StringBuilder();
        this.n = n;
        this.m = m;

        int[][] dir = {
            {1, 0}, // d
            {0, -1}, // l
            {0, 1}, // r
            {-1, 0} // u
        };
        int[] dlru = new int[4];
        String[] dlruStr = {"d", "l", "r", "u"};

        dlru[0] = Math.max((r - x), 0);
        dlru[3] = Math.max((x - r), 0);
        dlru[2] = Math.max((c - y), 0);
        dlru[1] = Math.max((y - c), 0);
        int dis = dlru[0] + dlru[1] + dlru[2] + dlru[3];

        int diff = k - dis;
        if (dis > k) return "impossible";
        if (diff % 2 == 1) return "impossible";

        if(diff == 0) {
            answer.append("d".repeat(dlru[0])).append("l".repeat(dlru[1])).append("r".repeat(dlru[2])).append("u".repeat(dlru[3]));
            return answer.toString();
        }

        diff /= 2;

        while(true) {
            for(int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                if(isPossible(nx, ny)) {
                    x = nx;
                    y = ny;
                    answer.append(dlruStr[i]);
                    if(dlru[i] > 0) dlru[i]--;
                    else {
                        diff--;
                        if(i == 0) dlru[3]++;
                        else if(i == 1) dlru[2]++;
                        else if(i == 2) dlru[1]++;
                        else dlru[0]++;
                    }
                    break;
                }
            }
            if(diff == 0) {
                answer.append("d".repeat(dlru[0])).append("l".repeat(dlru[1])).append("r".repeat(dlru[2])).append("u".repeat(dlru[3]));
                return answer.toString();
            }
        }
    }

    private boolean isPossible(int x, int y) {
        return x > 0 && x <= n && y > 0 && y <= m;
    }
}