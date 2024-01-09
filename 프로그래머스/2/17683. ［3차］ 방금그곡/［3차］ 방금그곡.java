import java.util.*;
import java.time.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";

        List<Music> musicList = new ArrayList<>();

        for(String musicinfo : musicinfos) {
            String[] temp = musicinfo.split(",");
            LocalTime startTime = LocalTime.parse(temp[0]);
            LocalTime endTime = LocalTime.parse(temp[1]);
            String title = temp[2];
            String melody = temp[3];
            int melodyLength = melody.replaceAll("#", "").length();

            Duration duration = Duration.between(startTime, endTime);
            int playTime = (int) duration.toMinutes();

            int count = playTime / melodyLength;
            int rest = playTime % melodyLength;

            StringBuilder sb = new StringBuilder();
            sb.append(melody.repeat(count));

            for(int i = 0; i < melody.length() && rest > 0; i++) {
                sb.append(melody.charAt(i));
                if(melody.charAt(i + 1) == '#') {
                    sb.append('#');
                    i++;
                }
                rest--;
            }

            musicList.add(new Music(title, sb.toString(), playTime));
        }


        List<Music> answerList = new ArrayList<>();

        for(Music music : musicList) {
            while(music.melody.contains(m)) {
                    int index = music.melody.indexOf(m);
                    if(index + m.length() < music.melody.length() && music.melody.charAt(index + m.length()) == '#') {
                        music.melody = music.melody.substring(index + m.length() + 1);
                        continue;
                    }
                    answerList.add(music);
                    break;
            }
        }

        if(answerList.isEmpty()) return answer;

        answerList.sort(Music::compareTo);

        return answerList.get(0).title;
    }

    class Music {
        String title;
        String melody;
        int playTime;

        public Music(String title, String melody, int playTime) {
            this.title = title;
            this.melody = melody;
            this.playTime = playTime;
        }

        public int compareTo(Music o) {
            return o.playTime - this.playTime;
        }
    }
    //edit idea
}