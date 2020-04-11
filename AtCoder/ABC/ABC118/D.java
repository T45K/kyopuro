package AtCoder.ABC.ABC118;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class D {

    private static final int[] requirement = {-1, 2, 5, 5, 4, 5, 6, 3, 7, 6};

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final Set<Integer> numbers = new HashSet<>();
        for (int i = 0; i < m; i++) {
            numbers.add(scanner.nextInt());
        }

        removeRedundantNumber(numbers);
        final List<Tuple> tuples = numbers.stream()
                .map(e -> new Tuple(e, requirement[e]))
                .sorted((o1, o2) -> o2.getOriginalNumber() - o1.getOriginalNumber())
                .collect(Collectors.toList());

        final String[][] dp = new String[tuples.size()][n + 1];
        dp[0][0] = "";

        for (int i = 0; i < tuples.size(); i++) {
            for (int j = 0; j < n + 1; j++) {
                if (dp[i][j] != null) {
                    if (j < n - tuples.get(i).getNumOfMatches() + 1) {
                        final String tmp = dp[i][j] + tuples.get(i).getOriginalNumberString();
                        if (dp[i][j + tuples.get(i).getNumOfMatches()] == null || originalStringCompare(tmp, dp[i][j + tuples.get(i).getNumOfMatches()])) {
                            dp[i][j + tuples.get(i).getNumOfMatches()] = tmp;
                        }
                    }

                    if (i != tuples.size() - 1) {
                        dp[i + 1][j] = dp[i][j];
                    }

                    if (i != tuples.size() - 1 && j < n - tuples.get(i + 1).getNumOfMatches() + 1) {
                        final String tmp = dp[i][j] + tuples.get(i + 1).getOriginalNumberString();
                        if (dp[i + 1][j + tuples.get(i + 1).getNumOfMatches()] == null || originalStringCompare(tmp, dp[i + 1][j + tuples.get(i + 1).getNumOfMatches()]))
                            dp[i + 1][j + tuples.get(i + 1).getNumOfMatches()] = tmp;
                    }
                }
            }
        }

        System.out.println(dp[tuples.size() - 1][n]);
    }

    private static boolean originalStringCompare(final String a, final String b) {
        if (a.length() > b.length()) {
            return true;
        }

        if (b.length() > a.length()) {
            return false;
        }

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) > b.charAt(i))
                return true;

            if (a.charAt(i) < b.charAt(i)) {
                return false;
            }
        }

        return false;
    }

    private static void removeRedundantNumber(final Set<Integer> numbers) {
        if (numbers.contains(5)) {
            numbers.remove(3);
            numbers.remove(2);
        }

        if (numbers.contains(3)) {
            numbers.remove(2);
        }

        if (numbers.contains(1)) {
            numbers.remove(4);
            numbers.remove(6);
            numbers.remove(9);
        }

        if (numbers.contains(7)) {
            numbers.remove(9);
            numbers.remove(6);
        }
    }

    static class Tuple {
        private final int originalNumber;
        private final int numOfMatches;

        Tuple(final int originalNumber, final int numOfMatches) {
            this.originalNumber = originalNumber;
            this.numOfMatches = numOfMatches;
        }

        int getOriginalNumber() {
            return originalNumber;
        }

        String getOriginalNumberString() {
            return Integer.toString(originalNumber);
        }

        int getNumOfMatches() {
            return numOfMatches;
        }
    }

    // 1 2 3 4 5 6 7 8 9
    // 2 5 5 4 5 6 3 7 6
}
