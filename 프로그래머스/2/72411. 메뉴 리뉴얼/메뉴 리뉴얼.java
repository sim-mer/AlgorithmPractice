import java.util.*;

class Solution {
    Map<String, Integer> map = new HashMap<>();
    public String[] solution(String[] orders, int[] course) {
        for (String order : orders) {
            for (int k : course) {
                if (order.length() >= k) {
                    mkCombination(k, order, new ArrayList<>(), 0);
                }
            }
        }

        Map<Integer, List<String>> resultMap = new HashMap<>();

        for(int k : course) {
            int max = 0;
            for(String key : map.keySet()) {
                if(key.length() == k && map.get(key) >= 2) {
                    if(!resultMap.containsKey(k)) resultMap.put(k, new ArrayList<>());
                    if(map.get(key) == max) resultMap.get(k).add(key);
                    if(map.get(key) > max) {
                        resultMap.get(k).clear();
                        resultMap.get(k).add(key);
                        max = map.get(key);
                    }
                }
            }
        }

        List<String> resultList = new ArrayList<>();
        for(int k : resultMap.keySet()) {
            resultList.addAll(resultMap.get(k));
        }
        resultList.sort(String::compareTo);


        return resultList.toArray(new String[0]);
    }



    private void mkCombination(int size, String order, List<String> list, int start) {
        if(list.size() == size) {
            List<String> temp = new ArrayList<>(list);
            temp.sort(String::compareTo);
            StringBuilder sb = new StringBuilder();
            for(String s : temp) sb.append(s);
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            return;
        }

        for(int i = start; i < order.length(); i++) {
            list.add(String.valueOf(order.charAt(i)));
            mkCombination(size, order, list, i + 1);
            list.remove(list.size() - 1);
        }
    }
}