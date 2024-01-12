import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public long solution(String expression) {
        String[] numbers = expression.split("\\D");
        String[] operators = expression.split("\\d+");

        String[][] operatorPriority = {
                {"+", "-", "*"},
                {"+", "*", "-"},
                {"-", "+", "*"},
                {"-", "*", "+"},
                {"*", "+", "-"},
                {"*", "-", "+"}
        };

        List<Long> answerCandidate = new ArrayList<>();
        List<Long> numberList = Arrays.stream(numbers)
                .map(Long::parseLong)
                .collect(Collectors.toList());

        for(String[] priority : operatorPriority) {
            List<Long> tempNumberList = new ArrayList<>(numberList);
            List<String> tempOperatorList = new ArrayList<>(Arrays.asList(operators));

            for(String operator : priority) {
                for(int i = 1; i < tempOperatorList.size(); i++) {
                    if(tempOperatorList.get(i).equals(operator)) {
                        long result = calculate(tempNumberList.get(i - 1), tempNumberList.get(i), operator);
                        tempNumberList.remove(i - 1);
                        tempNumberList.remove(i - 1);
                        tempNumberList.add(i - 1, result);
                        tempOperatorList.remove(i);
                        i--;
                    }
                }
            }
            answerCandidate.add(Math.abs(tempNumberList.get(0)));
        }

        return answerCandidate.stream()
                .max(Long::compareTo)
                .get();
    }

    private long calculate(long num1, long num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            default:
                return num1 * num2;
        }
    }
}