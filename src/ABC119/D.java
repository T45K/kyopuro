package ABC119;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class D {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final int shrine = scanner.nextInt();
		final int temple = scanner.nextInt();
		final int startPoint = scanner.nextInt();
		final List<Long> shrines = new ArrayList<>();
		final List<Long> temples = new ArrayList<>();

		for (int i = 0; i < shrine; i++) {
			shrines.add(scanner.nextLong());
		}

		for (int i = 0; i < temple; i++) {
			temples.add(scanner.nextLong());
		}

		Collections.sort(shrines);
		Collections.sort(temples);

		boolean isLeftShrine;

		long shortestDiff = Long.MAX_VALUE;
		long left;
		long resultLeft = 0, resultRight = 0;

		if (shrines.get(0) < temples.get(0)) {
			isLeftShrine = true;
			left = shrines.get(0);
			shrines.remove(0);
		} else {
			isLeftShrine = false;
			left = temples.get(0);
			temples.remove(0);
		}

		while (shrines.size() > 0 && temples.size() > 0) {
			if (shrines.get(0) < temples.get(0)) {
				if (isLeftShrine) {
					left = shrines.get(0);
					shrines.remove(0);
				} else {
					if (shrines.get(0) - left < shortestDiff) {
						resultLeft = left;
						resultRight = shrines.get(0);
						shortestDiff = resultRight - resultLeft;
					}
					left = shrines.get(0);
					isLeftShrine = true;
					shrines.remove(0);
				}
			} else {
				if (!isLeftShrine) {
					left = temples.get(0);
					temples.remove(0);
				} else {
					if (temples.get(0) - left < shortestDiff) {
						resultLeft = left;
						resultRight = temples.get(0);
						shortestDiff = resultRight - resultLeft;
					}
					left = temples.get(0);
					isLeftShrine = false;
					temples.remove(0);
				}

			}
		}
		
		final List<String> results = new ArrayList<>();
		for (int i = 0; i < startPoint; i++) {
			long index = scanner.nextLong();
			results.add(Long.toString(shortestDiff + Math.min(Math.abs(index - resultLeft), Math.abs(index - resultRight))));
		}

		System.out.println(String.join("\n", results));
		scanner.close();
	}
//check answer, later...
}
