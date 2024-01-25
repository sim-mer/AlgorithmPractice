import java.util.*;
class Solution {
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        Map<Long, Long> nextRoom = new HashMap<>();
        int idx = 0;

        for(long room : room_number) {
            if(!nextRoom.containsKey(room)) {
                nextRoom.put(room, room + 1);
                answer[idx++] = room;
                continue;
            }

            List<Long> path = new ArrayList<>();

            long next = nextRoom.get(room);
            while(nextRoom.containsKey(next)) {
                path.add(next);
                next = nextRoom.get(next);
            }

            for(long p : path) {
                nextRoom.put(p, next + 1);
            }

            nextRoom.put(next, next + 1);
            nextRoom.put(room, next + 1);

            answer[idx++] = next;
        }

        return answer;
    }
}