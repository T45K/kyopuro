package ABC.ABC116;

import java.util.Scanner;

public class C {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int counter = scanner.nextInt();
        final int[] array = new int[counter];

        for (int i = 0; i < counter; i++) {
            array[i] = scanner.nextInt();
        }

        int result = 0;
        while (isLoopContinue(array)) {
            boolean bool = false;
            for (int i = 0; i < array.length; i++) {
                if (array[i] > 0) {
                    bool = true;
                    array[i]--;
                } else {
                    if (bool) {
                        bool = false;
                        result++;
                    }
                }
            }
            if (bool) {
                result++;
            }
        }
        System.out.println(result);
        scanner.close();
    }

    private static boolean isLoopContinue(final int[] array) {
		for (final int value : array) {
			if (value > 0) {
				return true;
			}
		}

        return false;
    }

}
