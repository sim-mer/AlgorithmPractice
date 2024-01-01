package level2;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class ParkingFeeCalculator {
    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN",
                            "06:00 0000 IN",
                            "06:34 0000 OUT",
                            "07:59 5961 OUT",
                            "07:59 0148 IN",
                            "18:59 0000 IN",
                            "19:09 0148 OUT",
                            "22:59 5961 IN",
                            "23:00 5961 OUT"};

        Solution sol = new Solution();

        System.out.println(Arrays.toString(sol.solution(fees, records)));
    }

    static class Solution {
        public int[] solution(int[] fees, String[] records) {
            Map<String, LocalTime> carMap = new HashMap<>();
            Map<String, Integer> timeMap = new HashMap<>();

            for(String record : records) {
                String[] split = record.split(" ");
                String time = split[0];
                String carNum = split[1];
                String inOut = split[2];

                if(inOut.equals("IN")) {
                    carMap.put(carNum, LocalTime.parse(time));
                    continue;
                }
                calcTime(carMap, timeMap, carNum, time);
                carMap.remove(carNum);
            }

            for(String carNum : carMap.keySet()) {
                calcTime(carMap, timeMap, carNum, "23:59");
            }

            List<String> sortedKeys = new ArrayList<>(timeMap.keySet());
            sortedKeys.sort(String::compareTo);

            int[] answer = new int[sortedKeys.size()];

            for(int i = 0; i < sortedKeys.size(); i++) {
                int time = timeMap.get(sortedKeys.get(i));
                if(time <= fees[0]) {
                    answer[i] = fees[1];
                    continue;
                }
                answer[i] = fees[1] + (int)Math.ceil((double)(time - fees[0]) / (double)fees[2]) * fees[3];
            }

            return answer;
        }

        private void calcTime(Map<String, LocalTime> carMap, Map<String, Integer> timeMap, String carNum, String time) {
            LocalTime inTime = carMap.get(carNum);
            Duration duration = Duration.between(inTime, LocalTime.parse(time));
            int diff = (int)duration.toMinutes();
            timeMap.put(carNum, timeMap.getOrDefault(carNum, 0) + diff);
        }
    }
}
