N = int(input())

homework = []
for _ in range(N):
    homework.append(list(map(int, input().split())))
homework.sort()

idx = homework[-1][0]
#뒤 날짜부터시작
able = []
ans = 0

while idx > 0:
    for i in range(len(homework)):
        if homework[i][0] == idx:
            able.append(homework[i])
    able = sorted(able, key=lambda x: x[1])

    if len(able) != 0:
        ans += able[-1][1]
        able.pop()
    idx -= 1

print(ans)



# 7
# 4 60
# 4 40
# 1 20
# 2 50
# 3 30
# 4 10
# 6 5