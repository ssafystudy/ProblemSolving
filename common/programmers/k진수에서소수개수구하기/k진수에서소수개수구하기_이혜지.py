def convert(n, k):
    ret = ""
    while n > 0:
        ret += str(n % k)
        n = n // k
    return ''.join(reversed(ret))


def isPrime(n):
    if n < 2:
        return False
    for i in range(2, int(n ** 0.5) + 1):
        if n % i == 0:
            return False
    return True


def solution(n, k):
    answer = 0

    kbase_cnum = convert(n, k)

    for c in kbase_cnum.split("0"):
        if c == "": continue
        if isPrime(int(c)):
            answer += 1

    return answer


print(solution(437674, 3))
