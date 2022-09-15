minArr = []
res = [0 for _ in range(11)]
maxScore = -100
N = 0

def solution(n, info):
    global minArr, N
    minArr = [x+1 for x in info]
    N = n

    winpos = []
    for i in range(10):
        winpos.append(i)
        dfs(winpos, i, info)
        winpos.pop(-1)
    
    if maxScore <= 0:
        return [-1]
    return res

def dfs(winpos, idx, info):
    sum = 0
    for p in winpos:
        sum += minArr[p]
    if sum > N:
        return
    else:
        info1 = [0 for _ in range(11)]
        for p in winpos:
            info1[p] = minArr[p]
        info1[10] += N - sum
        global maxScore, res
        sc = score(info1,info)
        if sc > maxScore:
            maxScore = sc
            res = info1
        elif sc == maxScore:
            for i in range(10,-1,-1):
                if info1[i] > res[i]:
                    res = info1
                    break
                elif info1[i] < res[i]:
                    break
    for i in range(idx+1,10):
        winpos.append(i)
        dfs(winpos, i, info)
        winpos.pop(-1)

def score(info1, info2):
    res = 0
    for i in range(10):
        if info1[i] > info2[i]:
            res += 10-i
        elif info2[i] != 0:
            res -= 10-i
    return res

print(solution(10, [0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3]))