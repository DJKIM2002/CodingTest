
import java.util.HashMap;
import java.util.Map;

public class Q118666 { // 프로그래머스 Lv1 성격 유형 검사하기
    // https://school.programmers.co.kr/learn/courses/30/lessons/118666
    /**
     * @param survey 질문마다 판단하는 지표를 담은 1차원 문자열 배열
     * @param choices 각 질문마다 선택한 선택지 번호를 담은 1차원 정수 배열
     * @return 검사자의 성격 유형 검사 결과, 지표 번호 순서대로, 같은 지표 번호에서 같은 점수가 나오면 사전 순으로 빠른 순서대로로
     */
    public String solution(String[] survey, int[] choices) {
        // 각 성격 유형별 점수를 저장할 맵 초기화
        Map<Character, Integer> scores = new HashMap<>(); 
        scores.put('R', 0); scores.put('T', 0);
        scores.put('C', 0); scores.put('F', 0);
        scores.put('J', 0); scores.put('M', 0);
        scores.put('A', 0); scores.put('N', 0);
        
        // 설문 결과 계산
        for (int i = 0; i < survey.length; i++) {
            char type1 = survey[i].charAt(0);
            char type2 = survey[i].charAt(1);
            
            int score = choices[i];
            if (score < 4) { // 4보다 작은 경우(매우 비동의 ~ 약간 비동의) -> 유형1의 점수를 앞에서부터 4, 3, 2, 1씩 부여
                scores.put(type1, scores.get(type1) + (4 - score));
            } else if (score > 4) { // 4보다 큰 경우(약간 동의 ~ 매우 동의) -> 유형2의 점수를 뒤에서부터 4, 3, 2, 1씩 부여
                scores.put(type2, scores.get(type2) + (score - 4));
            }
            // 4인 경우 -> 모르겠음을 선택했으므로 아무 점수도 부여하지 않음
        }
        
        // 최종 성격 유형 결정
        StringBuilder answer = new StringBuilder();
        // 각 지표 별로 점수가 더 높은 쪽을 선택
        answer.append(scores.get('R') >= scores.get('T') ? 'R' : 'T');
        answer.append(scores.get('C') >= scores.get('F') ? 'C' : 'F');
        answer.append(scores.get('J') >= scores.get('M') ? 'J' : 'M');
        answer.append(scores.get('A') >= scores.get('N') ? 'A' : 'N');
        
        return answer.toString();
    }
    
    public static void main(String[] args) { 
        Q118666 question = new Q118666(); 
        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choice = {5, 3, 2, 7, 5};
        System.out.println(question.solution(survey, choice)); 
    } 
}

