public class Q340213 { // 프로그래머스 Lv1 동영상 재생기
    // https://school.programmers.co.kr/learn/courses/30/lessons/340213
    /*
     * video_len : 동영상의 길이
     * pos : 기능이 수행되기 직전의 재생 위치
     * op_start : 오프닝 시작 시각
     * op_end : 오프닝 끝 시각
     * commands : 사용자의 입력(next, prev 두 가지)
     * return 사용자의 입력이 모두 끝난 후 동영상의 위치(String 타입, "mm:ss" 형태)
     */

    // 시간 문자열을 초로 변환
    private int timeToSeconds(String time) {
        String[] parts = time.split(":"); // :을 기준으로 분리(분, 초)
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]); // 정수(초)로 변환
    }
    
    // 초를 시간 문자열로 변환
    private String secondsToTime(int seconds) {
        int minutes = seconds / 60; // 분
        int secs = seconds % 60; // 초
        return String.format("%02d:%02d", minutes, secs); // "mm:ss" 문자열로 변환
    }

    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        // 시간 계산을 위해 매개변수로 받아온 값을 전부 정수(초)로 변환 
        int videoLen = timeToSeconds(video_len);
        int currentPos = timeToSeconds(pos);
        int opStart = timeToSeconds(op_start);
        int opEnd = timeToSeconds(op_end);
        
        for (String command : commands) {
            if (command.equals("next")) { // next 명령을 받으면
                // 오프닝 구간이면 스킵
                if (currentPos >= opStart && currentPos <= opEnd) {
                    currentPos = opEnd;
                }
                currentPos += 10; // 현재 위치보다 10초 다음으로
                // 10초 후에 오프닝 구간에 진입하면 오프닝 스킵
                if (currentPos >= opStart && currentPos <= opEnd) {
                    currentPos = opEnd;
                }
                // 비디오 길이를 초과하면 마지막으로
                if (currentPos >= videoLen) {
                    currentPos = videoLen;
                }
                
            } else { // prev 명령을 받으면
                // 오프닝 구간이면 스킵
                if (currentPos >= opStart && currentPos <= opEnd) {
                    currentPos = opEnd;
                }
                currentPos -= 10; // 현재 위치보다 10초 이전으로
                // 10초 전이 오프닝 구간이면 오프닝 스킵
                if (currentPos >= opStart && currentPos <= opEnd) {
                    currentPos = opEnd;
                }
                // 0보다 작아지면 영상의 처음으로
                if (currentPos < 0) {
                    currentPos = 0;
                }
            }
        }
        // 정수(초)를 문자열(시각)로 변환하여 리턴 
        return secondsToTime(currentPos);
    }

    public static void main(String[] args) { 
        Q340213 question = new Q340213();
        String video_len = "10:55";
        String pos = "00:05";
        String op_start = "00:15";
        String op_end = "06:55";
        String[] commands = {"prev", "next", "next"};
        System.out.println(question.solution(video_len, pos, op_start, op_end, commands));
    } 
}
