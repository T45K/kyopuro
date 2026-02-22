package ABC.ABC121;

import java.util.Scanner;

public class B {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final int numOfSorceCodes = scanner.nextInt();
		final int features = scanner.nextInt();
		final int constatnt = scanner.nextInt();

		final int[] feature = new int[features];
		for (int i = 0; i < features; i++) {
			feature[i] = scanner.nextInt();
		}

		int result = 0;
		for (int i = 0; i < numOfSorceCodes; i++) {
			final int[] sourceCodeFeature = new int[features];
			for (int j = 0; j < features; j++) {
				sourceCodeFeature[j] = scanner.nextInt();
			}

			int tmp = 0;
			for (int j = 0; j < features; j++) {
				tmp += feature[j] * sourceCodeFeature[j];
			}

			if (tmp + constatnt > 0) {
				result++;
			}

		}
		System.out.println(result);
		scanner.close();
	}

}
