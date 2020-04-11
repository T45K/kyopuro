package AtCoder.ABC.ABC124.D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int length = scanner.nextInt();
        final int counter = scanner.nextInt();
        scanner.nextLine();

        final String people = "1" + scanner.nextLine() + "1";
        final List<OneIndex> oneIndexList = new ArrayList<>();

        for (int i = 0; i < people.length(); i++) {
            if (people.charAt(i) == '0') {
                continue;
            }

            int j = i;
            for (; j < people.length(); j++) {
                if (people.charAt(j) == '0') {
                    break;
                }
            }

            oneIndexList.add(new OneIndex(i, j));
            i = j;
        }

        if (oneIndexList.size() <= counter) {
            System.out.println(length);
            return;
        }

        final int[] results = new int[oneIndexList.size() - counter];
        for (int i = 0; i < results.length; i++) {
            results[i] = oneIndexList.get(i + counter).getEnd() - oneIndexList.get(i).getBegin();
        }

        results[0]--;
        results[results.length - 1]--;

        Arrays.sort(results);

        System.out.println(results[results.length - 1]);
    }

    static class OneIndex {
        private final int begin;
        private final int end;

        OneIndex(final int begin, final int end) {
            this.begin = begin;
            this.end = end;
        }

        int getBegin() {
            return begin;
        }

        int getEnd() {
            return end;
        }
    }
}
