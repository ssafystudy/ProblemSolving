#https://www.acmicpc.net/problem/3020
from bisect import bisect_left
N, H = map(int, input().split()) #종유석순개수, 높이

top = []
bottom = []
for i in range(N):
    height = int(input())
    if i % 2 == 0: #석순
        bottom.append(height)
    else:
        top.append(height)

bottom.sort()
top.sort()
min_v = int(1e9) #최소값
cnt = 1 #구간수

for h in range(1, H + 1):
    t = bisect_left(top, (H+1) - h)
    b = bisect_left(bottom, h)
    total = N - (t + b)
    if min_v > total:
        min_v = total
        cnt = 1
    elif total == min_v:
        cnt += 1

print(min_v, cnt)