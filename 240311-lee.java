//1. https://www.acmicpc.net/problem/1406
//2. https://www.acmicpc.net/problem/1918
//3. https://www.acmicpc.net/problem/1725
/** 3번 히스토그램*/
import java.util.*;

public class Main{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int width = scanner.nextInt();
        int[] height = new int[width];
        int maxHeight = 0;
        for (int i = 0; i < width; i++) {
            height[i] = scanner.nextInt();
            maxHeight = height[i] > maxHeight ? height[i] : maxHeight;
        }

        int[][] arr = new int[maxHeight][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height[i]; j++) {
                arr[j][i] = 1;
            }
        }


        int tempRactSize = 0;
        for (int i = 0; i < width; i ++) {
            int maxRactHeight = height[i];
            int maxRactWidth = 0;

            int k = height[i] - 1;
            while (k >= 0) {
                boolean isBreak = false;
                for (int j = i; j < width; j++) {
                    if (arr[k][j] == 0) {
                        // tempRactSize = tempRactSize < (j - i + 1) * (k + 1) ? (j - i + 1) * (k + 1) : tempRactSize;
                        k--;
                        isBreak = true;
                        break;
                    }
                    else {
                        tempRactSize = tempRactSize < (j - i + 1) * (k + 1) ? (j - i + 1) * (k + 1) : tempRactSize;
                    }
                }
                if (!isBreak) {
                    tempRactSize = tempRactSize < (width - i) * (k + 1) ? (width - i) * (k + 1) : tempRactSize;
                    k--;
                }
            }
            //arr[0][i] = tempRactSize;

        }


        System.out.println(tempRactSize);
    }

}

/**    2번 후위 표기식   */
//import java.util.*;
//
//public class Main{
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String origin = scanner.nextLine();
//        Stack<String> stack = new Stack<>();
//        String result = "";
//
//        for (int i = 0; i < origin.length(); i++) { // +, -
//            // 1. 피연산자는 결과물에, 연산자는 스택에 넣는다.
//            // 2. 이때, 연산자의 우선위를 비교하여 스택의 연산자가 우선순위가 더 높으면 스택에 있는 것을 결과물에 넣어야 한다. 이 때, 스택의 연산자 우선순위가 같아질때까지 pop을 해야한다
//            // 3. (는 스택에 무조건 넣는다. 이때 닫는 괄호가 나온다면, 여는 괄호가 나올때까지 연산자들을 결과물에 넣는다. 괄호들은 결과물에서 제거한다
//            if (origin.charAt(i) == '*' || origin.charAt(i) == '/' || origin.charAt(i) == '+' || origin.charAt(i) == '-') {
//                if (!stack.isEmpty()) {
//                    boolean isBreak = false;
//                    int popCnt = 0;
//                    while (!stack.isEmpty()) {
//                        if (needPop(String.valueOf(origin.charAt(i)), stack.peek())) {
//                            result = result + stack.pop();
//                            popCnt++;
//                        }
//                        else if (popCnt > 0) {
//                            result = result + stack.pop();
//                            stack.push(String.valueOf(origin.charAt(i)));
//                            isBreak = true;
//                            break;
//                        }
//                        else {
//                            //result = result + stack.pop();
//                            stack.push(String.valueOf(origin.charAt(i)));
//                            isBreak = true;
//                            break;
//                        }
//                    }
//                    if (!isBreak) {
//                        stack.push(String.valueOf(origin.charAt(i)));
//                    }
//                }
//                else {
//                    stack.push(String.valueOf(origin.charAt(i)));
//                }
//            }
//            else if (origin.charAt(i) == '(') {
//                stack.push(String.valueOf(origin.charAt(i)));
//            }
//            else if (origin.charAt(i) == ')') {
//                while (!stack.isEmpty()) {
//                    if (stack.peek().equals("(")) {
//                        stack.pop();
//                        break;
//                    }
//                    else {
//                        result = result + stack.pop();
//                    }
//                }
//            }
//            else {
//                result = result + origin.charAt(i);
//            }
//        }
//        while (!stack.isEmpty()) {
//            result = result + stack.pop();
//        }
//        System.out.println(result);
//    }
//
//    public static boolean needPop(String newInput, String peek) {
//        if ((newInput.equals("-") || newInput.equals("+")) && (peek.equals("*") || peek.equals("/")) ) {
//            return true;
//        }
//        else if ((newInput.equals("-") || newInput.equals("+")) && (peek.equals("-") || peek.equals("+")) ) {
//            return true;
//        }
//        else if ((newInput.equals("*") || newInput.equals("/")) && (peek.equals("*") || peek.equals("/")) ) {
//            return true;
//        }
//
//        return false;
//    }
//}

/**    1번 에디터   */
//import java.util.*;
//
//public class Main{
//    static char[] result = new char[600000];
//    static int leng = 0;
//    static Double cursor = 0.5;
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String origin = scanner.nextLine();
//        leng = origin.length();
//        // result = origin.toCharArray();
//        // 문자열을 배열에 작은 인덱스부터 넣기
//        for (int i = 0; i < origin.length() && i < result.length; i++) {
//            result[i] = origin.charAt(i);
//        }
//        int M = scanner.nextInt();
//        String[] command = new String[M];
//        scanner.nextLine();
//        cursor = origin.length() - 0.5; // 오리지널문자열 길이가 3이면 -0.5 <= M < 2.5
//        for (int i = 0; i < M; i++) {
//            command[i] = scanner.nextLine();
//            if (command[i].charAt(0) == 'P') {
//                add((int)(cursor + 0.5), command[i].charAt(2));
//            }
//            else if (command[i].charAt(0) == 'B') {
//                sub((int)(cursor - 0.5));
//            }
//            else if (command[i].charAt(0) == 'L' && cursor != -0.5) {
//                cursor -= 1;
//            }
//            else if (command[i].charAt(0) == 'D' && cursor != origin.length() - 0.5) {
//                cursor += 1;
//            }
//        }
//
//        for (int i = 0; i < leng; i++) {
//            System.out.print(result[i]);
//        }
//
//    }
//
//    public static void add(int startIndex, char c) {
//        if (startIndex < 0 || startIndex > leng) {
//            // 아무 처리 안함
//        }
//        else{
//            leng++;
//            for (int i = leng - 1; i > startIndex; i--) {
//                result[i] = result[i - 1];
//            }
//            result[startIndex] = c;
//            cursor++;
//        }
//    }
//
//    public static void sub(int startIndex) {
//        if (startIndex < 0 || startIndex > leng) {
//            // 아무 처리 안함
//        }
//        else {
//            leng--;
//            for (int i = startIndex; i < leng; i++) {
//                result[i] = result[i + 1];
//            }
//            result[leng + 1] = '\u0000';
//            cursor--;
//        }
//    }
//}