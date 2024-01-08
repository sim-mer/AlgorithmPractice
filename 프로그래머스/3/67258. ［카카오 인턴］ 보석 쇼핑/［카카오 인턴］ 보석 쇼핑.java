import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        Set<String> gemSet = new HashSet<>(Arrays.asList(gems));

        int start = 0;
        int end = 0;
        Map<String, Integer> gemMap = new HashMap<>();

        while (end < gems.length) {
            gemMap.put(gems[end], gemMap.getOrDefault(gems[end], 0) + 1);
            end++;

            while (gemMap.size() == gemSet.size()) {
                if (answer[0] == 0 && answer[1] == 0) {
                    answer[0] = start + 1;
                    answer[1] = end;
                    continue;
                }

                if (end - (start + 1) < answer[1] - answer[0]) {
                    answer[0] = start + 1;
                    answer[1] = end;
                }

                gemMap.put(gems[start], gemMap.get(gems[start]) - 1);
                if (gemMap.get(gems[start]) == 0) {
                    gemMap.remove(gems[start]);
                }
                start++;
            }
        }

        return answer;
    }
}