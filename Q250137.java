
import java.util.HashMap;

public class Q250137 { // 프로그래머스 Lv1 붕대감기
    // https://school.programmers.co.kr/learn/courses/30/lessons/250137
    /*
     * 붕대 감기 : t초 동안 붕대를 감으면서 1초마다 x만큼의 체력을 회복한다
     * t초 연속으로 붕대를 감는 데 성공한다면 y만큼의 체력을 추가로 회복한다.
     * 최대 체력 이상으로 체력이 커질 수는 없다.
     * 기술을 쓰는 도중 몬스터에게 공격을 당하면 기술이 취소된다.
     * 공격을 당하는 순간에는 회복을 할 수 없다.
     * 기술이 취소당하거나 기술이 끝나면 그 즉시 붕대 감기를 다시 사용한다.
     * 현재 체력이 0 이하가 되면 캐릭터가 죽으며 더 이상 체력 회복이 불가능하다.
     * 
     * bandage = [t(시전 시간), x(초당 회복량), y(추가 회복량)]
     * health = 최대 체력
     * attacks[] = [[몬스터 공격 시간, 몬스터가 주는 피해량], ...]
     * 단, attacks 배열의 공격 시간은 모두 다르며, 오름차순 정렬된 상태
     */
    public int solution(int[] bandage, int health, int[][] attacks) {
        int endTime = attacks[attacks.length - 1][0]; // 몬스터가 마지막으로 공격하는 시간
        HashMap<Integer, Integer> attacksMap = new HashMap<>(); // 검색 시간 단축을 위해 HashMap으로 재정의
        for (int i = 0; i < attacks.length; i++)  
            attacksMap.put(attacks[i][0], attacks[i][1]);
        int maxHealth = health; // 최대 체력
        int currentHealth = health; // 현재 체력
        int continuity = 0; // 연속 성공 횟수 
        int answer = 0;

        for (int i = 1; i <= endTime; i++) { // 1초부터 몬스터 마지막 공격까지
            if (attacksMap.containsKey(i)) { // HashMap에서 이번 시간에 해당하는 key가 있는지 확인
                currentHealth -= attacksMap.get(i); // 이번 시간의 데미지 만큼 체력 감소
                continuity = 0; // 연속 성공 초기화
                if (currentHealth <= 0) // 죽으면 -1 리턴
                    return -1;
                    
            } else { // 이번 초에 공격이 오지 않으면(해당하는 키 값이 없으면)
                continuity++; // 연속 성공 횟수 증가
                currentHealth += bandage[1]; // 초당 회복량만큼 체력 회복
                
                if (continuity >= bandage[0]) { // 만약 연속 성공이 시전 시간에 도달하면
                    currentHealth += bandage[2]; // 체력 추가 회복
                    continuity = 0; // 연속 성공 초기화
                }

                if (currentHealth > maxHealth) // 최대 체력을 넘지 못함
                    currentHealth = maxHealth; 
            }
        }
        answer = currentHealth; // 죽지 않았다면, 현재 체력을 리턴
        return answer; 
    }

    public static void main(String[] args) { 
        Q250137 question = new Q250137();
        int[] bandage = {3, 2, 7};
        int health = 20;
        int[][] attacks = {{1, 15}, {5, 16}, {8, 6}};
        System.out.println(question.solution(bandage, health, attacks));
    } 
}