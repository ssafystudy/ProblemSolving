def solution(info, edges):
    res = 0
    v = [0] * len(info)
    q = []
    v[0] = 1
    q.append((0,1,0,v.copy()))

    while q:
        node, sheep, wolf, visited = q.pop()
        if sheep <= wolf:
            continue
        res = max(res,sheep)

        for i in range(len(visited)):
            if visited[i] == 1:
                for e in edges:
                    if e[0] == i and visited[e[1]] == 0:
                        next = e[1]
                        visited[next] = 1
                        q.append((next,sheep + 1 - info[next],wolf + info[next], visited.copy()))
                        visited[next] = 0

    return res

print(solution([0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1], [[0, 1], [1, 2], [1, 4], [0, 8], [8, 7], [9, 10], [9, 11], [4, 3], [6, 5], [4, 6], [8, 9]]))