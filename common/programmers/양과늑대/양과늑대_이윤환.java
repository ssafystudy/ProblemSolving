import java.util.*;

class Solution {
    private static int max = 0;
    private static int N;
    private static int E;

    public int solution(int[] info, int[][] edges) {

        N = info.length;
        E = edges.length;

        int[] order = new int[N];
        for (int i = 1; i <= N; i++) {
            order[0] = 0; //명시적으로
            perm(info, edges, order, 1, 1, i, 1, 0);
            Arrays.fill(order, 0);
        }

        return max;
    }

    private void perm(int[] info, int[][] edges, int[] order, int visit, int depth, int limit, int sheep, int wolf) {

        if (wolf >= sheep) {
            sheep = 0;
            return;
        }

        if (depth == limit) {
            max = Math.max(sheep, max);
            return;
        }

        for (int i = 0; i < N; i++) {
            if ((visit & (1 << i)) == 0) {
                boolean possible = false;
                for (int j = 0; j < depth; j++) {
                    for (int k = 0; k < E; k++) {
                        if (edges[k][0] == order[j] && edges[k][1] == i) {
                            possible = true;
                            break;
                        }
                    }
                    if (possible) break;
                }

                if (!possible) continue;

                order[depth] = i;
                if (info[i] == 0) {
                    perm(info, edges, order, visit | (1 << i), depth + 1, limit, sheep + 1, wolf);
                } else {
                    perm(info, edges, order, visit | (1 << i), depth + 1, limit, sheep, wolf + 1);
                }
            }
        }
    }
}