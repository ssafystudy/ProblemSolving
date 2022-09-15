import collections 

def solution(id_list, report, k):
    answer = []
    # 신고자가 신고한 사람들
    dict_report = collections.defaultdict(set)
    # 사람마다 신고당한 횟수
    dict_target = collections.defaultdict(int)
    #정지당한사람들
    banned_list = []
    #메일 보내는 리스트
    mail_list = []
    #메일 몇번 받는지 리스트
    dict_answer = {}
    
    #회원당 신고자 정지완료 메일 몇번 받는지 초기화
    for idl in id_list:
        dict_answer[idl] = 0
    
    #누가 누구를 신고했는지 set으로
    for r in report:
        reporter, target = r.split()
        dict_report[reporter].add(target)

    #신고몇번당했는지 카운트 세기
    for target in dict_report.values():
        for t in target:
            dict_target[t] += 1
    
    #K번 이상 신고당한사람 찾기
    for ans, cnt in dict_target.items():
        if cnt >= k:
            banned_list.append(ans)
    
    #메일받는사람 리스트에 넣기
    for i, j in dict_report.items():
        for k in banned_list:
            if k in j:
                mail_list.append(i)
    
    #아이디당 정지 완료 메일 몇번받는지
    for ml in mail_list:
        dict_answer[ml] += 1

    #답
    for a in dict_answer.values():
        answer.append(a)
    
    return answer

print(solution(["muzi", "frodo", "apeach", "neo"], ["muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"], 2))