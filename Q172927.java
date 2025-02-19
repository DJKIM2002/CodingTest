import java.util.Arrays;

public class Q172927 { // 프로그래머스 Lv2 광물 캐기
    // https://school.programmers.co.kr/learn/courses/30/lessons/172927
    /*
     * 각 곡괭이로 광물을 캘 때의 피로도를 나타내는 표
     * ------------------------------------------ 
     * |[곡괭이\광물] | [다이아몬드] | [철] | [돌] |
     * |-------------|-------------|------|------|
     * |[다이아몬드]  |     1       |  1   |  1   |
     * |-------------|-------------|------|------|
     * |[철]         |     5       |  1   |  1   |
     * |-------------|-------------|------|------|
     * |[돌]         |     25      |  5   |  1   |
     * ------------------------------------------
     * 
     * 사용할 수 있는 곡괭이 중 아무거나 하나를 선택해 광물을 캔다.
     * 한 번 사용하기 시작한 곡괭이는 사용할 수 없을 때까지 사용
     * 광물은 주어진 순서대로만 캘 수 있음
     * 광산에 있는 모든 광물을 캐거나, 더 사용할 곡괭이가 없을 때까지 광물을 캠 
     * 
     * 곡괭이를 하나 선택해서 광물 5개를 연속으로 캐고, 다음 곡괭이를 선택해서 
     * 광물 5개를 연속으로 캐는 과정을 반복
     * 각 곡괭이는 종류에 상관없이 광물 5개를 캔 후에는 더 이상 사용 불가
     * 
     * picks : 가지고 있는 곡괭이의 개수 [dia, iron, stone] 순
     * minerals : 곡괭이로 캐는 광물들의 순서
     * 
     * return : minerals 배열 안의 광물을 캐는데 필요한 최소한의 피로도
     */

    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        // minerals 배열을 요소 5개씩 쪼개서, 2차원 배열로로 저장
        // 모든 곡괭이를 써서 모든 광물을 캘 수 없는 경우
        // 곡괭이의 총 개수로 길이를 저장
        int cnt = Math.min(minerals.length / 5 + 1, picks[0] + picks[1] + picks[2]);
        int[][] part_point = new int[cnt][3]; // 앞에서부터 광물을 5개씩 묶었을 때, 곡갱이별로 광물을 캐는 데 필요한 피로도 저장
        int dp = 0, ip = 0, sp = 0; // 각 곡괭이로 광물을 캐는데 소모되는 피로도

        for(int i = 0; i < minerals.length; i += 5) {
            if (i / 5 == cnt) { // 사용 가능한 곡괭이 개수 만큼만 반복 
                break;
            } 
            
            for(int j = i; j < i + 5; j++) {
                String m = minerals[j]; // 어떤 광물인지 저장
                // 각 광물 별로 소모되는 피로도를 누적
                if (m.equals("diamond")) {
                    dp += 1;
                    ip += 5;
                    sp += 25;
                } else if (m.equals("iron")) {
                    dp += 1;
                    ip += 1;
                    sp += 5;
                } else {
                    dp += 1;
                    ip += 1;
                    sp += 1;
                }

                if (j == minerals.length - 1) break; // 더 이상 못 캐면 종료
            }
            part_point[i / 5][0] = dp;
            part_point[i / 5][1] = ip;
            part_point[i / 5][2] = sp;

            dp = ip = sp = 0; // 다음 라인의 피로도를 계산하기 위해 초기화
        }

        // 돌로 캤을 때 피로도가 가장 높은 순으로 내림차순 정렬
        Arrays.sort(part_point, (o1, o2) -> (o2[2] - o1[2])); 

        // 다이아 -> 철 -> 돌 곡괭이 순서로 사용
        for (int i = 0; i < cnt; i++) {
            if (picks[0] != 0) {
                answer += part_point[i][0]; // 다이아 곡괭이로 캤을 때 피로도를 누적
                picks[0]--; // 곡괭이 수 차감
            } else if (picks[1] != 0) { // 다이아 곡괭이가 0개이고, 철 곡괭이가 있을 때
                answer += part_point[i][1]; // 철 곡괭이로 캤을 때 피로도를 누적
                picks[1]--; // 곡괭이 수 차감
            } else if (picks[2] != 0) { // 다이아, 철 곡괭이가 모두 0개이면
                answer += part_point[i][2]; // 돌 곡괭이로 캤을 때 피로도를 누적
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Q172927 question = new Q172927();
        int[] picks = {1, 3, 2};
        String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
        System.out.println(question.solution(picks, minerals));
    } 
}