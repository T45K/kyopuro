package AtCoder.AGC.AGC031;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class A {

	public static void main(final String[] args) {
		new A().run();
	}

	public void run() {
		final Scanner scanner = new Scanner(System.in);
		final int counter = scanner.nextInt();
		scanner.nextLine();
		final char[] charSequence = scanner.nextLine().toCharArray();

		final Map<Integer, MyLong> wordCount = new HashMap<>();
		for (int index = 0; index < counter; index++) {
			wordCount.computeIfAbsent(Integer.valueOf(charSequence[index]) % 26, e -> new MyLong()).increment();
		}

		final List<MyLong> longs = new ArrayList<>(wordCount.values());

		long answer = 1;
		for (MyLong value : longs) {
			answer *= value.getValue() + 1;
			answer %= 1000000007;
		}

		--answer;
		System.out.println(answer);

		scanner.close();

	}

	class MyLong {
		private long value;

		public MyLong() {
			this.value = 0;
		}

		public void increment() {
			this.value++;
		}

		public long getValue() {
			return this.value;
		}
	}

}
