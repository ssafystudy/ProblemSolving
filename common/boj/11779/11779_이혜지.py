import heapq
import sys
input = sys.stdin.readline
INF = int(1e9)

n = int(input())
m = int(input())
graph = [[] for _ in range(n + 1)]
distance = [INF] * (n + 1)


for _ in range(m):
    f, t, c = map(int, input().split())
    graph[f].append((t, c))
start, end = map(int, input().split())
near_list = [start] * (n + 1) #가장 가까운 노드 기록

def dijkstra(start):
    q = []
    heapq.heappush(q, (0, start))
    distance[start] = 0
    while q:
        dist, now = heapq.heappop(q)
        if distance[now] < dist:
            continue
        for i in graph[now]:
            cost = dist + i[1]
            if cost < distance[i[0]]:
                distance[i[0]] = cost
                near_list[i[0]] = now
                heapq.heappush(q, (cost, i[0]))
dijkstra(start)

ans = []

tmp = end
while tmp != start:
    ans.append(str(tmp))
    tmp = near_list[tmp]

ans.append(start)
ans.reverse()

print(distance[end])
print(len(ans))
for a in ans:
    print(a, end = " ")
