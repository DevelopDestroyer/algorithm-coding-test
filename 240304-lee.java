import java.util.*;

public class Main{
    static List<Integer> graph[][];
    static boolean visit[][];

    /** 5번 등수 찾기 */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int N = Integer.parseInt(input.split(" ")[0]);
        int M = Integer.parseInt(input.split(" ")[1]);
        int X = Integer.parseInt(input.split(" ")[2]);

        graph = new List[N + 1][2];
        visit = new boolean[N + 1][2];

        for(int i=1;i<=N;i++){
            graph[i][0] = new ArrayList<>();
            graph[i][1] = new ArrayList<>();
        }

        int show[] = new int[N];
        int rank[] = new int[N];
        boolean showX = false;

        int[][] winer = new int[N][];
        int[][] loser = new int[N][];


        String[] match = new String[M];
        for (int i = 0; i < M; i++) {
            match[i] = scanner.nextLine();

            for (int j = 0; j < N; j++) {
                int win = Integer.parseInt(match[i].split(" ")[0]);
                int lose = Integer.parseInt(match[i].split(" ")[1]);

                graph[win][0].add(lose);
                graph[lose][1].add(win);

            }
        }

        System.out.println((1+bfs(X, 1)) + " " + ( N - bfs(X, 0)));
    }

    public static int bfs(int target, int flag) {
        Queue<Integer> q = new LinkedList<>();
        visit[target][flag] = true;
        int cnt = 0;
        q.offer(target);

        while (!q.isEmpty()) {
            int current = q.poll();
            for (int i = 0; i < graph[current][flag].size(); i++) {
                int next = graph[current][flag].get(i);
                if (!visit[next][flag]) {
                    visit[next][flag] = true;
                    q.offer(next);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    /** 4번 햄버거 분배 */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input1 = scanner.nextLine();
        int tableLen = Integer.parseInt(input1.split(" ")[0]);
        int K = Integer.parseInt(input1.split(" ")[1]);

        String tableInfo = scanner.nextLine();
        int[] matchingHambug = new int[tableLen];
        int[] chanceCounter = new int[tableLen]; // 값이 0보다 크면 사람, 0이면 햄버거, -1이면 사람인데 선택권 없음, -2이면 햄버거 선점됨, -3이면 햄버거 이미 먹은 사람
        int[] unhappinessList = new int[tableLen];

        // K거리만큼 가장 햄버거 선택권이 적은 사람 순서대로 정렬 -> 배열의 값은 각 사람의 위치(인덱스)를 의미
        for (int i = 0; i < tableLen; i++) {
            if (tableInfo.charAt(i) == 'P'){
                int chanceCnt = 0;
                for (int j = 0; j < K; j++) {
                    if (i + j - 1 >= 0 && tableInfo.charAt(i + j - 1) == 'H'){
                        chanceCnt++;
                    }
                    if (i + j + 1 < tableLen && tableInfo.charAt(i + j + 1) == 'H'){
                        chanceCnt++;
                    }
                }
                if (chanceCnt == 0) {
                    chanceCounter[i] = -1;
                }
                else {
                    chanceCounter[i] = chanceCnt;
                }
            }
        }

        int successCnt = 0;
        for (int i = 0; i < tableLen; i++) {
            if (chanceCounter[i] > 0) {
                for (int j = 0; j < tableLen; j++) {
                    int distance = i - j < 0 ? (i - j) * (-1) : i - j;
                    if (chanceCounter[j] == 0 && distance <= K) {
                        chanceCounter[i] = -3;
                        chanceCounter[j] = -2;
                        successCnt++;
                        break;
                    }
                }
            }
        }
//        for (int i = 0; i < tableLen; i++) {
//            int minChancePersonIDX = -100;
//            if (chanceCounter[i] > 0) {
//                for (int j = 0; j < tableLen; j++) {
//                    if (minChancePersonIDX == -100 && chanceCounter[j] > 0) {
//                        minChancePersonIDX = j;
//                    }
//                    else if (minChancePersonIDX == -100) {
//                        // 그냥 패스
//                    }
//                    else if (chanceCounter[j] > 0 && chanceCounter[minChancePersonIDX] > 0 && chanceCounter[minChancePersonIDX] < chanceCounter[j]) {// 최대의 기회를 가진 사람을 찾자.. 계획 변경
//                        minChancePersonIDX = j;
//                    }
//                }
//                for (int j = 0; j < tableLen; j++) {
//                    if (chanceCounter[j] > 0) {
//                        minChancePersonIDX = j; // 하 모르겠고 그냥 사람이면 기회를 주자...
//                        break;
//                    }
//                }
//                for (int k = 0; k < K; k++) {
//                    /**
//                    if (minChancePersonIDX + k - 1 >= 0 && tableInfo.charAt(minChancePersonIDX + k - 1) == 'H' && chanceCounter[minChancePersonIDX + k - 1] != -2){
//                        chanceCounter[minChancePersonIDX + k - 1] = -2;
//                        chanceCounter[minChancePersonIDX] = -3;
//                        successCnt++;
//                        break;
//                    }
//                    else if (minChancePersonIDX + k + 1 < tableLen && tableInfo.charAt(minChancePersonIDX + k + 1) == 'H' && chanceCounter[minChancePersonIDX + k + 1] != -2){
//                        chanceCounter[minChancePersonIDX + k + 1] = -2;
//                        chanceCounter[minChancePersonIDX] = -3;
//                        successCnt++;
//                        break;
//                    }
//                     */
//                }
//            }
//        }
        System.out.println(successCnt);

    }


    /** 3번 두 정삼각형 */
    public static void main(String[] args) {
        int[][] A = new int[10][10];
        int[][] B = new int[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++){
                A[i][j] = -1;
                B[i][j] = -1;
            }
        }

        Scanner scanner = new Scanner(System.in);



        // 삼각형의 크기
        int triangleSize = scanner.nextInt();
        scanner.nextLine();

        String[] inputStringA = new String[triangleSize];
        for (int i = 0; i < triangleSize; i++) {
            inputStringA[i] = scanner.nextLine();
            String[] parts = inputStringA[i].split(" ");
            for (int j = 0; j < parts.length; j++) {
                A[i][j] = Integer.parseInt(parts[j]);
            }
        }

        String[] inputStringB = new String[triangleSize];
        for (int i = 0; i < triangleSize; i++) {
            inputStringB[i] = scanner.nextLine();
            String[] parts = inputStringB[i].split(" ");
            for (int j = 0; j < parts.length; j++) {
                B[i][j] = Integer.parseInt(parts[j]);
            }
        }

        int min = 100;
        //그냥 비교
        min = min > diff(A, B, triangleSize) ? min = diff(A, B, triangleSize) : min;

        A = mirror(A, triangleSize);
        min = min > diff(A, B, triangleSize) ? min = diff(A, B, triangleSize) : min;

        //한번 회전 비교
        A = rotate120(A, triangleSize);
        min = min > diff(A, B, triangleSize) ? min = diff(A, B, triangleSize) : min;

        //회전 후 비교
        A = mirror(A, triangleSize);
        min = min > diff(A, B, triangleSize) ? min = diff(A, B, triangleSize) : min;

        //두번째 회전 비교
        A = rotate120(A, triangleSize);
        min = min > diff(A, B, triangleSize) ? min = diff(A, B, triangleSize) : min;

        //두번째회전 후 비교
        A = mirror(A, triangleSize);
        min = min > diff(A, B, triangleSize) ? min = diff(A, B, triangleSize) : min;

        System.out.println(min);
    }

    public static int[][] rotate120(int[][] target, int size){
        int[][] newA = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                newA[i][j] = -1;
            }
        }

        //0 x x (0, 0) => (2, 0)
        //1 0 x
        //0 0 1
        for (int i = 0; i < size; i++) { // 2, 2
            for (int j = 0; j < i + 1; j++) { // 1, 0 -> 2, 1
                newA[size - 1 - j][size - 1 - i] = target[i][j];
            }
        }

        return newA;
    }

    public static int[][] mirror(int[][] target, int size){
        for (int i = 0; i < size; i++) { // 2, 2
            for (int j = 0; j < (i + 1) / 2; j++) {
                int temp = target[i][j];
                target[i][j] = target[i][i - j];
                target[i][i - j] = temp;
            }
        }

        return target;
    }

    public static int diff(int[][] A, int[][] B, int size) {
        int diff = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (A[i][j] != B[i][j]) {
                    diff++;
                }
            }
        }
        return diff;
    }

    /** 2번 회문 */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 숫자 입력 받기
        int count = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        // 입력받은 숫자만큼의 문자열 입력 받기
        String[] origin = new String[count];
        for (int i = 0; i < count; i++) {
            origin[i] = scanner.nextLine();
        }

        for (int i = 0; i < count; i++) {
            if (palindrome(origin[i])) {
                System.out.println(0);
            }
            else if (pseudoPalindrome(origin[i])) {
                System.out.println(1);
            }
            else {
                System.out.println(2);
            }
        }
    }

    public static boolean palindrome(String origin) {
        String part1;
        String part2;

        if (origin.length() % 2 == 0) {
            part1 = origin.substring(0, origin.length() / 2);
            part2 = new StringBuilder(origin.substring(origin.length() / 2)).reverse().toString();;
        }
        else {
            part1 = origin.substring(0, origin.length() / 2);
            part2 = new StringBuilder(origin.substring(origin.length() / 2 + 1)).reverse().toString();
        }

        return part1.equals(part2);
    }

    public static boolean pseudoPalindrome(String origin) {
        for (int i = 0; i < origin.length(); i++) {
            boolean res = false;
            if (i != origin.length() - 1){
                res = palindrome(origin.substring(0, i) + origin.substring(i + 1));
            }
            else {
                res = palindrome(origin.substring(0, origin.length() - 1));
            }
            if (res){
                return res;
            }
        }
        return false;
    }
    // 재귀로 푸는 방법.... : https://kim-coffee.tistory.com/31

/**    1번 피터팬  */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        char[][] frame = new char[5][200]; //최대 가로갯수 : 15 X 5 - 15 = 60

        for (int i = 0; i < input.length(); i++) {
            char alpha = input.charAt(i);
            for (int j = 0; j < 5; j++) {
                int startIdx = i * 5 - i;
                char mark = (i + 1) % 3 == 0 ? '*' : '#';
                if (j == 0 || j == 4) {
                    frame[j][startIdx] = '.';
                    frame[j][startIdx + 1] = '.';
                    frame[j][startIdx + 2] = mark;
                    frame[j][startIdx + 3] = '.';
                    frame[j][startIdx + 4] = '.';
                }
                else if (j == 1 || j == 3) {
                    frame[j][startIdx + 0] = '.';
                    frame[j][startIdx + 1] = mark;
                    frame[j][startIdx + 2] = '.';
                    frame[j][startIdx + 3] = mark;
                    frame[j][startIdx + 4] = '.';
                }
                else { // j == 2
                    char markOverWrite = mark;
                    if (frame[j][startIdx + 0] == '*' && mark == '#'){
                        markOverWrite = '*';
                    }

                    frame[j][startIdx + 0] = markOverWrite;
                    frame[j][startIdx + 1] = '.';
                    frame[j][startIdx + 2] = alpha;
                    frame[j][startIdx + 3] = '.';
                    frame[j][startIdx + 4] = mark;
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 100; j++) {
                System.out.print(frame[i][j]);
                if (frame[i][j+1] == '\u0000') {
                    System.out.println("");
                    break;
                }

            }
            //System.out.println("");
        }

    }
}