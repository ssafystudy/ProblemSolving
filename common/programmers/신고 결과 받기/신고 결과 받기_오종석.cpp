#include <string>
#include <vector>
#include <unordered_map>
#include <set>

using namespace std;

vector<int> solution(vector<string> id_list, vector<string> report, int k) {
    vector<int> answer(id_list.size(),0);
    unordered_map<string, set<string>> reporting;
    unordered_map<string, int> reported;
    
    for(const string& r: report){
        int idx = r.find(" ");
        string reporterName = r.substr(0,idx);
        string reportedName = r.substr(idx+1);
        
        if(reporting[reporterName].find(reportedName) == reporting[reporterName].end()){
            reporting[reporterName].insert(reportedName);
            reported[reportedName]++;
        }
    }
    
    for(int i = 0, len = id_list.size(); i<len; i++){
        string user = id_list[i];
        for(string r : reporting[user]){
            if(reported[r]>=k) answer[i]++;
        }
    }
    
    return answer;
}