def solution(id_list, report, k):
    new_report = []
    for r in report:
        if not r in new_report:
            new_report.append(r)

    n = len(id_list)
    cnt = dict()
    mail = dict()
    for id in id_list:
        cnt[id] = 0
        mail[id] = 0

    for r in new_report:
        y = r.split()[1]
        cnt[y] += 1
    
    for r in new_report:
        x = r.split()[0]
        y = r.split()[1]
        if cnt[y] >= k:
            mail[x] += 1

    return list(mail.values())


print(solution(["muzi", "frodo", "apeach", "neo"],["muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"],2))