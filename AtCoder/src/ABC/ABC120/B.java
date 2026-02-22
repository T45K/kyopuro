package ABC.ABC120;

import java.util.Scanner;

public class B {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final int a = scanner.nextInt();
		final int b = scanner.nextInt();
		final int c = scanner.nextInt();

		int i = 0;
		int result = Math.max(a, b);
		while (true) {
			if (a % result == 0 && b % result == 0) {
				if (++i == c) {
					break;
				}
			}
			result--;
		}
		System.out.println(result);
	}

}
