package ABC121;

import java.nio.ByteBuffer;
import java.util.Scanner;

public class D {

	public static void main(String[] args) {
		final int capasity = 40;
		final Scanner scanner = new Scanner(System.in);
		long a = scanner.nextLong();
		long b = scanner.nextLong();
		byte[] aByte = ByteBuffer.allocate(capasity).putLong(a).array();
		byte[] bByte = ByteBuffer.allocate(capasity).putLong(b).array();
		byte[] rByte = new byte[capasity];

		long result = 0;
		for (long i = a; i <= b; i++) {
			result ^= i;
		}
		
		System.out.println(result);
		scanner.close();

	}

}
