package AtCoder.ABC.ABC121;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class C {

	public static void main(String[] args) {
		new C().run();
	}

	public void run() {
		final Scanner scanner = new Scanner(System.in);
		final int numOfStores = scanner.nextInt();
		int want = scanner.nextInt();

		Store[] stores = new Store[numOfStores];
		for (int i = 0; i < numOfStores; i++) {
			stores[i] = new Store(scanner.nextInt(), scanner.nextInt());
		}

		Arrays.sort(stores, new Comparator<Store>() {
			@Override
			public int compare(Store o1, Store o2) {
				return o1.getPrice() - o2.getPrice();
			}
		});

		long result = 0;

		for (int i = 0; i < numOfStores; i++) {
			if (want <= stores[i].getNumber()) {
				result += (long)want * (long)stores[i].getPrice();
				break;
			}

			result += (long)stores[i].getNumber() * (long)stores[i].getPrice();
			want -= (long)stores[i].getNumber();
		}

		System.out.println(result);
		scanner.close();
	}

	public class Store {
		final private int price;
		private int number;

		public Store(final int price, final int number) {
			this.price = price;
			this.number = number;
		}

		public int getPrice() {
			return this.price;
		}

		public int getNumber() {
			return this.number;
		}
	}

}
