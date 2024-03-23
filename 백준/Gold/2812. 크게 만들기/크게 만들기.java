import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);

        String num = br.readLine().trim();
        boolean[] pass = new boolean[n];

        PriorityQueue<One> pq = new PriorityQueue<>();
        pq.add(new One(0, num.charAt(0)));

        for(int i = 1; i < n; i++){
            One min = pq.peek();
            char current = num.charAt(i);
            while(!pq.isEmpty() && k > 0 && min.value < current) {
                k--;
                pass[pq.poll().idx] = true;

                if(pq.isEmpty()) break;
                min = pq.peek();
            }
            if(k == 0) break;
            pq.add(new One(i, num.charAt(i)));
        }
        
        while(k > 0) {
            pass[pq.poll().idx] = true;
            k--;
        }

        StringBuilder sb = new StringBuilder();
        for(int j = 0; j < num.length(); j++) {
            if(!pass[j]) sb.append(num.charAt(j));
        }
        System.out.println(sb.toString());

        br.close();
    }
}

class One implements Comparable<One>{
    int idx;
    int value;
    public One(int idx, int value) {
        this.idx = idx;
        this.value = value;
    }
    @Override
    public int compareTo(One o){
        return value - o.value;
    }
}