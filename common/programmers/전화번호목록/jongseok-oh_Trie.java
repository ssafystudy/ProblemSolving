class Solution {
    
    class Trie{
        private boolean isEnd;
        private Trie[] next;
        Trie(){
            this.next = new Trie[10];
        }
        
        void insert(String s, int index){
            if(s.length() == index){
                this.isEnd = true; return;
            }
            int num = s.charAt(index) - '0';
            if(next[num] == null) next[num] = new Trie();
            next[num].insert(s,index+1);
        }
        
        boolean check(String s, int index){
            if(s.length() != index && this.isEnd) return false;
            if(s.length() == index) return true;
            return next[s.charAt(index) - '0'].check(s,index +1);
        }
    }
    
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Trie root = new Trie();
        
        for(String s: phone_book){ // O(nm)
            root.insert(s,0);
        }
        
        for(String s: phone_book){ // O(m)
            if(!root.check(s, 0)) return false;
        }
        // -> O(nm)
        return answer;
    }
}