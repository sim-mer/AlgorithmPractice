import java.util.*;
class Solution {
    public int solution(int[] food_times, long k) {
        Map<Integer, Integer> foodMap = new HashMap<>();

        for(int food : food_times) {
            foodMap.put(food, foodMap.getOrDefault(food, 0) + 1);
        }

        int foodCnt = food_times.length;
        int cnt = 0;

        while(k >= foodCnt) {
            if(foodCnt == 0) return -1;
            k -= foodCnt;
            cnt++;
            if(foodMap.containsKey(cnt)) {
                foodCnt -= foodMap.get(cnt);
            }
        }

        for(int i = 0; i < food_times.length; i++) {
            if(food_times[i] > cnt) {
                if(k == 0) return i + 1;
                k--;
            }
        }

        return -1;
    }
}