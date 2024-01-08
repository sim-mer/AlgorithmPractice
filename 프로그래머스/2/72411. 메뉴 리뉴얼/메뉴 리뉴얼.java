import java.util.*;

class Solution {
    Map<String, Integer> map = new HashMap<>();
    int max = 0;
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();

        for (int k : course) {
            for (String order : orders) {
                if (order.length() >= k) {
                    mkCombination(k, order, new ArrayList<>(), 0);
                }
            }

            for (String key : map.keySet()) {
                if (map.get(key) == max && max > 1) {
                    answer.add(key);
                }
            }
            map.clear();
            max = 0;
        }

        answer.sort(String::compareTo);
        return answer.toArray(new String[0]);
    }



    private void mkCombination(int size, String order, List<String> list, int start) {
        if(list.size() == size) {
            List<String> temp = new ArrayList<>(list);
            temp.sort(String::compareTo);
            StringBuilder sb = new StringBuilder();
            for(String s : temp) sb.append(s);
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            max = Math.max(max, map.get(sb.toString()));
            return;
        }

        for(int i = start; i < order.length(); i++) {
            list.add(String.valueOf(order.charAt(i)));
            mkCombination(size, order, list, i + 1);
            list.remove(list.size() - 1);
        }
    }
}