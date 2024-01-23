import java.util.*;
class Solution {
    int[] answer = new int[2];
    int[][] users;
    int[] emoticons;
    double[] discount = {0.9, 0.8, 0.7, 0.6};
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;

        permutation(new ArrayList<>(), 0);

        return answer;
    }

    private void permutation(List<Emoticon> list, int idx) {
        if(list.size() == emoticons.length) {
            int subscribe = 0;
            double sum = 0;
            for(int[] user : users) {
                double userDiscount = 1 - (user[0] * 0.01);
                double tempSum = 0;
                for(Emoticon emoticon : list) {
                    if(emoticon.discount > userDiscount) continue;
                    tempSum += emoticon.price;
                }

                if(tempSum >= user[1]) {
                    subscribe++;
                    continue;
                }
                sum += tempSum;
            }

            if(answer[0] < subscribe) {
                answer[0] = subscribe;
                answer[1] = (int) sum;
                return;
            }
            if(answer[0] == subscribe && answer[1] < sum) {
                answer[1] = (int) sum;
            }
            return;
        }

        for(double d : discount) {
            list.add(new Emoticon(emoticons[idx] * d, d));
            permutation(list, idx + 1);
            list.remove(list.size() - 1);
        }
    }

    class Emoticon {
        double price;
        double discount;
        Emoticon(double price, double discount) {
            this.price = price;
            this.discount = discount;
        }
    }
}