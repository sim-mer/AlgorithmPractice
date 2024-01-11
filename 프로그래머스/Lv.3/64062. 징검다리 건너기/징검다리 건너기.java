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

//    class Solution {
//        public int solution(int[] stones, int k) {
//            int left = 1, right = 1 << 30, max = 0;
//            while(left <= right){
//                int mid = (left / 2) + (right / 2), cnt = 0;
//                for(int i : stones) {
//                    cnt = (i - mid) < 0 ? cnt + 1 : 0;
//                    if(cnt == k) break;
//                }
//                if(cnt < k) {
//                    left = mid + 1;
//                    max = Math.max(max, mid);
//                }
//                else right = mid - 1;
//            }
//            return max;
//        }
//    }