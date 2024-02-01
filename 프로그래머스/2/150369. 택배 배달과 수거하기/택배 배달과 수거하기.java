import java.util.*;
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int go = cap;
        int back = cap;

        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for(int i = n - 1; i >= 0; i--) {
            if(deliveries[i] == 0 && pickups[i] == 0) continue;
            if(go == cap && deliveries[i] > 0) {
                list.add(i + 1);
            }
            if(back == cap && pickups[i] > 0) {
                list2.add(i + 1);
            }

            int temp;

            while(deliveries[i] > 0) {
                temp = Math.min(go, deliveries[i]);
                deliveries[i] -= temp;
                go -= temp;
                if(go == 0) {
                    go = cap;
                    if(deliveries[i] > 0) {
                        list.add(i + 1);
                    }
                }
            }

            while(pickups[i] > 0) {
                temp = Math.min(back, pickups[i]);
                pickups[i] -= temp;
                back -= temp;
                if(back == 0) {
                    back = cap;
                    if(pickups[i] > 0) {
                        list2.add(i + 1);
                    }
                }
            }

            while(!list.isEmpty() && !list2.isEmpty()) {
                int a = list.remove(0);
                int b = list2.remove(0);
                int max = Math.max(a, b);
                answer += max * 2L;
            }
        }

        while(!list.isEmpty() || !list2.isEmpty()) {
            int a = 0, b = 0;
            if(!list.isEmpty()) a = list.remove(0);
            if(!list2.isEmpty()) b = list2.remove(0);
            int max = Math.max(a, b);
            answer += max * 2L;
        }

        return answer;
    }
}