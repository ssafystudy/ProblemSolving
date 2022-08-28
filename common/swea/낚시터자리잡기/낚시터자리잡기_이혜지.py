import sys
input = sys.stdin.readline

#num: 게이트 위치 번호, angler: 해당 게이트에서 입장하는 낚시꾼의 수
def entrance_gate(num, angler, r):
    # ret: 모든 낚시꾼 이동 총 거리, res2: 마지막 낚시꾼이 왼쪽에 입장하고 동일한 거리의 오른쪽에 입장가능한 경우 위치 변환을 위한 변수
    # dist: 게이트에서 떨어진 정도(좌우 절댓값으로 증가)
    # flag: 좌우로 체크할 변수 (-1, 1)
    ret = 0
    ret2 = False #고려하지 않아도 된다면
    dist = 0
    flag = -1

    while angler:
        x = num + (dist * flag) #너비 우선 탐색
        # 이동하고자하는 좌표 x가 낚시터 범위내이면서 먼저온 낚시꾼이 없는 경우,
        # 게이트 위치 번호로 어느 게이트로 들어온 낚시꾼인지 확인할 수 있게 표시
        if 0 <= x < N and fishing[x] == 0:
            fishing[x] += r
            ret += dist + 1
            angler -= 1
        # 같은 거리만큼 떨어진 오른쪽 확인하기 위해 flag *= -1
        flag *= -1

        # 왼쪽 먼저 검사 후, 오른쪽을 검사하고 다시 왼쪽을 검사한 경우는 다음칸을 봐야하기 때문에 cnt += 1
        if flag < 0 and angler != 0:
            dist += 1

        # 마지막 사람이 왼쪽에 들어간 경우, 오른족에 들어갈 수 있는 경우가 있는지 체크 후 해당 위치 반환
        if flag > 0 and angler == 0:
            x2 = num + (dist * flag)
            # 위 조건을 만족해 오른쪽에 입장한 경우도 체크해야하는 경우
            if 0 <= x2 < N and fishing[x2] == 0:
                # x는 기존에 왼쪽에 채워진 마지막 낚시꾼 위치, x2는 확인해보아야할 오른쪽 낚시꾼 위치
                ret2 = [x, x2]
    return ret, ret2


def dfs(n, total): #n: 재귀횟수, total: 낚시꾼이 이동한 총 거리
    global result
    if n == 3:
        if total < result:
            result = total
        return

    for i in range(3):
        if used[i]: #방문배열 used를 이용해 순열생성
            continue
        used[i] = 1 #사용처리
        ret, ret2 = entrance_gate(info[i][0], info[i][1], i + 1) #낚시꾼을 입장시킨다
        dfs(n + 1, total + ret)

        # 마지막 낚시꾼이 좌측 입장 & 오른쪽 같은거리에 빈자리가 있어 확인해야하는 경우
        # false가 아닌 경우
        if ret2:
            fishing[ret2[0]] = 0 #왼쪽 낚시터 0으로
            fishing[ret2[1]] = i + 1
            dfs(n + 1, total + ret)
            fishing[ret2[1]] = 0
            fishing[ret2[0]] = i + 1

        #fishing 배열의 해당 낚시꾼 입장 초기화
        for j in range(N):
            if fishing[j] == i + 1:
                fishing[j] = 0
        used[i] = 0
    return


T = int(input())

for tc in range(1, T + 1):
    N = int(input())
    fishing = [0] * N #낚시터에 낚시꾼이 있는지 확인
    info = []
    result = float('inf')
    used = [0] * 3
    
    for n in range(3):
        gate, pcnt = map(int, input().split())
        info.append([gate - 1, pcnt])
    dfs(0, 0)
    print(f"#{tc} {result}")