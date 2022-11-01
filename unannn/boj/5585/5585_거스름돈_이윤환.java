import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = 1000 - Integer.parseInt(br.readLine());
        int answer = 0;
        int[] arr = {500, 100, 50, 10, 5, 1};
        for (int i = 0; i < 6; i++) {
            while (N >= arr[i]) {
                N -= arr[i];
                answer++;
            }
        }
        System.out.println(answer);
    }
}