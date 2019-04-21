package niconico;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class B {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int counter = scanner.nextInt();
		int choice = scanner.nextInt();
		Integer[] array = new Integer[counter];
		
		for(int i = 0;i<counter;i++) {
			array[i] = scanner.nextInt();
		}
		
		Arrays.sort(array,Comparator.reverseOrder());
	}
}
