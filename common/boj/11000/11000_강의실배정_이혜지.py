# https://www.acmicpc.net/problem/11000
import heapq

N = int(input())
schedules = []
for _ in range(N):
    start, end = map(int, input().split())
    schedules.append((start, end))
schedules.sort()

he = []

end_time = schedules[0][1]
heapq.heappush(he, end_time)
for i in range(1, N):
    if schedules[i][0] >= he[0]:
        end_time = schedules[i][1]
        heapq.heappop(he)
        heapq.heappush(he, end_time)
    elif schedules[i][0] < end_time:
        heapq.heappush(he, schedules[i][1])
print(len(he))
