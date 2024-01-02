import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public int[] solution(String s) {
            int[] answer = {};

            Pattern pattern = Pattern.compile("\\{([^}|{]+)}");
            Matcher matcher = pattern.matcher(s);

            List<List<Integer>> tupleList = new ArrayList<>();

            while(matcher.find()) {
                String temp = matcher.group();
                temp = temp.replaceAll("[{}]", "");
                String[] tempArr = temp.split(",");

                List<Integer> list = new ArrayList<>();
                for(String str : tempArr) {
                    list.add(Integer.parseInt(str));
                }
                tupleList.add(list);
            }

            tupleList.sort((o1, o2) -> o1.size() - o2.size());

            Set<Integer> set = new LinkedHashSet<>();
            for(List<Integer> list : tupleList) {
                for(int i : list) {
                    set.add(i);
                }
            }

            answer = new int[set.size()];
            int idx = 0;
            for(int i : set) {
                answer[idx++] = i;
            }

            return answer;
        }
}