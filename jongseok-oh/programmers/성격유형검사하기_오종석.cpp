#include <string>
#include <vector>

using namespace std;

int alpha[26];

string solution(vector<string> survey, vector<int> choices) {
    string answer = "----";
    
    for(int i =0, len = survey.size(); i<len; i++){
        if(choices[i] <= 3){
            alpha[survey[i][0] - 'A'] += 4 - choices[i];
        }else if(choices[i] >= 5){
            alpha[survey[i][1] - 'A'] += choices[i] - 4;
        }
    }
    
    if(alpha['R' - 'A'] >= alpha['T' - 'A']) answer[0] = 'R';
    else answer[0] = 'T';
    
    if(alpha['C' - 'A'] >= alpha['F' - 'A']) answer[1] = 'C';
    else answer[1] = 'F';
    
    if(alpha['J' - 'A'] >= alpha['M' - 'A']) answer[2] = 'J';
    else answer[2] = 'M';
    
    if(alpha['A' - 'A'] >= alpha['N' - 'A']) answer[3] = 'A';
    else answer[3] = 'N';
    
    return answer;
}