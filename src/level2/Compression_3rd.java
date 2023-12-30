package level2;

import java.util.*;

public class Compression_3rd {
    public static void main(String[] args) {
        String msg = "KAKAO";

        Solution sol = new Solution();

        System.out.println(Arrays.toString(sol.solution(msg)));
    }

    static class Solution {
        public int[] solution(String msg) {
            Map<String, Integer> map = new HashMap<>();
            List<Integer> list = new ArrayList<>();

            for(int i = 0; i < 26; i++) {
                map.put((char)('A' + i) + "", i + 1);
            }

            int idx = 27;

            for(int i = 0; i < msg.length(); i++) {
                String temp = msg.charAt(i) + "";
                while(map.containsKey(temp)) {
                    i++;
                    if (i == msg.length()) break;
                    temp += msg.charAt(i);
                }
                i--;

                if(i == msg.length() - 1) {
                    list.add(map.get(temp));
                    break;
                }

                map.put(temp, idx++);
                list.add(map.get(temp.substring(0, temp.length() - 1)));
            }

            return list.stream().mapToInt(i -> i).toArray();
        }
    }
}
