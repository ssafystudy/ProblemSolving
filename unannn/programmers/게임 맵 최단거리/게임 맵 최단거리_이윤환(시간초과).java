import java.util.*;
class Solution {
    public int solution(int[][] maps) {

        int[][] visited = new int[maps.length][ maps[0].length];
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
        int[] goal = {maps.length, maps[0].length};

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 0}); // 시작 좌표 추가 { x 좌표, y 좌표, 이동 거리}

        //최단거리
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int distance = current[2];
            visited[x][y] = distance;
            maps[x][y] = 1;
            
            if(equals(current, goal)){
                if(visited[x][y] == 0 || visited[x][y] > distance){
                    visited[x][y] = distance;
                    return distance; //이미 목표지점에 도착했기 때문에 또 돌필요가 없음
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                if(isValid(maps, nx, ny) && maps[nx][ny] == 1 && visited[nx][ny] > distance + 1){
                    queue.add(new int[]{nx,ny, distance + 1});
                }
            }

        }
        int min = visited[maps.length - 1][maps[0].length - 1]; 
        return min == Integer.MAX_VALUE ? -1 : min + 1; //거리가 아니라 블록의 개수이므로 + 1
    }

    private boolean equals(int[] a, int[] b){
        return a[0] == b[0] && a[1] == b[1];
    }

    private boolean isValid(int[][] maps, int x, int y){
        return x >= 0 && x < maps.length && y >= 0 && y < maps[0].length;
    }
}