import java.util.*;

class Solution {
    boolean[] visited;
    Set<List<String>> resultSet = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {

        visited = new boolean[user_id.length];

        List<String> bannedList = new ArrayList<>();
        for(String banned : banned_id) {
            bannedList.add(banned.replaceAll("\\*", "."));
        }

        search(0, user_id, bannedList, new ArrayList<>());

        return resultSet.size();
    }

    private void search(int depth, String[] user_id, List<String> bannedList, List<String> caseList){
        if(depth == bannedList.size()) {
            caseList.sort(String::compareTo);
            resultSet.add(caseList);
            return;
        }

        for(int i = 0; i < user_id.length; i++) {
            if(!visited[i] && user_id[i].matches(bannedList.get(depth))) {
                visited[i] = true;
                caseList.add(user_id[i]);
                search(depth + 1, user_id, bannedList, caseList);
                caseList.remove(user_id[i]);
                visited[i] = false;
            }
        }
    }
}