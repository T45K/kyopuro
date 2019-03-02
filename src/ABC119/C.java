package ABC119;

import java.util.Arrays;
import java.util.Scanner;

public class C {
	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final int counter = scanner.nextInt();
		final int a = scanner.nextInt();
		final int b = scanner.nextInt();
		final int c = scanner.nextInt();

		final int[] bamboos = new int[counter];
		Arrays.stream(bamboos).forEach(s -> s = scanner.nextInt());
	}
}
