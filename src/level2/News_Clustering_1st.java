package level2;

import java.util.ArrayList;
import java.util.List;

public class News_Clustering_1st {
    public static void main(String[] args) {
        String str1 = "asdf";
        String str2 = "asdf";

        Solution sol = new Solution();

        System.out.println(sol.solution(str1, str2));
    }

    static class Solution {
        public int solution(String str1, String str2) {
            str1 = str1.toLowerCase();
            str2 = str2.toLowerCase();

            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            List<String> union = new ArrayList<>();
            List<String> intersection = new ArrayList<>();

            makeList(str1, list1);
            makeList(str2, list2);

            list1.sort(String::compareTo);
            list2.sort(String::compareTo);

            if(list1.isEmpty() && list2.isEmpty()) return 65536;
            if(list1.isEmpty() || list2.isEmpty()) return 0;

            findIntersectionAndUnion(list1, list2, intersection, union);
            return (int)((double)intersection.size() / (double)union.size() * 65536);
        }

        private void findIntersectionAndUnion(List<String> list1, List<String> list2, List<String> intersection, List<String> union) {
            for(String str : list1) {
                if(list2.remove(str)) {
                    intersection.add(str);
                }
                union.add(str);
            }
            union.addAll(list2);
        }

        private void makeList(String str, List<String> list) {
            for(int i = 0; i < str.length() - 1; i++) {
                String temp = str.substring(i, i + 2);
                if(temp.matches("[a-z]{2}")) {
                    list.add(temp);
                }
            }
        }
    }
}
