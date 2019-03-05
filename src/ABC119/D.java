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
		
		

		scanner.close();
	}

}
