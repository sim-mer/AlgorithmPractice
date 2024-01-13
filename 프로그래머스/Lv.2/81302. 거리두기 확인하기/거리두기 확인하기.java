import java.util.*;

class Solution {
    int[][] dir = {{0, 1}, {-1, 0}, {0, -1}, {-1, 1}, {-2, 0}, {-1, -1}, {0, -2}};

    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        int idx = 0;

        for(String[] place : places) {
            boolean flag = true;
            for(int i = 0; i < 5; i++) {
                for(int j = 0; j < 5; j++) {
                    if(place[i].charAt(j) == 'P' && !checkP(place, i, j)) {
                        flag = false;
                        break;
                    }
                }
                if(!flag) break;
            }
            if(flag) answer[idx]++;
            idx++;
        }
        return answer;
    }

    private boolean checkP(String[] place, int i, int j) {
        if(i == 0 && j == 0) return true;
        List<String> points = new ArrayList<>();

        for(int[] d : dir) {
            int x = i + d[0];
            int y = j + d[1];
            if(isPositive(x, y)) {
                points.add(place[x].charAt(y) + "");
                continue;
            }
            points.add("");
        }

        if(points.get(1).equals("P") || points.get(2).equals("P")) return false;

        if(points.get(0).equals("O")) {
            if(points.get(3).equals("P")) return false;
        }

        if (points.get(1).equals("O")) {
            for(int k = 3; k < 6; k++)
                if(points.get(k).equals("P")) return false;
        }

        if (points.get(2).equals("O")) {
            for(int k = 5; k < 7; k++)
                if(points.get(k).equals("P")) return false;
        }

        return true;
    }
    private boolean isPositive(int x, int y) {
        return x >= 0 && y >= 0 && x < 5 && y < 5;
    }
}