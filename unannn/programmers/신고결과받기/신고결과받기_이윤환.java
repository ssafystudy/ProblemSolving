import java.util.*;

class Solution {
        private static class User {
        String name;
        Set<String> reportSet;
        int reportCount;

        public User(String name) {
            this.name = name;
            reportSet = new HashSet<>();
            reportCount = 0;
        }
    }

    public int[] solution(String[] id_list, String[] reports, int k) {

        Map<String, Integer> reportMap = new HashMap<>();
        Map<String, User> userMap = new HashMap<>();
        for (String id : id_list) {
            reportMap.put(id, 0);
            userMap.put(id, new User(id));
        }

        for (String reportStr : reports) {
            String[] report = reportStr.split(" ");
            if (!userMap.get(report[0]).reportSet.contains(report[1]))
                reportMap.put(report[1], reportMap.get(report[1]) + 1);
            userMap.get(report[0]).reportSet.add(report[1]);
        }

        Set<Map.Entry<String, Integer>> entries = reportMap.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            if (entry.getValue() >= k) {
                for (String id : id_list) {
                    User user = userMap.get(id);
                    if (user.reportSet.contains(entry.getKey())) {
                        user.reportCount++;
                    }
                }
            }
        }

        int index = 0;
        int[] answer = new int[id_list.length];
        for (String id : id_list) {
            answer[index++] = userMap.get(id).reportCount;
        }

        return answer;
    }
}