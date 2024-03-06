import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int first = 301;
        int last = 1130;

        int answer = 0;

        int n = Integer.parseInt(br.readLine());
        Flower[] flowerList = new Flower[n];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startMonth = Integer.parseInt(st.nextToken());
            int startDay = Integer.parseInt(st.nextToken());
            int endMonth = Integer.parseInt(st.nextToken());
            int endDay = Integer.parseInt(st.nextToken());
            int start = startMonth * 100 + startDay;
            int end = endMonth * 100 + endDay;
            flowerList[i] = new Flower(start, end);
        }

        Arrays.sort(flowerList);

        int idx = 0;
        Flower temp;
        int max = first;
        while(true) {
            boolean flag = false;
            for(int i = idx; i < n; i++) {
                temp = flowerList[i];

                if(temp.start > first) {
                    idx = i;
                    break;
                }

                if(temp.end > max) {
                    max = temp.end;
                    flag = true;
                }
            }

            if(flag) {
                answer++;
                first = max;
            }

            if(!flag || first > last) break;
        }

        if(first > last) System.out.println(answer);
        else System.out.println(0);

        br.close();
    }

    static class Flower implements Comparable<Flower>{
        int start;
        int end;

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower o) {
            if(start == o.start) {
                return end - o.end;
            }
            return start - o.start;
        }
    }
}