package level2;

public class NBaseGame {
    public static void main(String[] args) {
        int n = 16;
        int t = 16;
        int m = 2;
        int p = 1;

        Solution sol = new Solution();

        System.out.println(sol.solution(n, t, m, p));

    }

    static class Solution {
        public String solution(int n, int t, int m, int p) {
            String answer = "";
            String number = "";

            int num = (t - 1) * m + p;
            int i = 0;

            while(number.length() <= num) {
                String str = Integer.toString(i++, n);
                number += str;
                while(number.length() >= p) {
                    if(answer.length() == t) return answer.toUpperCase();
                    answer += number.charAt(p - 1);
                    p = p + m;
                }
            }

            return answer.toUpperCase();
        }
    }

}
