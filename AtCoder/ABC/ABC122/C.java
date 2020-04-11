package AtCoder.ABC.ABC122;

import java.util.Scanner;

public class C {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final int counter = scanner.nextInt();
		final int loop = scanner.nextInt();
		scanner.nextLine();
		final String line = scanner.nextLine();
		final int[][] index = new int[loop][2];
		final int[] array = new int[counter];
		
		for(int i = 0;i<loop;i++) {
			index[i][0] = scanner.nextInt();
			index[i][1] = scanner.nextInt();
		}

		for (int i = 1; i < counter; i++) {
			array[i] = array[i - 1];
			if (line.charAt(i) == 'C' && line.charAt(i - 1) == 'A') {
				array[i]++;
			}
		}

		for (int i = 0; i < loop; i++) {
			final int begin = index[i][0];
			final int end = index[i][1];
			System.out.println(array[end - 1] - array[begin - 1]);
		}

		scanner.close();
	}

}
