package ABC120;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final char[] a = scanner.nextLine().toCharArray();
		final List<Integer> list = new ArrayList<>();

		for (int i = 0; i < a.length; i++) {
			list.add(a[i] - '0');
		}

		int result = 0;
		while (true) {
			int tmp = 0;
			for (int i = 0; i < list.size() - 1; i++) {
				if (list.get(i) == 1 && list.get(i + 1) == 0 || list.get(i) == 0 && list.get(i + 1) == 1) {
					list.remove(i);
					list.remove(i);
					i--;
					tmp += 2;
				}
			}
			if(tmp == 0) {
				break;
			}
			result += tmp;
		}
		System.out.println(result);
		scanner.close();
	}

}
