# https://www.acmicpc.net/problem/20057
import sys

input = sys.stdin.readline


# 1.토네이도 이동 구현
# 2. 토네이도 이동에 따른 모래 이동

def solution(sr, sc, direction):
    global ans
    if sc < 0:
        return
    rest = 0  # 알파 구할라고
    for dr, dc, p in direction:
        nr = sr + dr
        nc = sc + dc
        if p == 0:  # a(나머지)
            new_sand = map[sr][sc] - rest
        else:
            new_sand = map[sr][sc] * p // 100
            rest += new_sand

        if 0 <= nr < N and 0 <= nc < N:  # 인덱스 범위
            map[nr][nc] += new_sand
        else:  # 범위밖일때 모래
            ans += new_sand


N = int(input())
map = [list(map(int, input().split())) for _ in range(N)]

left = [(-1, 1, 1), (1, 1, 1), (-2, 0, 2), (2, 0, 2), (-1, 0, 5),
        (-1, 0, 7), (1, 0, 7), (-1, -1, 10), (1, -1, 10), (0, -1, 0)]
right = [(r, -c, p) for r, c, p in left]
down = [(-c, r, p) for r, c, p in left]
up = [(c, r, p) for r, c, p in left]

sr, sc = N // 2, N // 2  # 토네이도 시작위치
ans = 0
# 좌, 상, 우, 하
dr = [0, 1, 0, -1]
dc = [-1, 0, 1, 0]

dict = {0: left, 1: down, 2: right, 3: up}
time = 0
for i in range(2 * N - 1):
    # 목과나머지로
    d = i % 4
    if d == 0 or d == 2:
        time += 1
    for _ in range(time):
        nr = sr + dr[d]
        nc = sc + dc[d]
        solution(nr, nc, dict[d])  # c좌표 방향
        sr, sc = nr, nc

print(ans)
