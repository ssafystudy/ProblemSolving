def roll(command, dice):
    a, b, c, d, e, f = \
        dice[0], dice[1], dice[2], dice[3], dice[4], dice[5]
    if command == 1:  #
        dice[0], dice[1], dice[2], dice[3], dice[4], dice[5] = d, b, a, f, e, c
    elif command == 2:
        dice[0], dice[1], dice[2], dice[3], dice[4], dice[5] = c, b, f, a, e, d
    elif command == 3:
        dice[0], dice[1], dice[2], dice[3], dice[4], dice[5] = e, a, c, d, f, b
    elif command == 4:
        dice[0], dice[1], dice[2], dice[3], dice[4], dice[5] = b, f, c, d, a, e


N, M, R, C, K = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(N)]
commands = list(map(int, input().split()))
dr = [0, 0, -1, 1]
dc = [1, -1, 0, 0]
dice = [0] * 6

nr, nc = R, C
for command in commands:
    nr += dr[command - 1]
    nc += dc[command - 1]

    if nr < 0 or nc < 0 or nr >= N or nc >= M:
        nr -= dr[command - 1]
        nc -= dc[command - 1]
        continue
    roll(command, dice)
    if graph[nr][nc] != 0:
        dice[-1] = graph[nr][nc]
        graph[nr][nc] = 0
    else:
        graph[nr][nc] = dice[-1]
    print(dice[0])
