
class Q389478 { // 프로그래머스 Lv1 택배 상자 꺼내기
    // https://school.programmers.co.kr/learn/courses/30/lessons/389478
    /*
     * n : 쌓아야 하는 상자 수
     * w : 한 줄에 쌓아야 하는 상자 수
     * num : 찾아야 하는 상자의 번호
     * 
     * return : 꺼내야 하는 상자의 개수
     */
    public int solution(int n, int w, int num) {
        int answer = 0;
        int line = n/w + 1; // 쌓이는 상자 줄 수 
        int[][] box = new int[line][w]; // 상자가 n/w + 1 줄로, 1줄 당 w개씩 쌓여짐.
        // 상자에 번호 매기기
        int idx = 1; // 상자 번호
        int r = 0; // 찾아야 하는 상자가 위치한 행을 저장하기 위한 변수
        int c = 0; // 같은 열에 있는 상자를 찾기 위한 변수
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < w; j++) {
                // 짝수 줄은 왼쪽에서 오른쪽으로 상자를 쌓고,
                // 홀수 줄은 오른쪽에서 왼쪽으로 상자를 쌓는다.
                if (idx > n) {
                    break;
                } else {
                    if (i % 2 != 0) { // 홀수 줄이라면 
                        box[i][w - j - 1] = idx;
                    } else { // 짝수 줄이라면 
                        box[i][j] = idx; 
                    }
                    idx++;
                }
            }
        }

        for (int i = 0; i < line; i++) {
            for (int j = 0; j < w; j++) {
                if (box[i][j] == num) {
                    r = i;
                    c = j;
                }
            }
        } 

        // 찾아야 하는 상자와 같은 열에 있는 상자의 개수를 리턴
        for (int i = 0; i < line; i++) 
            for (int j = 0; j < w; j++) 
                if (box[i][j] != 0 &&
                    j == c &&
                    i >= r) 
                    answer++; 
        return answer;
    }

    public static void main(String[] args) { 
        Q389478 question = new Q389478();
        int n = 13;
        int w = 3;
        int num = 6;
        System.out.println(question.solution(n, w, num));
    } 
}