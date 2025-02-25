
import java.util.Arrays;
import java.util.Collections;


class Q135808 { // 프로그래머스 Lv1 과일 장수 
    // https://school.programmers.co.kr/learn/courses/30/lessons/135808
    /*
     * 한 상자에 사과를 m개씩 담아 포장
     * k : 사과의 최대 점수
     * 상자에 담긴 사과 중 가장 낮은 점수가  p (1 ≤ p ≤ k)점인 경우, 사과 한 상자의 가격은 p * m
     * return 가능한 많은 사과를 팔았을 때, 얻을 수 있는 최대 이익
     * 이익이 발생하지 않는 경우 0을 리턴
     */
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        // score를 내림차순 정렬하기 위해 래퍼 클래스로 변환(박싱) 
        Integer[] ArrScore = Arrays.stream(score).boxed().toArray(Integer[]::new);
        // score를 내림차순 정렬
        Arrays.sort(ArrScore, Collections.reverseOrder()); 
        for(int i = 0; i < ArrScore.length; i += m) {
            int pIdx = i + m - 1; // score를 m만큼 잘랐을 때, 각 부분에서 맨 마지막 수가 p가 됨.
            if (pIdx < ArrScore.length) 
                answer += ArrScore[pIdx] * m; // 각 부분별로 이익을 계산하여 누적적
        }
        return answer;
    }

    public static void main(String[] args) { 
        Q135808 question = new Q135808(); 
        int k = 3;
        int m = 4;
        int[] score = {1, 2, 3, 1, 2, 3, 1};
        System.out.println(question.solution(k, m, score)); 
    } 
}