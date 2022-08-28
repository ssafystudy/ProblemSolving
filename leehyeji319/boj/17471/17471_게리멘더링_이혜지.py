import sys
from itertools import combinations
from collections import deque

input = sys.stdin.readline

N = int(input())
people_size = list(map(int, input().split()))
# print(people_size)
graph = [[] for i in range(N + 1)]

# 인접리스트에 넣기
for i in range(1, N + 1):
    l = list(map(int, input().split()))
    for j in range(1, len(l)):
        graph[i].append(l[j])

# 조합할숫자
comb_num = [int(i) for i in range(1, N + 1)]


def bfs(comb):
    # 정점연결할수잇는지확인
    start = comb[0]
    queue = deque([start])
    visited = set([start])
    _sum = 0
    while queue:
        v = queue.popleft()
        _sum += people_size[v - 1] #사람 수 더해주기
        for i in graph[v]:
            if i not in visited and i in comb:
                queue.append(i)
                visited.add(i)
    return _sum, len(visited)


min_value = int(1e9)
for i in range(1, N // 2 + 1): #반만봐도 됨 왜냐면 어차피 조합 뽑은거 + 안뽑은거 같이 볼거니까
    temp_comb_arr = list(combinations(comb_num, i))
    # print(temp_comb_arr)
    for comb in temp_comb_arr:
        # bfs로 확인할 것 - 조합으로 뽑은거랑 조합을 뺀거 두가지를 bfs돌리고 받아와서 visited의 길이 더한게
        # N이면 다돈거니까 끝
        # 다 돌았는데도 안되면 -1출력
        # print([i for i in range(1, N + 1) if i not in comb])
        print(comb)
        _sum1, _comb_len1 = bfs(comb)
        _sum2, _comb_len2 = bfs([i for i in range(1, N + 1) if i not in comb])

        if _comb_len1 + _comb_len2 == N:
            min_value = min(min_value, abs(_sum1 - _sum2))

if min_value < int(1e9) - 1:
    print(min_value)
else:
    print(-1)
