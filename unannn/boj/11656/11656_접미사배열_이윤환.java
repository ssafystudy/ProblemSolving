import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int N = S.length();
        String[] strs = new String[N];
        for (int i = 0; i < N; i++) {
            strs[i] = S.substring(i, N);
        }

        Arrays.sort(strs);

        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str).append('\n');
        }
        System.out.println(sb.toString());
    }
}