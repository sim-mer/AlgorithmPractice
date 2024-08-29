import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        boolean[] visited = new boolean[words.length];
        List<String> list = new ArrayList<>();
        List<String> newList = new ArrayList<>();
        list.add(begin);
        
        while(true) {
            answer++;
            for(int i = 0; i < words.length; i++) {
                if(visited[i]) continue;
                for(String s : list) {
                    if(oneDiff(s, words[i])) {
                        if(words[i].equals(target)) return answer;
                        visited[i] = true;
                        newList.add(words[i]);
                        break;
                    }
                }
            }
            if(newList.isEmpty()) return 0;
            list.clear();
            list.addAll(newList);
            newList.clear();
        }
    }
    
    private boolean oneDiff(String a, String b) {
        boolean flag = false;
        
        for(int i = 0; i < a.length(); i++) {
            if(a.charAt(i) != b.charAt(i)) {
                if(flag) return false;
                flag = true;
            }
        }
        
        if(flag) return true;
        return false;
    }
}