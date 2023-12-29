package level2;

import java.util.Arrays;

public class PrimeCountInBaseK {
    public static void main(String[] args) {
        int n = 110011;
        int k = 10;

        Solution sol = new Solution();

        System.out.println(sol.solution(n, k));
    }

    static class Solution {
        public int solution(int n, int k) {
            int answer = 0;

            String str = Long.toString(n, k);
            String[] arr = str.split("0");

            for(String s : arr) {
                if(s.isEmpty()) continue;
                if(isPrime(Long.parseLong(s))) answer++;
            }

            return answer;
        }

        private boolean isPrime(long n) {
            if(n == 1) return false;
            for(int i = 2; i <= Math.sqrt(n); i++) {
                if(n % i == 0) return false;
            }
            return true;
        }
    }
}
