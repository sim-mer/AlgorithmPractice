import java.util.*;

class Solution {
        public int solution(int m, int n, String[] board) {
            int answer = 0;

            List<List<String>> boardList = new ArrayList<>();
            //이 리스트에서는 역순서의 열을 행으로 바꾼 형태로 저장한다.

            for(int i = 0; i < n; i++) {
                List<String> reverseColumnList = new ArrayList<>();
                for(int j = m - 1; j >= 0; j--) {
                    reverseColumnList.add(board[j].charAt(i) + "");
                }
                boardList.add(reverseColumnList);
            }

            while(true) {
                Set<Pair> removePairSet = new HashSet<>();

                for(int i = 0; i < boardList.size() - 1; i++) {
                    for(int j = 0; j < boardList.get(i).size() - 1; j++) {
                        if(isRemovable(boardList, j, i)) {
                            removePairSet.addAll(List.of(new Pair(j, i), new Pair(j + 1, i), new Pair(j, i + 1), new Pair(j + 1, i + 1)));
                        }
                    }
                }

                if(removePairSet.isEmpty()) break;

                answer += removePairSet.size();

                for(Pair pair : removePairSet) {
                    boardList.get(pair.y).set(pair.x, "");
                }

                for(List<String> list : boardList) {
                    list.removeIf(String::isEmpty);
                }
            }

            return answer;
        }

        private class Pair {
            int x;
            int y;

            public Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object obj) {
                if(obj instanceof Pair) {
                    Pair pair = (Pair)obj;
                    return this.x == pair.x && this.y == pair.y;
                }
                return false;
            }

            @Override
            public int hashCode() {
                return this.x + this.y;
            }
        }

        private boolean isRemovable(List<List<String>> boardList, int x, int y) {
            List<String> list = boardList.get(y);
            String str = list.get(x);
            if(boardList.get(y + 1).size() <= x + 1) return false;
            return str.equals(list.get(x + 1)) && str.equals(boardList.get(y + 1).get(x)) && str.equals(boardList.get(y + 1).get(x + 1));
        }

}
