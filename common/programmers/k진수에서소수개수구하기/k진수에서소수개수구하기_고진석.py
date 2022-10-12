import math

def solution(n, k):
    answer = 0

    N = convert(n,k)
    
    num = ''
    for c in N: 
        if c != '0':
            num += c
        elif num != '':
            if isPrime(num):
                answer += 1
            num = ''

    if num != '' and isPrime(num):
        answer += 1

    return answer

def convert(n, q):
    tmp = ''

    while n > 0:
        n, mod = divmod(n, q)
        tmp += str(mod)

    return tmp[::-1] 

def isPrime(x):
    x = int(x)
    if x == 1:
        return False
    for i in range(2, int(math.sqrt(x))+1):
        if x % i == 0:
            return False
    return True

print(solution(437674,3))