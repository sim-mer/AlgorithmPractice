import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public String[] solution(String[] files) {
        List<File> fileList = new ArrayList<>();

        for(String file : files) {
                String tail = "";
                String[] fileParts = file.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)", 3);
                if(fileParts.length == 3) tail = fileParts[2];
                fileList.add(new File(fileParts[0], fileParts[1], tail));
            }

        fileList.sort(File::compareTo);

        return fileList.stream()
                .map(file -> file.head + file.number + file.tail)
                .toArray(String[]::new);

    }

    private class File implements Comparable<File> {
        String head;
        String number;
        String tail;

        public File(String head, String number, String tail) {
            this.head = head;
            this.number = number;
            this.tail = tail;
        }

        @Override
        public int compareTo(File o) {
            int headCompare = this.head.compareToIgnoreCase(o.head);
            if(headCompare != 0) return headCompare;
            return Integer.parseInt(this.number) - Integer.parseInt(o.number);
        }
    }
}