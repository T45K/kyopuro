package deverta2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        new B().run();
    }

    private void run() {
        final Scanner scanner = new Scanner(System.in);
        final int count = scanner.nextInt();
        if (count == 1) {
            System.out.println(1);
            return;
        }
        final long[][] table = new long[count][2];

        for (int i = 0; i < count; i++) {
            table[i][0] = scanner.nextLong();
            table[i][1] = scanner.nextLong();
        }

        List<Len> lens = new ArrayList<>();
        List<MyInteger> counts = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                if (table[i][0] > table[j][0]) {
                    extracted(table, lens, counts, j, i);
                } else if (table[i][0] == table[j][0]) {
                    if (table[i][1] > table[j][1]) {
                        extracted(table, lens, counts, i, j);
                    } else {
                        extracted(table, lens, counts, j, i);
                    }
                } else {
                    extracted(table, lens, counts, i, j);
                }
            }
        }
        Collections.sort(counts);
        System.out.println(count - counts.get(0).getInteger());
    }

    private void extracted(final long[][] table, final List<Len> lens, final List<MyInteger> counts, final int i, final int j) {
        final Len len = new Len(table[j][0] - table[i][0], table[j][1] - table[i][1]);
        int index;
        if ((index = lens.indexOf(len)) != -1) {
            counts.get(index).increment();
        } else {
            lens.add(len);
            counts.add(new MyInteger());
        }
    }

    class MyInteger implements Comparable {
        private int integer = 1;

        void increment() {
            this.integer++;
        }

        public int getInteger() {
            return integer;
        }

        @Override
        public int compareTo(final Object o) {
            return ((MyInteger) o).getInteger() - this.integer;
        }
    }

    class Len {
        private final long x;
        private final long y;

        Len(final long x, final long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(final Object obj) {
            return ((Len) obj).getX() == this.getX() && ((Len) obj).getY() == this.getY();
        }

        long getX() {
            return x;
        }

        long getY() {
            return y;
        }
    }
}
