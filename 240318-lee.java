//        덩치
//          https://www.acmicpc.net/problem/7568
//        체스판 다시 칠하기
//          https://www.acmicpc.net/problem/1018
//        스타트와 링크
//          https://www.acmicpc.net/problem/14889



 import java.util.ArrayList;
 import java.util.List;

 public class CombinationExample {

 public static void main(String[] args) {
 List<Integer> elements = new ArrayList<>();
 for (int i = 1; i <= 6; i++) {
 elements.add(i);
 }

 List<List<Integer>> combinations = new ArrayList<>();
 generateCombinations(elements, 3, 0, new ArrayList<>(), combinations);

 // 조합 출력
 for (List<Integer> combination : combinations) {
 System.out.println(combination);
 }
 }

 public static void generateCombinations(List<Integer> elements, int k, int start, List<Integer> combination, List<List<Integer>> combinations) {
 if (k == 0) {
 combinations.add(new ArrayList<>(combination));
 return;
 }

 for (int i = start; i <= elements.size() - k; i++) {
 combination.add(elements.get(i));
 generateCombinations(elements, k - 1, i + 1, combination, combinations);
 combination.remove(combination.size() - 1);
 }
 }
 }


/** 2번 체스판 다시 칠하기 */
import java.util.*;

public class Main{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input1 = scanner.nextLine();
        char[][] chess = new char[Integer.parseInt(input1.split(" ")[0])][Integer.parseInt(input1.split(" ")[1])];
        for (int i = 0; i < Integer.parseInt(input1.split(" ")[0]); i++) {
            String input2 = scanner.nextLine();
            for (int j = 0; j < Integer.parseInt(input1.split(" ")[1]); j++) {
                chess[i][j] = input2.charAt(j);
            }
        }

        int w = 0;
        int h = 0;
        int min = 100000000;
        int whilecnt = 0;
        do {
            do {
                int res = partCnt(chess, h, w);
                whilecnt++;
                min = res < min ? res : min;
                w++;
            } while (w + 8 <= Integer.parseInt(input1.split(" ")[1]));
            h++;
            w = 0;
        } while (h + 8 <= Integer.parseInt(input1.split(" ")[0]));
        System.out.print(min);
        //System.out.println(" . . " + whilecnt);

    }

    public static int partCnt(char[][] arr, int x, int y) {
        int caseA = 0;
        int caseB = 0;
        char[] a = {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'};
        char[] b = {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'};

        for (int i = x; i < x + 8; i++) {
            char[] c = i % 2 == 0 ? a : b;
            char[] d = i % 2 == 0 ? b : a;

            if (arr[i][y] != c[0]) {
                caseA++;
            }

            if (arr[i][y+1] != c[1]) {
                caseA++;
            }

            if (arr[i][y+2] != c[2]) {
                caseA++;
            }

            if (arr[i][y+3] != c[3]) {
                caseA++;
            }

            if (arr[i][y+4] != c[4]) {
                caseA++;
            }

            if (arr[i][y+5] != c[5]) {
                caseA++;
            }

            if (arr[i][y+6] != c[6]) {
                caseA++;
            }

            if (arr[i][y+7] != c[7]) {
                caseA++;
            }

            if (arr[i][y] != d[0]) {
                caseB++;
            }

            if (arr[i][y+1] != d[1]) {
                caseB++;
            }

            if (arr[i][y+2] != d[2]) {
                caseB++;
            }

            if (arr[i][y+3] != d[3]) {
                caseB++;
            }

            if (arr[i][y+4] != d[4]) {
                caseB++;
            }

            if (arr[i][y+5] != d[5]) {
                caseB++;
            }

            if (arr[i][y+6] != d[6]) {
                caseB++;
            }

            if (arr[i][y+7] != d[7]) {
                caseB++;
            }
        }

        return caseA > caseB ? caseB : caseA;
    }


}

/** 1번 덩치 */
//import java.util.*;
//
//public class Main{
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int len = scanner.nextInt();
//        int[] x = new int[len];
//        int[] y = new int[len];
//        int[] rank = new int[len];
//        HashMap<Integer, Integer> hashMap = new HashMap<>();
//        int[] z = new int[len];
//
//        scanner.nextLine();
//
//        for (int i = 0; i < len; i++) {
//            String[] input = scanner.nextLine().split(" ");
//            x[i] = Integer.parseInt(input[0]);
//            y[i] = Integer.parseInt(input[1]);
////            for (int j = 0; j <= i; j++) {
////                if  (i == 0) {
////                    rank[0] = 0;
////                    break;
////                }
////                if (x[i] > x[rank[j]] && y[i] > y[rank[j]]) {
////                    for (int k = i; k > j; k--) {
////                        rank[k] = rank[k-1];
////                    }
////                    rank[j] = i;
////                    break;
////                }
////                if (j == i) {
////                    rank[j] = i;
////                }
////            }
//        }
////
////        int rankCnt = 1;
////        int stack = 0;
////        for (int i = 0; i < rank.length - 1; i++) {
////            if (x[rank[i]] > x[rank[i+1]] && y[rank[i]] > y[rank[i+1]]) {
////                hashMap.put(rank[i], rankCnt);
////                rankCnt = rankCnt + stack;
////                rankCnt++;
////                stack = 0;
////            }
////            else {
////                stack++;
////                hashMap.put(rank[i], rankCnt);
////            }
////        }
////        if (len == 1) {
////            hashMap.put(rank[0], 1);
////        }
////        else {
////            hashMap.put(rank[len - 1], rankCnt);
////        }
////
////        for (int i = 0; i < len; i++) {
////            System.out.print(rank[i] + " ");
////        }
////
////        System.out.print("\n");
////
////
////        for (int i = 0; i < len; i++) {
////            System.out.print(hashMap.get(i) + " ");
////        }
//
//        for (int i = 0; i < len; i++) {
//            int winnerCnt = 0;
//            for (int j = 0; j < len; j++) {
//                if (i != j && x[i] < x[j] && y[i] < y[j]) {
//                    winnerCnt++;
//                }
//            }
//            z[i] = winnerCnt + 1;
//            System.out.print(z[i] + " ");
//        }
//    }
//
//}