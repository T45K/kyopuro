package ABC122;

import java.util.Scanner;

public class A {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final String string = scanner.nextLine();
		if (string.contains("A")) {
			System.out.println("T");
		} else if (string.contains("T")) {
			System.out.println("A");
		} else if (string.contains("C")) {
			System.out.println("G");
		} else {
			System.out.println("C");
		}
		scanner.close();
	}

}
