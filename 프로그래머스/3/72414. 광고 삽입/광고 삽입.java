class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        if(play_time.equals(adv_time)) return "00:00:00";

        int playTime = strToSec(play_time);
        int advTime = strToSec(adv_time);
        int[] time = new int[playTime + 1];

        for(String log : logs) {
            String[] arr = log.split("-");
            int start = strToSec(arr[0]);
            int end = strToSec(arr[1]);

            time[start]++;
            time[end]--;
        }
        
        for(int i = 0;i < playTime; i++) time[i + 1] += time[i];

        long sum = 0, max = 0;
        for(int i = 0;i < advTime; i++) sum += time[i];
        max = sum;
        int insertTime = 0;

        for(int i = advTime; i < playTime; i++) {
            sum = sum + time[i] - time[i - advTime];
            if(max < sum) {
                max = sum;
                insertTime = i - advTime + 1;
            }
        }

        int hour = insertTime / 3600;
        int min = (insertTime % 3600) / 60;
        int sec = insertTime % 60;

        return String.format("%02d:%02d:%02d", hour, min, sec);
    }

    private int strToSec(String str) {
        String[] time = str.split(":");
        return Integer.parseInt(time[0]) * 3600 + Integer.parseInt(time[1]) * 60 + Integer.parseInt(time[2]);
    }
}