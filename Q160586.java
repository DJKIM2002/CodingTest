
import java.util.HashMap;

public class Q160586 { // 프로그래머스 Lv1 대충 만든 자판
    // https://school.programmers.co.kr/learn/courses/30/lessons/160586
    /*
     * keymap : 1번 키부터 차례대로 할당된 문자들이 순서대로 담긴 문자열 배열
     * targets : 입력하려는 문자열들이 담긴 문자열 배열
     * return : 각 문자열을 작성하기 위해 눌러야 하는 최소한의 횟수가 담긴 배열
     */ 
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        HashMap<Character, Integer> hm = new HashMap<>(); // 문자를 key, 키 입력 수를 value로 하는 해시 맵
        for(String str : keymap) { // keymap에서 문자열을 꺼내옴
            for(int i = 0; i < str.length(); i++) { 
                char key = str.charAt(i); // 현재 문자를 저장
                int v = hm.getOrDefault(key, i + 1); // key가 존재하면 저장된 값을, 아니면 값에 i + 1 저장
                hm.put(key, Math.min(v, i + 1)); // key 값이 v와 i + 1 중 작은 값으로 저장
                // 같은 문자를 더 적은 횟수로 입력할 수 있게 함
            }
        }

        for(int i = 0; i < targets.length; i++) { // tartgets에서 문자열을 꺼내옴
            for(int j = 0; j < targets[i].length(); j++) {
                char key = targets[i].charAt(j); // 현재 문자를 저장
                if(hm.containsKey(key)) 
                   // key가 존재하면 hm에서 해당 key의 값을 가져와 answer에 저장
                   answer[i] += hm.get(key); 
                else {
                    // key가 존재하지 않으면 -1을 저장(만들 수 없는 문자열)
                    answer[i] = -1;
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Q160586 question = new Q160586(); 
        String[] keymap = {"ABACD", "BCEFD"};
        String[] targets = {"ABCD", "AABB"};
        System.out.print("[");
        for (int i = 0; i < targets.length; i++) {
            if (i < targets.length - 1)
                System.out.print(question.solution(keymap, targets)[i] +", ");
            else 
                System.out.print(question.solution(keymap, targets)[i] +"]");
        }
    } 
}
