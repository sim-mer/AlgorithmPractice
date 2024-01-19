import java.util.*;

class Solution {
    int tupleCount;
    String[][] relation;
    List<List<Integer>> candidateKeyList = new ArrayList<>();
    public int solution(String[][] relation) {
        tupleCount = relation.length;
        this.relation = relation;

        for(int i = 1; i <= relation[0].length; i++) {
            combination(0, 0, i, new ArrayList<>());
        }

        return candidateKeyList.size();
    }

    private void combination(int start, int depth, int r, List<Integer> columnList) {
        if(depth == r) {
            if(check(columnList) && isCandidateKey(columnList)) {
                candidateKeyList.add(new ArrayList<>(columnList));
            }
            return;
        }

        for(int i = start; i < relation[0].length; i++) {
            columnList.add(i);
            combination(i + 1, depth + 1, r, columnList);
            columnList.remove(columnList.size() - 1);
        }
    }

    private boolean check(List<Integer> columnList) {
        if(columnList.isEmpty()) return false;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < tupleCount; i++) {
            StringBuilder sb = new StringBuilder();
            for (int column : columnList) {
                sb.append(relation[i][column]);
            }
            set.add(sb.toString());
        }
        return set.size() == tupleCount;
    }

    private boolean isCandidateKey(List<Integer> columnList) {
        for(List<Integer> key : candidateKeyList) {
            if(columnList.containsAll(key)) return false;
        }
        return true;
    }
}