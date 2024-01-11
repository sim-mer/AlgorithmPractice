class Solution {
    public int solution(int[] stones, int k) {
        int first = 1;
        int last = 200000000;
        int answer = 0;

        while(first <= last) {
            int mid = (first + last) / 2;

            if(isPossible(stones, k, mid)) {
                first = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                last = mid -1;
            }
        }
        return answer + 1;
    }

    private boolean isPossible(int[] stones, int k, int person){
        int count = 0;
        boolean ing = false;

        for(int i = 0;i< stones.length; i++) {
            if(stones[i] - person <= 0) {
                if(ing) count++;
                else {
                    count = 1;
                    ing = true;
                }
            }
            else {
                if(ing) {
                    ing = false;
                    count = 0;
                }
            }
            if(count >= k) return false;
        }
        return true;
    }
}