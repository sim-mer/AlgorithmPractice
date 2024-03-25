import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        String answer = "YES";

        for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            Map<Integer, Map> tree = new HashMap<>();

            for(int j = 0; j < n; j++){
                String currentNum = br.readLine().trim();
                Map currentMap = tree;
                boolean isPresent = false;

                if(!answer.equals("NO")){
                    for(int k = 0; k < currentNum.length(); k++){
                        int temp = currentNum.charAt(k) - '0';

                        if(currentMap.isEmpty() && isPresent) {
                            answer = "NO";
                            break;
                        }

                        if(currentMap.containsKey(temp)) isPresent = true;
                        else isPresent = false;

                        if(isPresent){
                            if(k == currentNum.length() - 1) {
                                answer = "NO";
                                break;
                            }
                            currentMap = (Map) currentMap.get(temp);
                            continue;
                        }

                        Map<Integer, Map> nextMap = new HashMap<>();
                        currentMap.put(temp, nextMap);
                        currentMap = nextMap;
                    }
                }
            }
            System.out.println(answer);
            answer = "YES";
        }
    }
}