package ABC118;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class D {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final int stick = scanner.nextInt();
		final int availableNumber = scanner.nextInt();

		final List<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < availableNumber; i++) {
			numbers.add(scanner.nextInt());
		}

		minimize5Match(numbers);
		minimize6Match(numbers);
		minimizeSum7Match(numbers);
		minimizeSum6Match(numbers);
		minimizeSum5Match(numbers);
		minimizeSum4Match(numbers);
		
		scanner.close();
	}

	// 1 2 3 4 5 6 7 8 9
	// 2 5 5 4 5 6 3 7 6
	public static void minimizeSum4Match(List<Integer> list) {
		if(list.contains(4)) {
			if(list.contains(1)) {
				list.remove(list.indexOf(4));
			}
		}
	}
	
	public static void m(List<Integer> list, int m) {
		if (list.contains(m)) {
			if (list.contains(1) && list.contains(7)) {
				list.remove(list.indexOf(m));
			}
		}
	}

	public static void minimizeSum5Match(List<Integer> list) {
		m(list, 5);
		m(list, 2);
		m(list, 3);
	}

	public static void minimizeSum6Match(List<Integer> list) {
		if (list.contains(6)) {
			if (list.contains(1) || list.contains(7)) {
				list.remove(list.indexOf(6));
			}
		}
	}

	public static void minimizeSum7Match(List<Integer> list) {
		if (list.contains(8)) {
			if ((list.contains(1) || list.contains(4)) && list.contains(7)) {
				list.remove(list.indexOf(8));
				return;
			}

			if (list.contains(1) && (list.contains(2) || list.contains(3) || list.contains(5))) {
				list.remove(list.indexOf(8));
				return;
			}
		}
	}

	public static void minimize5Match(List<Integer> list) {
		if (list.contains(5)) {
			int i;
			if ((i = list.indexOf(3)) != -1) {
				list.remove(i);
			}

			if ((i = list.indexOf(2)) != -1) {
				list.remove(i);
			}
			return;
		}

		if (list.contains(3)) {
			if (list.contains(2)) {
				list.remove(list.indexOf(2));
			}
		}
	}

	public static void minimize6Match(List<Integer> list) {
		if (list.contains(9)) {
			if (list.contains(6)) {
				list.remove(list.indexOf(6));
			}
		}
	}

}
