package AtCoder.ABC.ABC119;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.util.Scanner;

public class C {
	static int counter;
	static int a;
	static int b;
	static int c;
	static int[] bamboos;

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		counter = scanner.nextInt();
		a = scanner.nextInt();
		b = scanner.nextInt();
		c = scanner.nextInt();

		bamboos = new int[counter];
		for (int i = 0; i < counter; i++) {
			bamboos[i] = scanner.nextInt();
		}

		scanner.close();
		System.out.println(result(0, 0, 0, 0));
	}

	public static int result(int index, int aValue, int bValue, int cValue) {
		if (index == counter) {
			if (aValue == 0 || bValue == 0 || cValue == 0) {
				return 1000000000;
			}
			return abs(a - aValue) + abs(bValue - b) + abs(cValue - c) - 30;
		}

		final int ret1 = result(index + 1, aValue, bValue, cValue);
		final int ret2 = result(index + 1, aValue + bamboos[index], bValue, cValue) + 10;
		final int ret3 = result(index + 1, aValue, bValue + bamboos[index], cValue) + 10;
		final int ret4 = result(index + 1, aValue, bValue, cValue + bamboos[index]) + 10;

		return min(min(ret1, ret2), min(ret3, ret4));
	}
}
