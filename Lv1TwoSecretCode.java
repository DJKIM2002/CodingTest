public class Lv1TwoSecretCode { // 프로그래머스 Lv1 둘만의 코드  
    // https://school.programmers.co.kr/learn/courses/30/lessons/155652
    /*
        두 문자열 s와 skip, 자연수 index가 주어질 때, 
        다음 규칙에 따라 문자열을 만듦.
        1. 문자열 s의 각 알파벳을 index만큼 뒤의 알파벳으로 바꿔줌.
        2. index만큼의 뒤의 알파벳이 z를 넘어갈 경우 다시 a로 돌아감.
        3. skip에 있는 알파벳은 제외하고 건너뛰고, index만큼 뒤의 알파벳을 찾아야 함.
        
        return 위 규칙대로 s를 변환한 결과(문자열)

        ex) s = "aukks", skip = "wbqd", index = 5
        a에서 5만큼 뒤의 알파벳은 f지만
        a뒤의 알파벳 [b, c, d, e, f] 에서 b, d는 skip에 있는 알파벳이므로
        b와 d를 제외하고 a뒤의 5번째 알파벳을 다시 찾으면
        [c, e, f, g, h], 즉 h가 된다.
        이 방법대로 s를 변환하면, "happy"가 된다.
        
        * 제한사항 
        - 5 <= s의 길이 <= 50
        - 1 <= skip의 길이 <= 10
        - s와 skip은 알파벳 소문자로만 이루어진 문자열
        - skip에 포함되는 알파벳은 s에 포함되지 않음
        - 1 <= index <= 20 

        * 알고리즘 
            1. s의 알파벳에서 index만큼 뒤의 알파벳을 찾아 배열에 저장
            2. skip에 포함된 알파벳은 배열에 저장하지 않고 다음 알파벳으로 넘어감
            3. s의 각각의 알파벳을 해당 배열의 제일 마지막 요소로 변환
            4. 위 과정을 s 길이만큼 반복한 결과를 return 
     */ 
     
    public String solution(String s, String skip, int index) {
        // 성공한 코드
        String answer = "";
        char[] sArr = s.toCharArray(); // s를 알파벳 하나씩 쪼개어 배열로 저장 
        for (int i = 0; i < sArr.length; i++) { // s의 길이만큼 반복
            int count = 0;
            while (count < index) { // index만큼 뒤의 알파벳을 찾음
                sArr[i] += 1;
                if (sArr[i] > 'z') { // z를 넘어가면
                    sArr[i] = 'a'; // a로 돌아감
                }
                if (skip.indexOf(sArr[i]) == -1) { // skip에 포함되지 않은 알파벳이면 횟수 증가
                    count++;
                }
            }
            answer += sArr[i]; // 문자열로 변환 
        }
        return answer;

        // 이전 코드(실패패)
        // String answer = "";
        // char[] sArr = s.toCharArray(); // s를 알파벳 하나씩 쪼개어 배열로 저장 
        // for (int i = 0; i < sArr.length; i++) { // s의 길이만큼 반복
        //     for (int j = 0; j < index; j++) { // index만큼 뒤의 알파벳을 찾음
        //         sArr[i] += 1;
        //         if (sArr[i] > 'z') { // z를 넘어가면
        //             sArr[i] = 'a'; // a로 돌아감
        //         }
        //         if (skip.indexOf(sArr[i]) != -1) { // skip에 포함된 알파벳이면 다음 알파벳으로 넘어감
        //             sArr[i] += 1;
        //         }
        //     }
        //     answer += sArr[i]; // 문자열로 변환
        // }
        // return answer;
    }

    public static void main(String[] args) {
        Lv1TwoSecretCode question = new Lv1TwoSecretCode();
        String s = "aukks";
        String skip = "bcdef";
        int index = 5;
        System.out.println(question.solution(s, skip, index));
    }    
}
