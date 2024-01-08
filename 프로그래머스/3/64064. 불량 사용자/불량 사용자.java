import java.util.*;

class Solution {
    boolean[] visited;
    Set<Set<String>> resultSet = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {

        visited = new boolean[user_id.length];

        List<String> bannedList = new ArrayList<>();
        for(String banned : banned_id) {
            bannedList.add(banned.replaceAll("\\*", "."));
        }

        search(0, user_id, bannedList, new HashSet<>());

        return resultSet.size();
    }

    private void search(int depth, String[] user_id, List<String> bannedList, Set<String> caseSet){
        if(depth == bannedList.size()) {
            resultSet.add(new HashSet<>(caseSet));
            return;
        }

        for(int i = 0; i < user_id.length; i++) {
            if(!visited[i] && user_id[i].matches(bannedList.get(depth))) {
                visited[i] = true;
                caseSet.add(user_id[i]);
                search(depth + 1, user_id, bannedList, caseSet);
                caseSet.remove(user_id[i]);
                visited[i] = false;
            }
        }
    }
}