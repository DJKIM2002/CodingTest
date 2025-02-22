
import java.util.Arrays;

public class Q258712 { // 프로그래머스 Lv1 가장 많이 받은 선물
    // https://school.programmers.co.kr/learn/courses/30/lessons/258712
    /*
     * friends : 친구들의 이름을 담은 1차원 배열
     * gifts [A B] : 이번 달까지 친구들이 주고받은 선물 기록을 담은 1차원 배열
     * A : 선물을 주는 친구
     * B : 선물을 받은 친구
     * 
     * 두 사람이 선물을 주고 받은 기록이 있다면, 이번 달까지 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물을 받음.
     * ex) A가 B에게 5번 선물을 줬고, B가 A에게 3번 선물을 줬다면, 다음 달에는 B가 A에게 선물을 줘야 함.
     * 
     * 두 사람이 선물을 주고 받은 기록이 하나도 없거나 주고받은 수가 같은 경우, 선물 지수가 더 큰 사람이 선물 지수가 작은 사람에게 선물을 하나 받음.
     * 만약 두 사람의 선물 지수도 같다면, 다음 달에는 서로 선물을 주고 받지 않는다.
     * 선물 지수 : 이번 달까지 자신이 친구들에게 준 선물의 수 - 내가 받은 선물의 수
     * 
     * return 다음 달에 가장 많은 선물을 받는 친구가 받을 선물의 수  
     */
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        int[][] gpd = new int[n][2]; // {준 선물의 수, 받은 선물의 수}
        int[] gp = new int[n];       // 선물 지수
        int[][] giftCnt = new int[n][n]; // a가 b에게 선물을 준 횟수
        int[] getGift = new int[n];  // 다음 달에 각 친구가 받을 선물 수

        // 선물 기록 집계
        for (String gift : gifts) {
            String[] parts = gift.split(" ");
            String a = parts[0];
            String b = parts[1];
            int idxA = Arrays.asList(friends).indexOf(a);
            int idxB = Arrays.asList(friends).indexOf(b);
            gpd[idxA][0]++; // a가 준 선물
            gpd[idxB][1]++; // b가 받은 선물
            giftCnt[idxA][idxB]++;
        }

        // 선물 지수 계산
        for (int i = 0; i < n; i++) {
            gp[i] = gpd[i][0] - gpd[i][1];
        }

        // 모든 친구 쌍에 대해 한 번씩만 비교
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (giftCnt[i][j] > 0 || giftCnt[j][i] > 0) { // 선물 기록이 있다면
                    if (giftCnt[i][j] > giftCnt[j][i]) {
                        getGift[i]++; // i가 더 많이 선물했으므로, i가 다음 달에 선물을 받음
                    } else if (giftCnt[i][j] < giftCnt[j][i]) {
                        getGift[j]++;
                    } else { // 선물 횟수가 같으면 선물 지수로 비교
                        if (gp[i] > gp[j]) {
                            getGift[i]++;
                        } else if (gp[i] < gp[j]) {
                            getGift[j]++;
                        }
                    }
                } else { // 아무런 선물 기록이 없으면 선물 지수로 비교
                    if (gp[i] > gp[j]) {
                        getGift[i]++;
                    } else if (gp[i] < gp[j]) {
                        getGift[j]++;
                    }
                }
            }
        }

        int answer = 0;
        for (int cnt : getGift) {
            answer = Math.max(answer, cnt);
        }
        return answer;
    }

    public static void main(String[] args) { 
        Q258712 question = new Q258712();
        String[] friends = {"muzi", "ryan", "frodo", "neo"};
        String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};
        System.out.println(question.solution(friends, gifts));
    } 
}