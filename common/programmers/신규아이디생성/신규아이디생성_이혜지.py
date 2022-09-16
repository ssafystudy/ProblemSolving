def solution(new_id):
    #1단계
    new_id = new_id.lower()
    #2단계
    word = ''
    for id in new_id:
        if id.isalnum() or id in '-_.':
            word += id

    #3단계
    while '..' in word:
        word = word.replace('..', '.')
    #4단계
    word = word.strip('.')
    #5단계
    word = 'a' if word == '' else word
    #6단계
    if len(word) >= 16:
        word = word[:15]
        if word[-1] == '.':
            word = word[:-1]
    #7단계
    if len(word) <= 3:
        word += word[-1] * (3-len(word))

    return word