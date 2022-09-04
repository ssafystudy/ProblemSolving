#include <string>
#include <vector>
#include <sstream>
#include <map>
#include <unordered_map>

using namespace std;

int getMinute(string time){
    return stoi(time.substr(0,2)) * 60 + stoi(time.substr(3,2));
}

vector<int> solution(vector<int> fees, vector<string> records) {
    map<string, int> m;
    unordered_map<string, int> inTime;
    
    int MAX_TIME = 23*60 + 59;
    
    for(string record : records){
        istringstream sstr(record);
        string time, carNum, inout;
        sstr>>time>>carNum>>inout;

        int minute = getMinute(time);
        if(inout == "IN") inTime[carNum] = minute;
        else {
            m[carNum] += minute - inTime[carNum];
            inTime[carNum] = -1;
        }
    }
    
    for(auto it: inTime){
        if(it.second != -1){
            m[it.first]+= MAX_TIME - it.second;
        }
    }
    
    vector<int> v(m.size());
    
    int idx = 0;
    for(auto _m: m){
        int time = _m.second;
        int money = fees[1];
        
        if(fees[0] < time){
            money += (time - fees[0] + fees[2] - 1)/fees[2] * fees[3];
        }
        v[idx++] = money;
    }
    
    return v;
}