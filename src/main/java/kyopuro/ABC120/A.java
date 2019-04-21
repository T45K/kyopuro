package ABC120;

import java.util.Scanner;

public class A {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final int a = scanner.nextInt();
		final int b = scanner.nextInt();
		final int c = scanner.nextInt();
		System.out.println(Math.min(b / a, c));
		scanner.close();
	}

}
