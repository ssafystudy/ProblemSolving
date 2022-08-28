import sys
from itertools import permutations
from collections import defaultdict

input = sys.stdin.readline
# 브루트 포스 ?
T = int(input())
for tc in range(1, T + 1):
    N = int(input())
    old_door_people_dict = defaultdict(list)
    people_len = 0
    #출입문과 낚시꾼 정보 입력받기(딕셔너리)
    for _ in range(3):
        a, b = map(int, input().split())
        old_door_people_dict[a - 1] = [i for i in range(people_len + 1, b + people_len + 1)]  # 딕셔너리로 저장
        people_len += b
    # 딕셔너리 뒤집기
    door_people_dict = {}
    for key, value in old_door_people_dict.items():
        for val in value:
            if val in door_people_dict:
                door_people_dict[val].append(key)
            else:
                door_people_dict[val] = [key]

    able_people = [0] * people_len
    for i in range(people_len):
        able_people[i] = i + 1

    #순열을 위한 배열 생성
    min_value = int(1e9)
    able_people += [0] * (N - people_len)

    #순열 돌리기
    for perm in permutations(able_people):
        distance = 0
        perm = list(perm)

        for i in range(N):
            if perm[i] != 0:
                distance += abs(door_people_dict[perm[i]][0] - i) + 1
        min_value = min(min_value, distance)

    print(f"#{tc} {min_value}")
