package ABC86;

import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int counter = scanner.nextInt();
        int k = scanner.nextInt();

        int[][] whiteBoard = new int[2 * k][2 * k];
        int[][] blackBoard = new int[2 * k][2 * k];

        for (int i = 0; i < counter; i++) {
            int x = (scanner.nextInt() - 1) % (2 * k);
            int y = (scanner.nextInt() - 1) % (2 * k);
            int wb = scanner.nextInt();
            if (wb == 'W') {
                whiteBoard[x][y]++;
            } else {
                blackBoard[x][y]++;
            }
        }

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {

            }
        }
    }

}
