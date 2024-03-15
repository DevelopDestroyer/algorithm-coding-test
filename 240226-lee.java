/**
 * 1번
 */
import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.nextLine();
        int[] arr = new int[26]; // 알파벳의 종류 26개를 받는 배열을 준비한다

        for (int i = 0; i < 26; i++) {
            arr[i] = -1; // 미리 -1로 초기화한다
        }

        for (int i = 0; i < S.length(); i++) {
            char target = S.charAt(i);
            int ascii = target >= 97 ? target - 32 : target; // 소문자일 경우 대문자 숫자로 변경
            if (arr[ascii - 65] == -1 && ascii >= 65 && ascii <= 90) { // 배열에 -1이고, 대문자의 범위에 해당될경우에만 값을 넣는다
                arr[ascii - 65] = i;
            }
        }

        // 출력로직
        for (int i = 0; i < 26; i++) {
            System.out.print(arr[i] + " ");
        }

   }
}

/**
 * 2번
 */
import java.util.*;

public class Main{
    public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
 
      int N = in.nextInt();
      in.close();
        
      int cnt = 0;
      int copy = N;
        
      while (true) {
         N = ((N % 10) * 10) + (((N / 10) + (N % 10)) % 10);
         cnt++;
 
         if (copy == N) {
            break;
         }
      }
      System.out.println(cnt);
        /*
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int count = 0;
        int newNum = -1;
        
        do {
            newNum = ((N % 10) * 10) + (((N / 10) + (N % 10)) % 10);// (N % 10) * 10 + (N / 10 + N % 10) % 10;
            count++;
        }
        while(N != newNum);

        
        System.out.print(count);
*/
   }
}


/**
 * 3번
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static HashMap<String, Integer> roma;
   
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String inputLine1[] = in.readLine().split("");
        String inputLine2[] = in.readLine().split("");
        roma = new HashMap<String, Integer>();
 
        roma.put("I", 1);
        roma.put("IV", 4);
        roma.put("V", 5);
        roma.put("IX", 9);
        roma.put("X", 10);
        roma.put("XL", 40);
        roma.put("L", 50);
        roma.put("XC", 90);
        roma.put("C", 100);
        roma.put("CD", 400);
        roma.put("D", 500);
        roma.put("CM", 900);
        roma.put("M", 1000);
       
        int q1 = toArab(inputLine1) + toArab(inputLine2);
        String q2 = toRoma(q1);
        System.out.println(q1);
        System.out.println(q2);
       
    }

    private static int toArab(String[] romaStr) {
        int result = 0;
        boolean isTwoChar = false;
        for (int i = 0; i < romaStr.length; i++) {
            char c = romaStr[i].charAt(0); // char로 변환하기
            if ((c == 'I' || c == 'X' || c == 'C') && i < romaStr.length - 1) { // I, X, C일 경우, 2개 문자로 구성된 숫자일 수 있다 이 가능성에 대한 체크를한다
                String s = romaStr[i] + romaStr[i + 1];
                if (roma.containsKey(s)) { // 2글자 조합의 숫자일 경우, 실제 존하는 케이스인지 검증 후, 맞다면 연산해준다
                    result += roma.get(s);
                    i++; // 2글자이므로 i++를 한번 더 진행해주어야함
                    isTwoChar = true;
                }
            }
            if (isTwoChar == false){
                result += roma.get(romaStr[i]);
            }
            else {
                isTwoChar = false;
            }
        }
        return result;
    }

    public static String toRoma(int num) {
        // 트리맵 생성
        TreeMap<Integer, String> romanMap = new TreeMap<>();
        romanMap.put(1, "I");
        romanMap.put(4, "IV");
        romanMap.put(5, "V");
        romanMap.put(9, "IX");
        romanMap.put(10, "X");
        romanMap.put(40, "XL");
        romanMap.put(50, "L");
        romanMap.put(90, "XC");
        romanMap.put(100, "C");
        romanMap.put(400, "CD");
        romanMap.put(500, "D");
        romanMap.put(900, "CM");
        romanMap.put(1000, "M");
        
        // 로마 숫자가 담길 변수
        StringBuilder roman = new StringBuilder();
        
        while (num > 0) {
            int key = romanMap.floorKey(num); // 현재 숫자보다 작거나 같은 수 중 가장 큰 아라비아 수 가져오기
            String value = romanMap.get(key); // 위 숫자에 매치되는 로마 숫자를 가져오기
            roman.append(value); // 로마숫자를 스트링에 추가
            num -= key; // 추가되었으니 이 값은 빼고 while을 돈다
        }
        
        return roman.toString();
    }

    private static String toRoma(int num) {
        String result = "";
        List<Map.Entry<String, Integer>> list = new ArrayList<>(roma.entrySet());
        list.sort(Map.Entry.comparingByValue(Collections.reverseOrder())); // 
        while(num != 0) {
            int pivotNum = 0; //이 변수는 아래 반복문에서 연산중인 특정 로마숫자 단위문자가 몇개가 연속적으로 들어가야하는지를 의미한다
            for (Map.Entry<String, Integer> entry : list) {
                pivotNum = num / entry.getValue();
                if (pivotNum != 0) {
                    for (int i = 0; i < pivotNum; i++) {
                        result += entry.getKey();
                    }
                    num = num % entry.getValue();
                }
            }
        }
        return result;
    }


}