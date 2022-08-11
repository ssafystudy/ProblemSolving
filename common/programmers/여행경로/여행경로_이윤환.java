import java.util.*;

class Solution {
    String[][] tickets;
    String[] answer;
    boolean[] isVisited;

    public String[] solution(String[][] tickets) {
        
        this.tickets = tickets;
        answer = new String[tickets.length + 1];
        isVisited = new boolean[answer.length];

        //사전 순 조회를 위한 정렬
        Arrays.sort(tickets, (o1, o2) -> o1[1].compareTo(o2[1]));

        answer[0] = "ICN";

        return dfs(1);
    }

    private String[] dfs(int level) {
        //모든 티켓을 사용하여 최초로 도달했을 경우
        if (answer.length == level) {
            return answer;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (!isVisited[i] && answer[level - 1].equals(tickets[i][0])) {
                isVisited[i] = true;

                answer[level] = tickets[i][1];
                String[] temp = dfs(level + 1);
                if (temp != null) return temp;
                isVisited[i] = false;
            }
        }
        return null;
    }
}
