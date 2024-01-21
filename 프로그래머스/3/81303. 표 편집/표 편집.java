import java.util.*;
class Solution {
    public String solution(int n, int k, String[] cmd) {
        Deque<Integer> deleted = new ArrayDeque<>();
        int size = n;

        for (String c : cmd) {
            String[] arr = c.split(" ");
            String command = arr[0];

            if (command.equals("U")) {
                k -= Integer.parseInt(arr[1]);
            } else if (command.equals("D")) {
                k += Integer.parseInt(arr[1]);
            } else if (command.equals("C")) {
                deleted.add(k);
                size--;
                if (k == size) {
                    k--;
                }
            } else if (command.equals("Z")) {
                if (k >= deleted.pollLast()) {
                    k++;
                }
                size++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append("O");
        }
        while (!deleted.isEmpty()) {
            sb.insert(deleted.pollLast(), "X");
        }
        return sb.toString();
    }
}