import java.util.*;
import java.util.stream.*;
class Solution {
    private char[][] orderArr;
    private List<Character> singleList;
    private int N;
    private Map<char[], Integer> tempMap;

    public String[] solution(String[] orders, int[] course) {
        orderArr = new char[orders.length][];
        singleList = new ArrayList<>();
        tempMap = new HashMap<>();

        Set<Character> singleSet = new HashSet<>();
        for (int i = 0, size = orders.length; i < size; i++) {
            orderArr[i] = orders[i].toCharArray();
            Arrays.sort(orderArr[i]);
            for (char c : orderArr[i]) {
                singleSet.add(c);
            }
        }

        singleList = singleSet.stream()
                .sorted()
                .collect(Collectors.toList());

        N = singleList.size();

        Map<char[], Integer> answerMap = new HashMap<>();
        for (int i = course.length - 1; i >= 0; i--) {
            char[] arr = new char[course[i]];
            comb(arr, 0, 0, course[i]);
            answerMap.putAll(tempMap);
            tempMap.clear();
        }

        String[] answer = new String[answerMap.size()];
        int i = 0;
        for (char[] c : answerMap.keySet()) {
            answer[i++] = String.copyValueOf(c);
        }
        Arrays.sort(answer);
        return answer;
    }

    private void comb(char[] course, int start, int depth, int limit) {
        if (depth == limit) {
            int count = 0;

            char[] temp = Arrays.copyOf(course, depth);
            for (char[] order : orderArr) {
                if (contain(order, temp)) count++;
            }

            if (count > 1) {
                Integer first = tempMap.values().stream().findFirst().orElse(null);
                if (first == null) {       
                    tempMap.put(temp, count);
                } else if (first < count) {
                    tempMap.clear();
                    tempMap.put(temp, count);
                } else if (first == count) {
                    tempMap.put(temp, count);
                }
            }
            return;
        }

        for (int i = start; i < N; i++) {
            course[depth] = singleList.get(i);
            comb(course, i + 1, depth + 1, limit);
        }
    }

    private boolean contain(char[] A, char[] b) {
        int index = 0;
        for (char a : A) {
            if (b[index] == a) index++;
            if (index == b.length) return true;
        }
        return false;
    }
}