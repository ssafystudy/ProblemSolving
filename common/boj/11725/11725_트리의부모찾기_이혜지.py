#노드의 개수 10만
import sys
from collections import deque
input = sys.stdin.readline

N = int(input())
graph = [[] for _ in range(N + 1)]
parent = [0 for _ in range(N + 1)]
for _ in range(N - 1):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

q = deque([1])

while q:
    v = q.popleft()
    for i in graph[v]:
        if parent[i] == 0:
            parent[i] = v
            q.append(i)

for p in parent[2:]:
    print(p)