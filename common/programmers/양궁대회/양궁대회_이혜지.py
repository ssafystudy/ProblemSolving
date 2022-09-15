from copy import deepcopy

max_diff = 0
answer = []


# dfs
def play(info, rion_info, total_cnt, idx):
    global answer, max_diff
    # 기저 조건
    if idx == 11:
        if total_cnt != 0:
            rion_info[10] = total_cnt
        score_diff = grading_score(info, rion_info)
        if score_diff <= 0:
            return
        result = deepcopy(rion_info)
        if score_diff > max_diff:
            max_diff = score_diff
            answer = [result]
            return
        if score_diff == max_diff:
            answer.append(result)
        return

    if info[idx] < total_cnt:
        rion_info.append(info[idx] + 1)
        play(info, rion_info, total_cnt - info[idx] - 1, idx + 1) #재귀호출
        rion_info.pop() #값 빼주기

    # 점수를 먹지 않는 경우
    rion_info.append(0)
    play(info, rion_info, total_cnt, idx + 1)
    rion_info.pop()


# 점수계산
def grading_score(info, rion_info):
    apeach_score, rion_score, score_gap = 0, 0, 0
    for i in range(11):
        if (info[i], rion_info[i]) == (0, 0):
            continue
        if info[i] >= rion_info[i]:
            apeach_score += (10 - i)

        else:
            rion_score += (10 - i)

    score_gap = rion_score - apeach_score
    return score_gap


def solution(n, info):
    global answer, max_diff
    play(info, [], n, 0)
    # 라이언이 다 못쐈을 때
    if not answer:
        return [-1]

    # 방법이 여러 가지 일 경우, 가장 낮은 점수를 더 많이 맞힌 경우
    answer.sort(key=lambda x: x[::-1], reverse=True)

    return answer[0]

