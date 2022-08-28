package com.unannn._0828;

import java.io.*;
import java.util.*;

public class 낚시터_자리잡기 {

    static int N;
    static boolean[] visited;
    static int[] order;
    static int[][] gates;

    static boolean check;

    static final int[][] priorities = {
            {-1, -1, -1}, {1, -1, -1}, {-1, 1, -1}, {-1, -1, 1},
            {1, 1, -1}, {1, -1, 1}, {-1, 1, 1}, {1, 1, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st = null;
        visited = new boolean[N];
        StringBuilder sb = new StringBuilder();
        for (int testCase = 1; testCase <= T; testCase++) {

            N = Integer.parseInt(br.readLine());
            visited = new boolean[N];


            gates = new int[3][2];
            for(int i = 0; i < 3; i++){
                st = new StringTokenizer(br.readLine());
                gates[i][0] = Integer.parseInt(st.nextToken()) - 1;
                gates[i][1] = Integer.parseInt(st.nextToken());
            }

            int answer = Integer.MAX_VALUE;
            order = new int[3];
            for(int i = 0; i < 3;i++){
                order[0] = i;
                for (int j = 0; j < 3; j++) {
                    if(j == i) continue;
                    order[1] = j;
                    for (int k = 0; k < 3; k++) {
                        if(k == i || k == j) continue;
                        order[2] = k;
                        int sum = getMinSum();
                        answer = Integer.min(answer, sum);
                    }
                }
            }


            sb.append('#').append(testCase).append(' ')
                    .append(answer).append('\n');
        }
        System.out.println(sb);
    }


    static int getMinSum(){
        int[] priority = new int[3];
        int min = Integer.MAX_VALUE;
        check = false;
        for (int i = 0; i < 8; i++) {
            int sum = 0;
            Arrays.fill(visited,false);
            for (int gate : order) {
                sum += Math.min(min, bfs(gate, priorities[i]));
            }
            min = Math.min(min, sum);
            if(!check) break;
        }

        return min;
    }

    static int bfs(int gateOrder, int[] priority){
        int waiting = gates[gateOrder][1];
        int sum = 0;
        int gateIndex = gates[gateOrder][0];
        int minDistance = 0;
        while(waiting > 0){
            if(waiting != 1){
                if(isValid(gateIndex - minDistance) && !visited[gateIndex - minDistance]){
                    visited[gateIndex - minDistance] = true;
                    sum+= minDistance + 1;
                    waiting--;
                } else if(isValid(gateIndex + minDistance) && !visited[gateIndex + minDistance]){
                    visited[gateIndex + minDistance] = true;
                    sum+= minDistance + 1;
                    waiting--;
                } else{
                    minDistance++;
                }
            } else{
                if(isValid(gateIndex - minDistance) && !visited[gateIndex - minDistance]
                        && isValid(gateIndex + minDistance) && !visited[gateIndex + minDistance]){
                    check = true;
                    if(priority[gateOrder] < 0){
                        visited[gateIndex - minDistance] = true;
                    }else{
                        visited[gateIndex + minDistance] = true;
                    }
                    sum += minDistance + 1;
                    waiting--;
                }else if(isValid(gateIndex - minDistance) && !visited[gateIndex - minDistance]){
                    visited[gateIndex - minDistance] = true;
                    sum += minDistance + 1;
                    waiting--;
                }else if(isValid(gateIndex + minDistance) && !visited[gateIndex + minDistance]){
                    visited[gateIndex + minDistance] = true;
                    sum += minDistance + 1;
                    waiting--;
                } else{
                    minDistance++;
                }
            }
        }
        return sum;
    }

    private static boolean isValid(int l){
        return l >= 0 && l < N;
    }
}
