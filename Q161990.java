
import java.util.Arrays;

public class Q161990 { // 프로그래머스 Lv1 바탕화면 정리
    // https://school.programmers.co.kr/learn/courses/30/lessons/161990
    /*
     * wallpaper : 컴퓨터 바탕화면의 상태를 나타낸 문자열 배열
     * 바탕화면의 가장 왼쪽 위를 (0, 0)으로 시작해 (세로 좌표, 가로 좌표)로 표현
     * 빈칸은 ".", 파일이 있는 칸은 "#"의 값
     * return 바탕화면의 파일들을 한 번에 삭제하기 위해 최소한의 이동거리를 갖는 드래그의 시작점과 끝점을 담은 정수 배열
     */
    public int[] solution(String[] wallpaper) {
        int minRow = wallpaper.length; // x 최소 값
        int minCol = wallpaper[0].length(); // y 최소 값
        int maxRow = 0; // x 최대 값
        int maxCol = 0; // y 최대 값
        for (int i = 0; i < wallpaper.length; i++) { // 바탕화면의 높이만큼 반복
            for (int j = 0; j < wallpaper[i].length(); j++) { // 바탕화면의 너비만큼 반복 
                if (wallpaper[i].charAt(j) == '#') {
                    minRow = Math.min(minRow, i); // 가장 먼저 찾은 #의 좌표 값 저장
                    minCol = Math.min(minCol, j);
                    maxRow = Math.max(maxRow, i + 1); // 이후 드래그 범위를 늘려나감 
                    maxCol = Math.max(maxCol, j + 1); // 마지막 #의 좌표 값 저장
                }
            }
        }

        return new int[]{minRow, minCol, maxRow, maxCol}; // 드래그 시작 좌표 값, 드래그 종료 좌표 값을 담은 int타입 1차원 배열을 return
    }

    public static void main(String[] args) {
        Q161990 question = new Q161990();
        String[] wallpaper = {".#...", 
                              "..#..", 
                              "...#."};
        System.out.println(Arrays.toString(question.solution(wallpaper)));
    } 
}
