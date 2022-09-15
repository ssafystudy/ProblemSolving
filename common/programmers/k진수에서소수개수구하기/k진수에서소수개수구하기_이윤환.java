import java.util.*;

class Solution {
    public int solution(int n, int k) {
        return (int) Arrays.stream(Integer.toString(n, k).split("0"))
                .filter(o -> o.length() > 0)
                .filter(this::isPrime)
                .count();
    }

    private boolean isPrime(String str) {
        long temp = Long.parseLong(str);
        if (temp <= 1l) return false;

        for (long i = 2; i <= Math.sqrt(temp); i++) {
            if (temp % i == 0) return false;
        }
        return true;
    }
}