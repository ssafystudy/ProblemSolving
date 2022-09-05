import java.util.*;

class Solution {
    
    private static int max;
    private static int[] answer;

    public int[] solution(int n, int[] info) {

        max = 0;
        answer = new int[11];
        comb(info, new int[11], n, 0, 0);
        if (max == 0) {
            answer = new int[1];
            answer[0] = -1;
        }
        return answer;
    }

    private void comb(int[] info, int[] arr, int n, int depth, int start) {
        if (depth == n) {
            int lion = 0;
            int apeach = 0;
            for (int i = 0; i <= 10; i++) {
                if (info[i] < arr[i]) {
                    lion += 10 - i;
                } else if (info[i] > 0) {
                    apeach += 10 - i;
                }
            }
            if (lion > apeach && lion - apeach >= max) {
                if (lion - apeach == max) {
                    for (int i = 10; i >= 0; i--) {
                        if (arr[i] > answer[i]) {
                            max = lion - apeach;
                            answer = Arrays.copyOf(arr, 11);
                            break;
                        } else if (arr[i] < answer[i]) {
                            break;
                        }
                    }
                } else {
                    max = lion - apeach;
                    answer = Arrays.copyOf(arr, 11);
                }

            }
            return;
        }
        for (int i = start; i < arr.length; i++) {
            arr[i]++;
            comb(info, arr, n, depth + 1, i);
            arr[i]--;
        }
    }
}