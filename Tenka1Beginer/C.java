package Tenka1Beginer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class C {
	public static void main(final String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final int n = scanner.nextInt();
		final List<Long> list = IntStream.range(0, n)
				.mapToObj(i -> scanner.nextLong())
				.sorted()
				.collect(Collectors.toList());

		final List<Long> list1 = createList(n, 1);
		final List<Long> list2 = createList(n, -1);

		System.out.println(Math.max(sum(list, list1), sum(list, list2)));
	}

	private static long sum(final List<Long> list1, final List<Long> list2) {
		long sum = 0;
		for (int i = 0; i < list1.size(); i++) {
			sum += list1.get(i) * list2.get(i);
		}
		return sum;
	}

	private static List<Long> createList(final int length, long sign) {
		final List<Long> list = new ArrayList<>(length);
		for (int i = 0; i < length; i++) {
			if (i == 0) {
				list.add(sign);
			} else if (i == length - 1) {
				list.add(sign);
			} else {
				list.add(2 * sign);
			}
			sign *= -1;
		}
		Collections.sort(list);
		return list;
	}
}
