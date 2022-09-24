import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Deque<Character> deque = new ArrayDeque<>();

        int answer = 0;
        for (int i = 0; i < N; i++) {
            char[] word = br.readLine().toCharArray();
            for (char c : word) {
                if (deque.isEmpty()) deque.push(c);
                else {
                    if (deque.peek() == c) {
                        deque.pop();
                    } else {
                        deque.push(c);
                    }
                }
            }
            if (deque.isEmpty()) answer++;
            else deque.clear();

        }
        System.out.println(answer);
    }
}