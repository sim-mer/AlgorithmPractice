import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> userMap = new HashMap<>();
        List<UserAction> userActionList = new ArrayList<>();

        for(String str : record) {
            String[] temp = str.split(" ");
            String command = temp[0];
            String userId = temp[1];
            String nickname = "";
            if(temp.length == 3) nickname = temp[2];

            if(!command.equals("Leave")) {
                userMap.put(userId, nickname);
            }
            if(command.equals("Change")) continue;

            userActionList.add(new UserAction(Command.valueOf(command), userId));
        }

        return userActionList.stream()
                .map(userAction -> {
                    String nickname = userMap.get(userAction.userId);
                    String actionMessage = (userAction.command == Command.Enter) ? "들어왔습니다." : "나갔습니다.";
                    return String.format("%s님이 %s", nickname, actionMessage);
                })
                .toArray(String[]::new);
    }

    private enum Command {
        Enter, Leave
    }

    private class UserAction {
        Command command;
        String userId;

        public UserAction(Command command, String userId) {
            this.command = command;
            this.userId = userId;
        }
    }
}