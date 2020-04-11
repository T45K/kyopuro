package AtCoder.ABC.ABC119;

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

        for (int i = 0; i < startPoint; i++) {
            final long point = scanner.nextLong();
            int sp = Collections.binarySearch(shrines, point);
            final List<Long> candidateShrines = new ArrayList<>();
            if (sp >= 0) {
                candidateShrines.add(0L);
            } else {
                sp = -(sp + 1);
                if (sp != 0) {
                    candidateShrines.add(shrines.get(sp - 1) - point);
                }

                if (sp != shrines.size()) {
                    candidateShrines.add(shrines.get(sp) - point);
                }
            }

            int tp = Collections.binarySearch(temples, point);
            final List<Long> candidateTemples = new ArrayList<>();
            if (tp >= 0) {
                candidateShrines.add(0L);
            } else {
                tp = -(tp + 1);
                if (tp != 0) {
                    candidateTemples.add(temples.get(tp - 1) - point);
                }

                if (tp != temples.size()) {
                    candidateTemples.add(temples.get(tp) - point);
                }
            }

            final List<Long> candidateAnswers = new ArrayList<>();
            for (final Long candidateShrine : candidateShrines) {
                for (final Long candidateTemple : candidateTemples) {
                    candidateAnswers.add(calc(candidateShrine, candidateTemple));
                }
            }

            Collections.sort(candidateAnswers);
            System.out.println(candidateAnswers.get(0));
        }
    }

    private static long calc(final long a, final long b) {
        if (a < 0 && b > 0 || a > 0 && b < 0) {
            final long aa = Math.abs(a);
            final long ab = Math.abs(b);

            return Math.min(aa, ab) * 2 + Math.max(aa, ab);
        } else {
            return Math.max(Math.abs(a), Math.abs(b));
        }
    }
}
