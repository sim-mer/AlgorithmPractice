import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        List<LocalTime> busTimeList = new ArrayList<>();
        List<LocalTime> crewTimeList = new ArrayList<>(Arrays.stream(timetable)
                    .map(LocalTime::parse)
                    .sorted()
                    .collect(Collectors.toList()));

        LocalTime strartTime = LocalTime.of(9, 0);
        for(int i = 0; i < n; i++) {
            LocalTime busTime = strartTime.plusMinutes((long) i * t);
            busTimeList.add(busTime);
        }

        for(int i = 0; i < busTimeList.size()-1; i++) {
            LocalTime busTime = busTimeList.get(i);
            int tempM = m;

            while(!crewTimeList.isEmpty() && tempM > 0 && !crewTimeList.get(0).isAfter(busTime)) {
                crewTimeList.remove(0);
                tempM--;
            }
        }

        if(crewTimeList.isEmpty()) return busTimeList.get(busTimeList.size() - 1).toString();

        LocalTime lastBusTime = busTimeList.get(busTimeList.size() - 1);
        crewTimeList.removeIf(time -> time.isAfter(lastBusTime));

        if(crewTimeList.size() < m) return lastBusTime.toString();
        return crewTimeList.get(m - 1).minusMinutes(1).toString();
    }
}