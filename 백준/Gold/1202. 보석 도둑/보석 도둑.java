import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);

        List<Jewel> jewelList = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewelList.add(new Jewel(m, v));
        }

        Collections.sort(jewelList);

        PriorityQueue<Integer> bagPq = new PriorityQueue<>();

        for(int i = 0; i < k; i++) {
            bagPq.add(Integer.valueOf(br.readLine()));
        }

        long answer = 0;
        int idx = 0;
        PriorityQueue<Jewel> jewelPq = new PriorityQueue<>((o1, o2) -> o2.V - o1.V);

        while(!bagPq.isEmpty()) {
            Integer tempBag = bagPq.poll();
            for(int i = idx; i < n; i++) {
                Jewel j = jewelList.get(i);
                if(j.M > tempBag) {
                    idx = i;
                    break;
                }
                jewelPq.add(j);
                if(i == n - 1) idx = n;
            }
            if(!jewelPq.isEmpty()) answer += jewelPq.poll().V;
        }

        System.out.println(answer);
        br.close();
    }
    static class Jewel implements Comparable<Jewel>{
        int M;
        int V;
        protected Jewel (int M, int V) {
            this.M = M;
            this.V = V;
        }

        @Override
        public int compareTo(Jewel o){
            return M - o.M;
        }
    }
}
