package ABC119;

import java.util.Arrays;
import java.util.Scanner;

public class D {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final int shrine = scanner.nextInt();
		final int temple = scanner.nextInt();
		final int startPoint = scanner.nextInt();
		final long[] shrines = new long[shrine];
		final long[] temples = new long[temple];

		for (int i = 0; i < shrine; i++) {
			shrines[i] = scanner.nextLong();
		}

		for (int i = 0; i < temple; i++) {
			temples[i] = scanner.nextLong();
		}

		Arrays.sort(shrines);
		Arrays.sort(temples);

		boolean shr = true;
		long l = Long.MAX_VALUE, r = 0;
		int sIndex = 0, tIndex = 0;
		long result = Long.MAX_VALUE;
		int rlIndex = Integer.MAX_VALUE, rrIndex = 0;

		for (int count = 0; count < shrine + temple; count++) {
			if (shrines[sIndex] < temples[tIndex]) {
				if (shr) {
					l = shrines[sIndex++];
				} else {

				}
			} else {
				l = temples[tIndex];
				tIndex++;

			}
		}

		scanner.close();
	}

}
