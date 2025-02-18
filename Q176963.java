
import java.util.Arrays;

public class Q176963 { // 프로그래머스 Lv1 추억 점수
    // https://school.programmers.co.kr/learn/courses/30/lessons/176963
    /*
     * name : 그리워하는 사람의 이름을 담은 문자열 배열
     * yearning : 그리워는 사람의 추억 점수를 담은 정수 배열
     * photo : 각 사진에 찍힌 인물의 이름을 담은 이차원 문자열 배열
     * 
     * ex)
     * name = {"may", "kein", "kain", "radi"}
     * yearning = {5, 10, 1, 3};
     * photo = { {"may", "kein", "kain", "radi"}, 
     *           {"may", "kein", "brin", "deny"}, 
     *           {"kon", "kain", "may", "coni"} }
     * 
     * answer = {5 + 10 + 1 + 3 = 19,
     *           5 + 10 + 0 + 0 = 15, 
     *           0 + 1 + 5 + 0 = 6}
     */

    public int[] solution(String[] name, int[] yearning, String[][] photo) {
            int[] answer = new int[photo.length]; // photo 길이만큼 배열 생성 및 초기화 
            for (int i = 0; i < photo.length; i++) { // photo 길이만큼 반복
                for (String n : photo[i]) { // photo[i]를 끝까지 순회하며 요소를 하나씩 가져옴 
                    if (Arrays.asList(name).contains(n)) { // n이 name 배열에 포함된 이름이면
                        int idx = Arrays.asList(name).indexOf(n); // n의 인덱스 찾기(점수 배열과 매칭시키기 위함) 
                        answer[i] += yearning[idx]; // answer[i]에 photo[i]의 추억 점수를 누적
                    }   
                }
            }
            return answer;
    } 

    public static void main(String[] args) { 
        Q176963 question = new Q176963();
        String[] name = {"may", "kein", "kain", "radi"};
        int[] yearning = {5, 10, 1, 3};
        String[][] photo = { {"may", "kein", "kain", "radi"}, 
                             {"may", "kein", "brin", "deny"},
                             {"kon", "kain", "may", "coni"} };
        System.out.println(Arrays.toString(question.solution(name, yearning, photo)));
    } 
}
