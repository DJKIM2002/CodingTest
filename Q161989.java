public class Q161989 { // 프로그래머스 Lv1 덧칠하기 
    // https://school.programmers.co.kr/learn/courses/30/lessons/161989?language=java
    /*
        n = 페인트가 칠해진 벽의 길이
        m = 벽에 페인트를 칠하는 롤러의 길이 <= n
        section = 벽에 페인트를 칠할 구역들의 번호가 담긴 정수 배열 <= n
        
        벽에 페인트를 한 번 칠하는 규칙
        1. 롤러가 벽에서 벗어나면 안 됨.
        2. 구역의 일부분만 포함되도록 칠하면 안 됨.

        return 롤러로 페인트칠해야 하는 최소 횟수

        ex) n = 8, m = 4, section = [2, 3, 6]
        
        벽의 길이는 8m, 롤러의 길이는 4m이고,
        벽을 8구역으로 나누었을 때, 2, 3, 6번 구역에 페인트를 덧칠해야 하는 상황
        한 번의 페인트칠에 연속된 4개의 구역을 칠할 수 있음.
     */ 

    public int solution(int n, int m, int[] section) {
        if (n == m) { // 벽의 길이와 롤러의 길이가 같다면 
            return 1; // 1을 리턴
        }
        if (m == 1 || section.length == 1) { // 롤러의 길이 또는 칠해야 할 구역의 수가 1이라면 
            return section.length; // 칠해야 할 구역의 수를 리턴
        } 
        int start = section[0]; // 페인트 칠을 시작하는 구역
        int end = section[0] + m - 1; // 페인트 칠을 종료하는 구역 
        int answer = 1; // 최소 1번은 칠해야 함

        for (int s : section) { // section 길이 만큼 반복 
            if (s >= start && s <= end) { // start와 end사이에 있는 구역이면
                continue; // 다음 구역으로 이동 
            } else {
                start = s; 
                end = s + m - 1; // start와 end를 업데이트
                answer++; // 롤러칠 횟수 증가
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Q161989 question = new Q161989();
        int n = 8;
        int m = 4;
        int[] section = {2, 3, 6};
        System.out.println(question.solution(n, m, section));
    } 
}
