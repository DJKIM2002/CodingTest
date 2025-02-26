
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Q250121 { // 프로그래머스 Lv1 데이터 분석
    // https://school.programmers.co.kr/learn/courses/30/lessons/250121
    /*
    *  data : ["코드 번호(code)", "제조일(date)", "최대 수량(maximum)", "현재 수량(remain)"]의 집합
    *  ext : 어떤 정보를 기준으로 데이터를 뽑아낼지를 의미하는 문자열
    *  val_ext : 뽑아낼 정보의 기준을 나타내는 정수
    *  sort_by : 정보를 정렬할 기준이 되는 문자열 
    *  return data에서 ext 값이 val_ext보다 작은 데이터만 뽑은 후, sort_by에 해당하는 값을 기준으로 오름차순 정렬
    */ 

    public List<int[]> solution(int[][] data, String ext, int val_ext, String sort_by) {
        String[] str = {"code", "date", "maximum", "remain"};
        int extIdx = Arrays.asList(str).indexOf(ext); // ext가 data에서 어떤 부분을 가리키는지를 저장하기 위한 변수
        int sortIdx = Arrays.asList(str).indexOf(sort_by); // sort_by가 data에서 어떤 부분을 가리키는지를 저장하기 위한 변수

        return Arrays.stream(data)  
            .filter(row -> row[extIdx] < val_ext) // val_ext보다 작은 데이터만 추출
            .sorted(Comparator.comparingInt(row -> row[sortIdx])) // sort_by 기준으로 정렬
            .collect(Collectors.toList()); // List로 변환
    }

    public static void main(String[] args) { 
        Q250121 question = new Q250121();
        int[][] data = {{1, 20300104, 100, 80},
                        {2, 20300804, 847, 37},
                        {3, 20300401, 10, 8}};
        String ext = "date";
        int val_ext = 20300501;
        String sort_by = "remain";

        List<int[]> result = question.solution(data, ext, val_ext, sort_by);
        result.forEach(row -> System.out.println(Arrays.toString(row)));
    } 
}
