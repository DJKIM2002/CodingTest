import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q42840 { // 프로그래머스 Lv1 모의고사
    // https://school.programmers.co.kr/learn/courses/30/lessons/42840
    /*
     * 1번 수포자가 문제를 찍는 방식 : 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
     * 2번 수포자가 문제를 찍는 방식 : 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
     * 3번 수포자가 문제를 찍는 방식 : 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...
     * return 가장 많은 문제를 맞춘 사람의 번호(여러 명이면 번호를 기준으로 오름차순 정렬)
     */

    public int[] solution(int[] answers) {
        // 수포자들의 패턴 
        int[] studentA = {1, 2, 3, 4, 5};
        int[] studentB = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] studentC = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int scoreA = 0, scoreB = 0, scoreC = 0;

        // 답안 비교 및 점수 계산
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == studentA[i % studentA.length]) scoreA++;
            if (answers[i] == studentB[i % studentB.length]) scoreB++;
            if (answers[i] == studentC[i % studentC.length]) scoreC++;
        }

        // 최고 점수 찾기
        int maxScore = Math.max(scoreA, Math.max(scoreB, scoreC));

        // 최고 점수를 받은 사람 리스트 생성
        List<Integer> topScorers = new ArrayList<>();
        if (scoreA == maxScore) topScorers.add(1);
        if (scoreB == maxScore) topScorers.add(2);
        if (scoreC == maxScore) topScorers.add(3);

        // int 타입 배열로 변환하여 반환
        return topScorers.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        Q42840 question = new Q42840();
        int[] answers = {1,2,3,4,5};
        System.out.println(Arrays.toString(question.solution(answers)));
    } 
}