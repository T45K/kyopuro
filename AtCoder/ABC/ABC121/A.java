package AtCoder.ABC.ABC121;

import java.util.Scanner;

public class A {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final int col = scanner.nextInt();
		final int row = scanner.nextInt();
		final int selectCol = scanner.nextInt();
		final int selectRow = scanner.nextInt();

		System.out.println((col - selectCol) * (row - selectRow));
		scanner.close();
	}

}
