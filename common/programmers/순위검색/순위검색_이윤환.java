import java.util.*;

class Solution {
    private void addTable(Map<String, List<Integer>> map, String[] condition) {
        String key = condition[0] + condition[1] + condition[2] + condition[3];
        int grade = Integer.parseInt(condition[4]);
        if (map.containsKey(key)) {
            map.get(key).add(grade);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(grade);
            map.put(key, list);
        }
    }

    private void setTable(Map<String, List<Integer>> map, String[] condition, int depth, int limit, int start) {
        if (depth == limit) {
            addTable(map, condition);
            return;
        }

        for (int i = start; i < 4; i++) {
            String temp = condition[i];
            condition[i] = "-";
            setTable(map, condition, depth + 1, limit, i + 1);
            condition[i] = temp;
        }
    }

    public int[] solution(String[] info, String[] query) {
        Map<String, List<Integer>> map = new HashMap<>();

        for (String s : info) {
            String[] condition = s.split(" ");

            for (int i = 0; i <= 4; i++) {
                setTable(map, condition, 0, i, 0);
            }
        }

        Set<String> conditions = map.keySet();
        for (String condition : conditions) {
            Collections.sort(map.get(condition));
        }

        int N = query.length;
        int[] answer = new int[N];

        for (int i = 0; i < N; i++) {
            String[] condition = parse(query[i]);

            String key = condition[0] + condition[1] + condition[2] + condition[3];
            int grade = Integer.parseInt(condition[4]);

            if (map.containsKey(key)) {
                List<Integer> list = map.get(key);
                answer[i] = list.size() - binarySearch(list, grade);
            }
        }
        return answer;
    }

    int binarySearch(List<Integer> grades, int target){
        int start = 0;
        int end = grades.size() - 1;
        int mid;

        while(start <= end){
            mid = (start + end) / 2;
            if(grades.get(mid) < target){
                start = mid + 1;
            } else{
                end = mid - 1;
            }
        }
        return start;
    }

    private String[] parse(String query) {
        return Arrays.stream(query.split(" "))
                .filter(o -> !o.equals("and"))
                .toArray(String[]::new);
    }
}