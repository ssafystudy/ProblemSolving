import sys

input = sys.stdin.readline
sys.setrecursionlimit(10 ** 8)

N = int(input())
tree = [[] for _ in range(N + 1)]

for _ in range(N - 1):
    a, b, c = map(int, input().split())  # 노드,노드,비용
    tree[a].append((b, c))
    tree[b].append((a, c))


def dfs(start):
    for v, c in tree[start]:
        if distance[v] == -1:
            distance[v] = distance[start]
            dfs(v)


distance = [-1] * (N + 1)
distance[1] = 0
dfs(1)

find_node = distance.index(max(distance))
distance = [-1] * (N + 1)
distance[find_node] = 0
dfs(find_node)

print(max(distance))

'''
def dfs(start):
    global m
    global max_idx
    for v, c in tree[start]:
        if distance[v] == -1:
            distance[v] = distance[start] + c
            # if m < distance[v]:
                max_idx = v
                m = distance[v]
            dfs(v)


m = -1
max_idx = 0
distance = [-1] * (N + 1)
distance[1] = 0
dfs(1)


next_idx = max_idx
m = -1
max_idx = 0
distance = [-1] * (N + 1)
distance[next_idx] = 0
dfs(next_idx)
'''
