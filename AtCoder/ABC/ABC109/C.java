package AtCoder.ABC.ABC109;

import java.util.Arrays;
import java.util.Scanner;

public class C {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int counter = scanner.nextInt();
		int start = scanner.nextInt();
		int[] point = new int[counter];

		for (int i = 0; i < counter; i++) {
			point[i] = Math.abs(scanner.nextInt() - start);
		}

		Arrays.sort(point);

		for (int i = counter - 1; i > 0; i--) {
			getGCD(point, i - 1, i);
		}

		System.out.println(point[0]);
		scanner.close();
	}

	public static void getGCD(int[] a, int x, int y) {
		int tmp;
		while ((tmp = a[y] % a[x]) != 0) {
			a[y] = a[x];
			a[x] = tmp;
		}
	}
}
