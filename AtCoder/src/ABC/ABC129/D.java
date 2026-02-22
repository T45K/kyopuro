package ABC.ABC129;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();

        final char[][] board = new char[h][w];

        scanner.nextLine();

        for (int i = 0; i < h; i++) {
            board[i] = scanner.nextLine().toCharArray();
        }

        final int[][] tate = new int[h][w];
        final int[][] yoko = new int[h][w];

        for (int i = 0; i < h; i++) {
            int accum = 0;
            int start = 0;
            for (int j = 0; j < w; j++) {
                if (board[i][j] == '.') {
                    accum++;
                    continue;
                }

                for (int k = start; k < j; k++) {
                    yoko[i][k] = accum;
                }
                accum = 0;
                start = j + 1;
            }

            for (int k = start; k < w; k++) {
                yoko[i][k] = accum;
            }
        }

        for (int i = 0; i < w; i++) {
            int accum = 0;
            int start = 0;
            for (int j = 0; j < h; j++) {
                if (board[j][i] == '.') {
                    accum++;
                    continue;
                }

                for (int k = start; k < j; k++) {
                    tate[k][i] = accum;
                }
                accum = 0;
                start = j + 1;
            }

            for (int k = start; k < h; k++) {
                tate[k][i] = accum;
            }
        }

        int result = 0;
        for(int i = 0;i<h;i++){
            for(int j = 0;j<w;j++){
                if(result < tate[i][j] + yoko[i][j] - 1){
                    result = tate[i][j] + yoko[i][j] -1;
                }
            }
        }

        System.out.println(result);
    }
}
