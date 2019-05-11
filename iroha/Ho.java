package kyopuro.iroha;

import java.util.Scanner;

public class Ho {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int mult = scanner.nextInt();
        final int counter = scanner.nextInt();

        final int[] elements = new int[mult];
        int i = 2;
        int count = 0;
        int alt = mult;
        while (alt != 1) {
            if (alt % i == 0) {
                elements[i]++;
                alt /= i;
                count++;
            } else {
                i++;
            }
        }

        if (count < counter) {
            System.out.println(-1);
        } else {
            int point = 2;
            for (int j = 0; j < counter - 1; j++) {
                if (elements[point] != 0) {
                    System.out.print(point + " ");
                    elements[point]--;
                } else {
                    while (elements[++point] == 0) ;
                    System.out.print(point + " ");
                    elements[point]--;
                }
            }

            int k = point;
            int result = 1;
            while (k < elements.length) {
                if (elements[k] != 0) {
                    result *= k;
                    elements[k]--;
                }else {
                    k++;
                }
            }
            System.out.print(result);
        }
    }
}
