from collections import deque

N = int(input())
K = int(input())
time = 0
# 뱀이 이동할 판
graph = [[0] * N for _ in range(N)]

# 사과 있는 부분 1로 설정
for _ in range(K):
    r, c = map(int, input().split())
    graph[r - 1][c - 1] = 1

L = int(input())
# 뱀의 방향 정보 (딕셔너리로)
command = {}
for _ in range(L):
    s, d = input().split()
    command[int(s)] = d

# 뱀의 몸정보 관리 큐
snake = deque();
snake.append((0, 0))

# 회전 정보 - 북 동 남 서
dr = [-1, 0, 1, 0]
dc = [0, 1, 0, -1]

# 현재 진행 방향 (처음은 동쪽으로 전진)
current_dir = 1
# 뱀의 처음 위치(0,0)을 2로 설정
r, c = 0, 0
graph[r][c] = 2

def turnSnake(next_dir):
    global current_dir
    if next_dir == 'L': #왼쪽회전
        current_dir = (current_dir - 1) % 4
    else: #오른쪽회전
        current_dir = (current_dir + 1) % 4

while True:
    time += 1
    r += dr[current_dir]
    c += dc[current_dir]

    if r < 0 or r >= N or c < 0 or c >= N or (r, c) in snake: #범위체크랑 자기 몸에 부딪히는지 #맨첨에 뱀 몸 부딪히는 조건 안넣엇는데 통과함 머지??
        break

    if graph[r][c] == 1: #사과가 있다면
        graph[r][c] = 2 #뱀늘어나~
        snake.append((r, c)) #큐에 몸정보 추가
        # 딕셔너리 키값 = 방향을 전환할 time시간
        if time in command: #전환할 시간이 된다면
            turnSnake(command[time]) #명령으로 회전한다
    elif graph[r][c] == 0: #사과없으면
        graph[r][c] = 2 #뱀그냥가
        snake.append((r, c)) #뱀몸추가해
        tr, tc = snake.popleft() #뱀 몸정보 지워주기
        graph[tr][tc] = 0 #한칸갓으니까 꼬리없애
        if time in command:
            turnSnake(command[time])
    else:
        break

print(time)
# 사과 있는 칸 1  없는 칸 0 뱀2
