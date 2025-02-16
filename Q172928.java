public class Q172928 { // 프로그래머스 Lv1 공원 산책
    // https://school.programmers.co.kr/learn/courses/30/lessons/172928
    /*
       지나다니는 길 'O'
       장애물 'X'
       1. 주어진 방향으로 이동할 때 공원을 벗어나는지 확인
       2. 주어진 방향으로 이동 중 장애물을 만나는지 확인
       위 조건 중 하나라도 해당한다면, 해당 명령을 무시하고, 다음 명령을 수행
       routes = ["방향 거리", "방향 거리", ...] 
       return [세로 방향 좌표, 가로 방향 좌표]

       ex)
       park = ["SOO", "OOO", "OOO"], park에서 S = 시작시점
       routes = ["E 2", "S 2", "W 1"]
       시작 지점 [0, 0] 으로부터 동쪽으로 2칸 이동 [0, 2]
       남쪽으로 2칸 이동 [2, 2]
       서쪽으로 1칸 이동 [2, 1]
       return [2, 1]
     */

    public int[] solution(String[] park, String[] routes) {
        int x = 0; // x좌표를 저장할 변수
        int y = 0; // y좌표를 저장할 변수

        // 시작 지점 좌표 찾기
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[i].length(); j++) {
                if (park[i].charAt(j) == 'S') {
                    y = i;
                    x = j;
                }
            }
        }

        for (String r : routes) {
            String[] temp = r.split(" "); // 공백을 기준으로 문자열을 나누어 저장
            char dir = temp[0].charAt(0); // 방향(E, S, W, N)
            int dis = Integer.parseInt(temp[1]); // 거리

            boolean canMove = true; // 이동 가능 여부 확인

            switch (dir) {
                case 'E': // 동쪽
                    if (x + dis < park[0].length()) {
                        for (int i = 1; i <= dis; i++) { // 1부터 시작
                            if (park[y].charAt(x + i) == 'X') {
                                canMove = false;
                                break;
                            }
                        }
                        if (canMove) x += dis;
                    }
                    break;

                case 'W': // 서쪽
                    if (x - dis >= 0) {
                        for (int i = 1; i <= dis; i++) {
                            if (park[y].charAt(x - i) == 'X') {
                                canMove = false;
                                break;
                            }
                        }
                        if (canMove) x -= dis;
                    }
                    break;

                case 'S': // 남쪽
                    if (y + dis < park.length) {
                        for (int i = 1; i <= dis; i++) {
                            if (park[y + i].charAt(x) == 'X') {
                                canMove = false;
                                break;
                            }
                        }
                        if (canMove) y += dis;
                    }
                    break;

                case 'N': // 북쪽
                    if (y - dis >= 0) {
                        for (int i = 1; i <= dis; i++) {
                            if (park[y - i].charAt(x) == 'X') {
                                canMove = false;
                                break;
                            }
                        }
                        if (canMove) y -= dis;
                    }
                    break;
            }
        }

        int[] answer = {y, x}; 
        return answer;
    }

    public static void main(String[] args) {
        Q172928 question = new Q172928();
        String[] park = {"SOO", "OXX", "OOO"};
        String[] routes = {"E 2", "S 2", "W 1"};
        int[] result = question.solution(park, routes);
        System.out.println("최종 위치: [" + result[0] + ", " + result[1] + "]");
    } 
}