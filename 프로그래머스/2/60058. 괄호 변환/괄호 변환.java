class Solution {
    public String solution(String p) {
        if(p.isEmpty()) return "";

        int index = 0;
        int left = 0;
        int right = 0;
        boolean isCorrect = true;

        do {
            if(p.charAt(index++) == '(') left++;
            else right++;

            if(right > left) isCorrect = false;
        } while(left != right);

        String u = p.substring(0, index);
        String v = p.substring(index);

        if(isCorrect) return u + solution(v);

        StringBuilder sb = new StringBuilder();
        sb.append("(").append(solution(v)).append(")");

        for(int i = 1; i < u.length() - 1; i++) {
            if(u.charAt(i) == '(') sb.append(")");
            else sb.append("(");
        }
        return sb.toString();
    }
}