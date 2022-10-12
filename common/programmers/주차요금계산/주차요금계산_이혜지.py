from collections import defaultdict
import math


def solution(fees, records):
    answer = []
    car_each_time = []

    record_dic = defaultdict(list)

    for i in range(len(records)):
        time, car_number, state = records[i].split(" ")
        hour, minute = time.split(":")
        car_time = int(hour) * 60 + int(minute)
        record_dic[car_number].append(car_time)

    car_nums = list(record_dic.keys())

    for r in range(len(car_nums)):
        if len(record_dic[car_nums[r]]) % 2 == 1:
            record_dic[car_nums[r]].append(1439)

    record_dic = sorted(record_dic.items())

    for i in record_dic:
        sum = 0
        for j in range(0, len(i[1]) - 1, 2):
            sum += i[1][j + 1] - i[1][j]

        car_each_time.append(sum)

    for i in car_each_time:
        if i >= fees[0]:
            answer.append(fees[1] + math.ceil((i - fees[0]) / fees[2]) * fees[3])
        else:
            answer.append(fees[1])
    return answer


print(solution([180, 5000, 10, 600],
               ["05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN",
                "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"]))
