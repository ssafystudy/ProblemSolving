import sys

sys.setrecursionlimit(10 ** 5)
input = sys.stdin.readline
N, Q = map(int, input().split())
N = 2 ** N
arr = [list(map(int, input().split())) for _ in range(N)]
Ls = list(map(int, input().split()))
dr = [1, -1, 0, 0]
dc = [0, 0, 1, -1]
for L in Ls:
    k = 2 ** L
    for r in range(0, N, k):
        for c in range(0, N, k):
            temp_arr = [arr[i][c:c + k] for i in range(r, r + k)]
            for i in range(k):
                for j in range(k):
                    arr[r + j][c + k - i - 1] = temp_arr[i][j]

    # 사방 얼음 카운팅
    cnt = [[0] * N for _ in range(N)]
    for r in range(0, N):
        for c in range(0, N):
            for d in range(4):
                nr = r + dr[d]
                nc = c + dc[d]
                if 0 <= nr < N and 0 <= nc < N and arr[nr][nc]:
                    cnt[r][c] += 1
    # 얼음 없애기
    for r in range(0, N):
        for c in range(0, N):
            if arr[r][c] > 0 and cnt[r][c] < 3:
                arr[r][c] -= 1

print(sum(sum(i) for i in arr))


# 덩어리 탐색
def dfs(r, c):
    count = 1
    arr[r][c] = 0
    for d in range(4):
        nr = r + dr[d]
        nc = c + dc[d]
        if 0 <= nr < N and 0 <= nc < N and arr[nr][nc]:
            count += dfs(nr, nc)
    return count


answer = 0

for r in range(N):
    for c in range(N):
        if arr[r][c] > 0:
            answer = max(answer, dfs(r, c))
print(answer)
