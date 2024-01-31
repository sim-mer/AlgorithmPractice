import java.util.*; 
class Solution {
    public int[] solution(String[] info, String[] query) {
        ArrayList<Integer>[][][][] applicants = new ArrayList[3][2][2][2];
        int[] answer = new int[query.length];

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 2; j++) {
                for(int k = 0; k < 2; k++) {
                    for(int l = 0; l < 2; l++) {
                        applicants[i][j][k][l] = new ArrayList<>();
                    }
                }
            }
        }

        for(String applicant : info) {
            String[] infoArr = applicant.split(" ");
            int score = Integer.parseInt(infoArr[4]);
            int lang = infoArr[0].equals("cpp") ? 0 : infoArr[0].equals("java") ? 1 : 2;
            int job = infoArr[1].equals("backend") ? 0 : 1;
            int career = infoArr[2].equals("junior") ? 0 : 1;
            int food = infoArr[3].equals("chicken") ? 0 : 1;

            applicants[lang][job][career][food].add(score);
        }

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 2; j++) {
                for(int k = 0; k < 2; k++) {
                    for(int l = 0; l < 2; l++) {
                        applicants[i][j][k][l].sort(null);
                    }
                }
            }
        }

        int idx = 0;
        for(String q : query) {
            String[] split = q.split(" and ");
            String[] last = split[3].split(" ");
            split[3] = last[0];
            int score = Integer.parseInt(last[1]);

            int lang = split[0].equals("cpp") ? 0 : split[0].equals("java") ? 1 : split[0].equals("python") ? 2 : -1;
            int job = split[1].equals("backend") ? 0 : split[1].equals("frontend") ? 1 : -1;
            int career = split[2].equals("junior") ? 0 : split[2].equals("senior") ? 1 : -1;
            int food = split[3].equals("chicken") ? 0 : split[3].equals("pizza") ? 1 : -1;

            int count = 0;
            for(int i = 0; i < 3; i++) {
                if(lang != -1 && lang != i) continue;
                for(int j = 0; j < 2; j++) {
                    if(job != -1 && job != j) continue;
                    for(int k = 0; k < 2; k++) {
                        if(career != -1 && career != k) continue;
                        for(int l = 0; l < 2; l++) {
                            if(food != -1 && food != l) continue;
                            count += applicants[i][j][k][l].size() - binSearch(applicants[i][j][k][l], score);
                        }
                    }
                }
            }
            answer[idx++] = count;
        }

        return answer;
    }

    private int binSearch(List<Integer> list, int score) {
        int first = 0;
        int last = list.size() - 1;
        int mid = 0;

        while(first <= last) {
            mid = (first + last) / 2;
            if(list.get(mid) < score) {
                first = mid + 1;
            } else {
                last = mid - 1;
            }
        }
        return first;
    }
}