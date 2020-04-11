package AtCoder.ABC.ABC131.D;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int count = scanner.nextInt();

        final List<Work> works = new ArrayList<>();

        for(int i = 0;i<count;i++){
            works.add(new Work(scanner.nextInt(), scanner.nextInt()));
        }

        works.sort(Comparator.comparing(Work::getDeadLine));

        int accum = 0;
        for (final Work work : works) {
            accum += work.getLength();

            if (accum > work.getDeadLine()) {
                System.out.println("No");
                return;
            }
        }

        System.out.println("Yes");
    }

    static class Work {
        private final int length;
        private final int deadLine;

        public Work(final int length, final int deadLine) {
            this.length = length;
            this.deadLine = deadLine;
        }

        public int getLength() {
            return length;
        }

        public int getDeadLine() {
            return deadLine;
        }
    }
}
