package AtCoder.ABC.ABC118;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class C {
	static int counter;
	static ArrayList<Integer> list;

	public static void main(final String[] args) {
		final Scanner scanner = new Scanner(System.in);
		counter = scanner.nextInt();
		list = new ArrayList<>();
		for (int i = 0; i < counter; i++) {
			list.add(scanner.nextInt());
		}
		
		int min = 100000000;
		while (list.size() != 1) {
			min = Collections.min(list);
			for (int i = 0; i < list.size(); i++) {
				int j = list.get(i) % min;
				if (j == 0) {
					list.remove(i);
					i--;
				} else {
					list.set(i,j);
				}
			}
			list.add(min);
		}
		System.out.println(min);
		scanner.close();
	}
}
