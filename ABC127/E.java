package ABC127;

import java.util.Scanner;

public class E {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int counter = scanner.nextInt();
        scanner.nextInt();

        long a = scanner.nextInt();
        long cons = -1 * a + scanner.nextInt();
        long mina = a;
        long maxa = a;
        int count = 1;

        for (int i = 0; i < counter - 1; i++) {
            if (scanner.nextInt() == 1) {
                final long tmp = scanner.nextInt();
                cons -= tmp;
                mina = Math.min(tmp, mina);
                maxa = Math.max(tmp, maxa);

                cons += scanner.nextInt();
                count++;
            } else {
                System.out.println(mina + " " + (maxa * count + cons));
            }
        }

    }
}
