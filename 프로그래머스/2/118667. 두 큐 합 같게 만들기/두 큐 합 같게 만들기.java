import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;

        if(queue1.length == 0 && queue2.length == 0) return 0;

        Deque<Integer> deque1 = new ArrayDeque<>();
        Deque<Integer> deque2 = new ArrayDeque<>();

        long sum1 = 0;
        long sum2 = 0;
        int max = 0;

        for(int i : queue1) {
            deque1.add(i);
            sum1 += i;
            max = Math.max(max, i);
        }
        for(int i : queue2) {
            deque2.add(i);
            sum2 += i;
            max = Math.max(max, i);
        }

        long goal = (sum1 + sum2) / 2;
        if(max > goal) return -1;

        while(true) {
            if(answer > queue1.length + queue2.length + 2) return -1;
            if(sum1 == sum2) break;

            if(sum1 > sum2) {
                deque2.add(deque1.pollFirst());
                sum1 -= deque2.peekLast();
                sum2 += deque2.peekLast();
                answer++;
                continue;
            }
            deque1.add(deque2.pollFirst());
            sum1 += deque1.peekLast();
            sum2 -= deque1.peekLast();
            answer++;
        }

        return answer;
    }
}