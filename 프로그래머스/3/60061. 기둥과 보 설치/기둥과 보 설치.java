import java.util.*;

class Solution {
    List<Structure> structures = new ArrayList<>();
    public int[][] solution(int n, int[][] build_frame) {

        for(int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int type = frame[2];
            int command = frame[3];

            if(command == 1) {
                if(isPossible(x, y, type)) {
                    structures.add(new Structure(x, y, type));
                }
                continue;
            }
            structures.removeIf(s -> s.x == x && s.y == y && s.type == type);
            for(Structure s : structures) {
                if(!isPossible(s.x, s.y, s.type)) {
                    structures.add(new Structure(x, y, type));
                    break;
                }
            }
        }

        structures.sort((o1, o2) -> {
            if(o1.x == o2.x) {
                if(o1.y == o2.y) {
                    return o1.type - o2.type;
                }
                return o1.y - o2.y;
            }
            return o1.x - o2.x;
        });

        int[][] answer = new int[structures.size()][3];
        for(int i = 0; i < structures.size(); i++) {
            answer[i][0] = structures.get(i).x;
            answer[i][1] = structures.get(i).y;
            answer[i][2] = structures.get(i).type;
        }
        return answer;
    }

    private boolean isPossible(int x, int y, int type) {
        if(type == 0) {
            if(y == 0) return true;
            if(structures.contains(new Structure(x, y - 1, 0))) return true;
            if(structures.contains(new Structure(x - 1, y, 1))) return true;
            if(structures.contains(new Structure(x, y, 1))) return true;
            return false;
        }
        if(structures.contains(new Structure(x, y - 1, 0))) return true;
        if(structures.contains(new Structure(x + 1, y - 1, 0))) return true;
        if(structures.contains(new Structure(x - 1, y, 1)) && structures.contains(new Structure(x + 1, y, 1))) return true;
        return false;
    }

    class Structure {
        int x;
        int y;
        int type;
        Structure(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
        
        @Override
        public boolean equals(Object obj) {
            Structure s = (Structure) obj;
            return this.x == s.x && this.y == s.y && this.type == s.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, type);
        }
    }
}