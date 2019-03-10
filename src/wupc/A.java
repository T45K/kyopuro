package wupc;

import java.util.Scanner;

public class A {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();

		while (string.contains("WA"))
			string = string.replaceAll("WA", "AC");

		System.out.println(string);
		scanner.close();

	}

}
