import java.util.*;

class Solution {

        public int solution(String begin, String target, String[] words) {

        Set<String> visited = new HashSet<>();  //방문한 문자열 저장
        Queue<Node> queue = new ArrayDeque<>(); //bfs 돌릴 큐
        queue.add(new Node(begin, 0));  //시작 노드 추가

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            visited.add(current.str);    //방문 처리

            if (current.str.equals(target)) {   //현재 문자열이 target 과 일치하면 높이 반환
                return current.height;
            }

            if (current.height == words.length - 1) {  // 모든 문자열을 방문할떄까지 taget과 일치하지 않았으므로 continue;
                continue;
            }

            for (int i = 0; i < words.length; i++) {
                //문자열을 방문하지 않음 && 문자열이 한 문자 뺴고 같다면
                if (!visited.contains(words[i]) && isPossibleNext(current.str, words[i])) {
                    queue.add(new Node(words[i], current.height + 1));
                }
            }
        }
        return 0;
    }

    static class Node {
        String str;
        int height;

        Node(String str, int height) {
            this.str = str;
            this.height = height;
        }
    }

    private boolean isPossibleNext(String str, String target) {
        int length = str.length();
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += str.charAt(i) == target.charAt(i) ? 1 : 0;
        }
        return sum == length - 1;
    }
}