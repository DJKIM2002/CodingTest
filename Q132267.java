public class Q132267 { // 프로그래머스 Lv1 콜라 문제
    // https://school.programmers.co.kr/learn/courses/30/lessons/132267?language=java
    /*
     * 콜라 빈 병 a개를 콜라 병 b개로 교환해주는 마트가 있는데,
     * 이 마트에 빈 병 n개를 가져다주면 몇 병을 받을 수 있는기?
     * 단, 1 ≤ b < a ≤ n ≤ 1,000,000
     * 
     * ex) 만약 가지고 있는 빈 병이 20개, 마트에서 빈 병 2개를 콜라 병 1개로 
     * 교환이 가능하다면, 20개를 가져다주고, 콜라 10병을 받는다.
     * 이후, 콜라 10병을 모두 마시고 난 빈 병을 다시 마트에 가져가 콜라 5병을 받는다.
     * 또, 콜라 5병을 모두 마시고 난 빈 병을 다시 마트에 가져가 콜라 2병을 받는다. 
     * 이때 남는 빈 병 1개는 교환하지 못한다.
     * 위 과정을 반복하여, 최대로 받을 수 있는 콜라 병 수는
     * 10 + 5 + 2 + 1 + 1 = 19병이다.
     */
    public int solution(int a, int b, int n) {
        int answer = 0; // 받을 수 있는 콜라 병의 총 개수

        while(n >= a) {
            int exchange = n / a * b; // 교환받을 수 있는 콜라 병 개수 
            int left = n % a; // 교환하지 못하고 남은 빈 병 개수
            answer += exchange; // 교환받은 콜라 병 개수 누적
            n = exchange + left; // 교환받은 콜라를 모두 마신 후, 가지고 있는 모든 빈 병 개수  
        }
        return answer;
    }

    public static void main(String[] args) {
        Q132267 question = new Q132267(); 
        int a = 2; // 교환에 필요한 빈 병의 개수
        int b = 1; // 교환받을 콜라 병의 개수
        int n = 20; // 가지고 있는 빈 병의 개수
        System.out.println(question.solution(a, b, n));
    } 
}
