#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int answer = 1e9;

long long sSum = 0, lSum = 0;

// small queue, larger queue, target
int solve(const vector<int>& sq, const vector<int>& lq, long long target){
    int ret = 1e9;
    
    int n = lq.size();
    int m = sq.size();
    
    int low = 0, high = 0;
    long long tSum = lq[0];
    
    // case 1.
    // 누적합의 크기가 더 큰 queue에서 가장 먼저
    // 부분합의 크기가 target이 되는 지점을 search
    while(high<n){
        if(tSum < target){
            if(++high == n) break;
            tSum += lq[high];
        }else{
            if(tSum == target) break;
            tSum -= lq[low++];
        }
    }
    
    // lq에서 부분합과 부분합 앞에 부분을 sq로 옮기고
    // lq에서 옮긴 부분합을 제외한 모든 부분을 sq에서 lq로 옮기는 가지 수
    if(tSum == target){
        int t = high + 1 + sq.size() + low;
        ret = min(t, ret);
    }
    
    // case 2.
    // 두 큐의 앞에서 부터 한개씩 스왑 해 보면서
    // sSum과 lSum이 같아지는 지 확인
    int cnt = 0;
    int lIdx = 0, sIdx = 0;
    while(true){
        if(lSum == sSum) break;
        if(lSum > sSum){
            lSum -= lq[lIdx];
            sSum += lq[lIdx++]; cnt++;
            if(lIdx == n) break;
        }else if(lSum < sSum){
            sSum -= sq[sIdx];
            lSum += sq[sIdx++]; cnt++;
            if(sIdx == m) break;
        }
    }
    if(lSum == sSum) ret = min(ret, cnt);
    return ret;
}

int solution(vector<int> queue1, vector<int> queue2) {
    for(const int& a: queue1) sSum += a;
    for(const int& b: queue2) lSum += b;
    
    long long target = (sSum + lSum) >>1;
    
    bool aMin = sSum < lSum;
    if(!aMin){
        long long temp = sSum;
        sSum = lSum;
        lSum = temp;
    }
    
    if(aMin) answer = min(solve(queue1, queue2, target), answer);
    else answer = min(solve(queue2, queue1, target), answer);
    
    return (answer == 1e9? -1: answer);
}