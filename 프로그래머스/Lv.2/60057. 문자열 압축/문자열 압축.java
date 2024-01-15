import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = s.length();

        for(int i = 1; i <= s.length() / 2; i++) {
            List<String> sList = split(s, i);

            String current = sList.get(0);
            int cnt = 0;

            StringBuilder sb = new StringBuilder();

            for(String str : sList) {
                if (str.equals(current)) {
                    cnt++;
                    continue;
                }
                if(cnt == 1) {
                    sb.append(current);
                } else {
                    sb.append(cnt).append(current);
                    cnt = 1;
                }
                current = str;
            }
            if(cnt == 1) {
                sb.append(sList.get(sList.size() - 1));
            } else {
                sb.append(cnt).append(current);
            }

            answer = Math.min(answer, sb.toString().length());
        }
        return answer;
    }

    private List<String> split(String s, int n) {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < s.length(); i += n) {
            int end = Math.min(i + n, s.length());
            list.add(s.substring(i, end));
        }
        return list;
    }
}