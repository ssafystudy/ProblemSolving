#include <string>
#include <vector>
#include <iostream>

using namespace std;

long long prefixTime[3600 * 99 + 60 * 59 + 80];

int timeToInt(string time){
    int ret = 0;
    ret += stoi(time.substr(0,2)) * 3600;
    ret += stoi(time.substr(3,2)) * 60;
    ret += stoi(time.substr(6,2));
    return ret;
}

string timeToString(int time){
    string ret;
    
    int h = time/3600;
    if(h <10) ret.append("0");
    ret.append(to_string(h));
    ret.append(":");
    
    int m = time%3600/60;
    if(m<10) ret.append("0");
    ret.append(to_string(m));
    ret.append(":");
    
    int s = time%60;
    if(s<10) ret.append("0");
    ret.append(to_string(s));
    
    return ret;
}


string solution(string play_time, string adv_time, vector<string> logs) {
    int pt = timeToInt(play_time);
    int at = timeToInt(adv_time);
    
    for(string log: logs){
        int st = timeToInt(log.substr(0,8));
        int et = timeToInt(log.substr(9));
        prefixTime[st]++;
        prefixTime[et]--;
    }
    
    for(int i =0; i<=pt; i++) prefixTime[i] += prefixTime[i-1];
    for(int i =0; i<=pt; i++) prefixTime[i] += prefixTime[i-1];
    
    long long _max = -1;
    int startTime = -1;
    
    for(int i = 0; i<=pt - at + 1; i++){
        long long tt = prefixTime[i + at - 1] - prefixTime[i - 1];
        if(tt> _max){
            _max = tt;
            startTime = i;
        }
    }
    return timeToString(startTime);
}