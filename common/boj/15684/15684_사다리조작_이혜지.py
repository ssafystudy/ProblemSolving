import sys

input = sys.stdin.readline


# i번 세로선의 결과가 i번인지 확인하는 메서드
def check():
    for i in range(N):
        tmp = i  # 이동할 세로선
        for j in range(H):
            if graph[j][tmp]:  # 오른쪽이 1이면
                tmp += 1
            elif tmp > 0 and graph[j][tmp - 1]:  # 왼쪽이 1이면
                tmp -= 1
        if tmp != i:
            return False
    return True


# 사다리를 놓는 메서드
def dfs(cnt, r, c):  # 사다리 개수, 행, 열
    global ans
    # 조건
    if check():
        ans = min(ans, cnt)
        return
    elif cnt == 3 or ans <= cnt: #cnt 3이면 다음꺼 4돼서 안됨, cnt가 ans보다 크면 안봐도 됨
        return

    # 행탐색
    for i in range(r, H):
        # 가로선 우선 탐색
        if i == r: k = c # 행이 바뀌지 않으면 가로선을 계속 확인
        else: k = 0 # 아니면 다시 가로 첨부터
        for j in range(k, N - 1):  # 열탐색
            if not graph[i][j] and not graph[i][j + 1]: # 가로선 안놓여있고 오른쪽이 비어있으면
                if j > 0 and graph[i][j - 1]: continue # 만약 왼쪽에 가로선이 있으면 두면 안됨
                graph[i][j] = 1 # 가로선 놓기
                # 개수 하나 증가, 세로선은 두고, 가로선은 두개 증가 하나 증가해버리면 -- 이렇게 되니까 두칸씩
                dfs(cnt + 1, i, j + 2)
                graph[i][j] = 0 #되돌리기


N, M, H = map(int, input().split())  # 세로선, 가로선, 세로선마다 가로선 놓을수있는 위치 개수

graph = [[0] * N for _ in range(H)]

for _ in range(M):
    a, b = map(int, input().split())
    graph[a - 1][b - 1] = 1

ans = 4 #4개로 초기화
dfs(0, 0, 0)

print(ans if ans < 4 else - 1)
