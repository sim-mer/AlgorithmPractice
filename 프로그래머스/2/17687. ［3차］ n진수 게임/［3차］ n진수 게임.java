class Solution {
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