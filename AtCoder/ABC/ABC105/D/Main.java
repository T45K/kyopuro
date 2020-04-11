package AtCoder.ABC.ABC105.D;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int[] candies = new int[n];
        final Map<Integer, AtomicLong> countMap = new HashMap<>();

        int accum = 0;
        for (int i = 0; i < candies.length; i++) {
            final int mod = scanner.nextInt() % m;
            accum = (accum + mod) % m;
            candies[i] = accum;
            countMap.computeIfAbsent(accum, counter -> new AtomicLong()).incrementAndGet();
        }


        long answer = countMap.get(0) != null ? countMap.get(0).get() : 0;
        for (final int candy : candies) {
            answer += countMap.get(candy).decrementAndGet();
        }

        System.out.println(answer);
    }
}
