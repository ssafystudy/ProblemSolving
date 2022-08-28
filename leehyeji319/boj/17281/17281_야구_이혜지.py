import sys
from itertools import permutations

input = sys.stdin.readline
N = int(input())
innings = [list(map(int, input().split())) for _ in range(N)]

max_score = 0

for perm in permutations(range(1, 9), 8):
    perm = list(perm[:3]) + [0] + list(perm[3:])

    hitter_idx = 0
    score = 0
    for inning in innings:
        out_cnt = 0
        g1, g2, g3 = 0, 0, 0
        while out_cnt < 3:

            if inning[perm[hitter_idx]] == 0:
                out_cnt += 1
            elif inning[perm[hitter_idx]] == 1:
                score += g3
                g1, g2, g3 = 1, g1, g2
            elif inning[perm[hitter_idx]] == 2:
                score += (g2 + g3)
                g1, g2, g3 = 0, 1, g1
            elif inning[perm[hitter_idx]] == 3:
                score += (g1 + g2 + g3)
                g1, g2, g3 = 0, 0, 1
            elif inning[perm[hitter_idx]] == 4:
                score += (1 + g1 + g2 + g3)
                g1, g2, g3 = 0, 0, 0
            hitter_idx = (hitter_idx + 1) % 9
    max_score = max(max_score, score)

print(max_score)


##===================시간초과========================##
# import sys
# from collections import defaultdict
# from itertools import permutations
#
# input = sys.stdin.readline
# N = int(input())
# players = defaultdict(list)
# for _ in range(N):
#     players_scores = list(map(int, input().split()))
#     for idx, score in enumerate(players_scores):
#         players[idx + 1].append(score)
# players_without_one = [2, 3, 4, 5, 6, 7, 8, 9]
# players_without_one = list(permutations(players_without_one))
# max_score = 0
#
# for players_order in players_without_one:
#     players_order = list(players_order)
#     players_order = players_order[:3] + [1] + players_order[3:]
#     current_hitter_idx = 0
#     score = 0
#     for ining in range(N):
#         out_cnt = 0
#         ground = [False, False, False]
#         while out_cnt < 3:
#             result = players[players_order[current_hitter_idx]][ining]
#
#             if result == 0:
#                 out_cnt += 1
#             else:
#                 next_ground = [False] * (result - 1) + [True]
#                 ground = next_ground + ground
#                 for player in ground[3:]:
#                     if player == True:
#                         score += 1
#                 ground = ground[:3]
#
#             current_hitter_idx = (current_hitter_idx + 1) % 9
#     max_score = max(score, max_score)
#
# print(max_score)