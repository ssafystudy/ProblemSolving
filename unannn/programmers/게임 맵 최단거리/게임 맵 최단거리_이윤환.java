import java.util.*;

class Solution {
    public int solution(int[][] maps) {

        //도착 좌표
        int[] goal = {maps.length - 1, maps[0].length - 1};

        //bfs큐 객체 생성
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 1}); // 시작 좌표 추가 { x 좌표, y 좌표, 거쳐간 칸 수}
        maps[0][0] = 0;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int distance = current[2];

            //도착하면 지나온 칸 개수 반환
            if(equals(current, goal)){
                return distance;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(isValid(maps, nx, ny) && maps[nx][ny] == 1){
                    queue.add(new int[]{nx,ny, distance + 1});
                    maps[nx][ny] = 0;
                }
            }

        }
        return -1; //거리가 아니라 블록의 개수이므로 + 1
    }


    private boolean equals(int[] a, int[] b){
        return a[0] == b[0] && a[1] == b[1];
    }

    private boolean isValid(int[][] maps, int x, int y){
        return x >= 0 && x < maps.length && y >= 0 && y < maps[0].length;
    }
}