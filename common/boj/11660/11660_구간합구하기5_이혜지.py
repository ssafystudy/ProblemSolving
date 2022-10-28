import sys

input = sys.stdin.readline

N, M = map(int, input().split())

sum_b = [[0] * (N + 1) for _ in range(N + 1)]
board = [list(map(int, input().split())) for _ in range(N)]

for i in range(1, N + 1):
    for j in range(1, N + 1):
        sum_b[i][j] = sum_b[i - 1][j] + sum_b[i][j - 1] - sum_b[i - 1][j - 1] + board[i - 1][j - 1]

for _ in range(M):
    x1, y1, x2, y2 = map(int, input().split())
    print(sum_b[x2][y2] - sum_b[x1 - 1][y2] - sum_b[x2][y1 - 1] + sum_b[x1 - 1][y1 - 1])
