import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tuple {
    public static void main(String[] args) {
        String s = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(s)));
    }

    static class Solution {
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

//            Set<String> set = new HashSet<>();
//            String[] arr = s.replaceAll("[{]", " ").replaceAll("[}]", " ").trim().split(" , ");
//            Arrays.sort(arr, (a, b)->{return a.length() - b.length();});
//            int[] answer = new int[arr.length];
//            int idx = 0;
//            for(String s1 : arr) {
//                for(String s2 : s1.split(",")) {
//                    if(set.add(s2)) answer[idx++] = Integer.parseInt(s2);
//                }
//            }
//            return answer;
        }
    }
}
