import sys

input = sys.stdin.readline

N, M, K = map(int, input().split())  # N:격자크기 M:파이어볼개수 K:이동명령 횟수
direction = {0: (-1, 0), 1: (-1, 1), 2: (0, 1), 3: (1, 1), 4: (1, 0), 5: (1, -1), 6: (0, -1), 7: (-1, -1)}
graph = [[[] for _ in range(N)] for _ in range(N)]
fireballs = []
for _ in range(M):
    r, c, m, s, d = map(int, input().split())  # 행 열 질량 속력 방향
    fireballs.append([r - 1, c - 1, m, s, d])

for i in range(K):
    # 일단 다 보내
    while fireballs:
        cr, cc, cm, cs, cd = fireballs.pop(0)
        nr = (cr + cs * direction[cd][0]) % N
        nc = (cc + cs * direction[cd][1]) % N
        graph[nr][nc].append([cm, cs, cd])

    for r in range(N):
        for c in range(N):
            if len(graph[r][c]) > 1:
                # 질량 속도 홀수갯수 짝수갯수 그좌표볼개수
                sum_m, sum_s, cnt_odd, cnt_even, cnt = 0, 0, 0, 0, len(graph[r][c])
                while graph[r][c]:
                    _m, _s, _d = graph[r][c].pop(0)
                    sum_m += _m
                    sum_s += _s
                    if _d % 2:
                        cnt_odd += 1
                    else:
                        cnt_even += 1

                if cnt_odd == cnt or cnt_even == cnt:
                    nd = [0, 2, 4, 6]
                else:
                    nd = [1, 3, 5, 7]
                if sum_m // 5 != 0:  # 질량 0이 아닐때만 처리
                    for d in nd: #네갈죽 추가
                        fireballs.append([r, c, sum_m // 5, sum_s // cnt, d])

            if len(graph[r][c]) == 1: #하나일때
                fireballs.append([r, c] + graph[r][c].pop())
print(sum(f[2] for f in fireballs))
