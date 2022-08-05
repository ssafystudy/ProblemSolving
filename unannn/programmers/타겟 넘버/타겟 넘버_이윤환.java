class Solution {
    
    public int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }
    /*
        index : 현재 조회하고 있는 numbers 의 index
        sum   : 현재 조회하고  numbers 에서 depth번째 수까지 누적 연산한 값
    */
    private int dfs(int[] numbers, int target, int sum, int index){
        //numbers의 마지막 수까지 연산을 끝냈을때
        if(index == numbers.length){
            //마지막 수까지 연산한 결과가 target 과 같으면 1 반환, 다르면 0 반환
            return sum == target ? 1 : 0;
        }
        
        return dfs(numbers, target, sum + numbers[index], index + 1) 
            + dfs( numbers, target, sum - numbers[index], index + 1);
    }
}