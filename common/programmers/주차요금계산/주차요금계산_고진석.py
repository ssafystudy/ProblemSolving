import math

def solution(fees, records):
    answer = []
    records = [record.split() for record in records]
    records = sorted(records, key = lambda x : x[1])

    res = dict()
    intime = -1
    carnum = ''
    for r in records:
        if r[2] == 'IN':
            if intime != -1:
                if carnum in res:
                    res[carnum] += totime("23:59") - intime
                else:
                    res[carnum] = totime("23:59") - intime
            intime = totime(r[0])
            carnum = r[1]
        else:
            if r[1] in res:
                res[r[1]] += totime(r[0]) - intime
            else:
                res[r[1]] = totime(r[0]) - intime
            intime = -1
            carnum = ''
    
    if intime != -1:
        if carnum in res:
            res[carnum] += totime("23:59") - intime
        else:
            res[carnum] = totime("23:59") - intime

    for time in res.values():
        if time <= fees[0]:
            answer.append(fees[1])
        else:
            answer.append(fees[1] + math.ceil((time - fees[0])/fees[2]) * fees[3])

    return answer

def totime(msg):
    h, m = map(int,msg.split(":"))
    return 60*h + m

print(solution(	[1, 461, 1, 10], ["00:00 1234 IN"]))