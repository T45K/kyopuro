package ew;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class C {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final int length = scanner.nextInt();
		final int counter = scanner.nextInt();
		scanner.nextLine();
		final String string = scanner.nextLine();

		final Map<Character, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < length; i++) {
			map.computeIfAbsent(string.charAt(i), s -> new ArrayList<>())
				.add(i);
		}

		final int[] array = new int[length];
		for (int i = 0; i < length; i++) {
			array[i] = 1;
		}

		for (int i = 0; i < counter; i++) {
			final String[] order = scanner.nextLine()
				.split(" ");
			final List<Integer> intList = map.get(order[0].charAt(0));
			if(intList == null) {
				continue;
			}
			if (order[1].equals("L")) {
				for (int j = 0; j < intList.size(); j++) {
					final int index = intList.get(j);
					if (index == 0) {
						array[0] = 0;
					} else {
						array[index - 1] += array[index];
						array[index] = 0;
					}
				}
			} else {
				for (int j = intList.size() - 1; j >= 0; j--) {
					final int index = intList.get(j);
					if (index == length - 1) {
						array[index] = 0;
					} else {
						array[index + 1] += array[index];
						array[index] = 0;
					}
				}
			}
		}

		int result = 0;
		for (int value : array) {
			result += value;
		}

		System.out.println(result);

		scanner.close();

	}

}
