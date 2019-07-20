package ABC134;

import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] array = new int[n];

        int most = 0;
        int secondMost = 0;

        for (int i = 0; i < n; i++) {
            final int next = scanner.nextInt();
            array[i] = next;

            if (next > most) {
                secondMost = most;
                most = next;
                continue;
            }

            if(next> secondMost){
                secondMost = next;
            }
        }

        for (int i = 0; i < n; i++) {
            if (array[i] == most) {
                System.out.println(secondMost);
                continue;
            }

            System.out.println(most);
        }
    }
}
