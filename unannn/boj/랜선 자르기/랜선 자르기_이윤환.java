import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        long[] lanLines = new long[K];
        for(int i = 0; i < K;i++){
            lanLines[i] = Long.parseLong(br.readLine());
        }

        long start = 1;
        long end = 0;
        for(int i = 0; i < K; i++){
            end = Math.max(end, lanLines[i]);
        }
        while(start <= end){
            int num = 0;
            long L = (start + end) / 2;

            for(int i = 0; i < K; i++){
                num += lanLines[i] / L;
            }
            if(num >= N){
                start = L + 1;
            }else{
                end = L - 1;
            }
        }
        System.out.println(end);
    }
}