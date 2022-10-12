import heapq
import sys
input = sys.stdin.readline
INF = int(1e9)
N = int(input()) #도시 개수
M = int(input()) #버스 개수
#정보를 담는 그래프
graph = [[] for _ in range(N + 1)]
distance = [INF] * (N + 1)

for _ in range(M):
    f, t, c = map(int, input().split())
    graph[f].append((t, c))

start, end = map(int, input().split())

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
                heapq.heappush(q, (cost, i[0]))
dijkstra(start)

print(distance[end])