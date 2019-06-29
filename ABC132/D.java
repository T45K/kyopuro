package ABC132;

import java.util.Scanner;

public class D {
    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();

        for (int i = 1; i < k + 1; i++) {
            if (i == 1) {
                System.out.println(n - k + 1);
                continue;
            }

            final int hashi0K = kumiawase(k - 1, i - 1);
            final int hashi0R = recursive(1, i - 1, n - k);
            final int hashi1R = recursive(1, i, n - k);
            final int hashi2R = recursive(1, i + 1, n - k);

            final int res = (hashi0K * hashi0R + 2 * hashi0K * hashi1R + hashi0K * hashi2R) % MOD;
            System.out.println(res);
        }
    }

    private static int recursive(final int depth, final int selectable, final int amount) {
        if (selectable == 1) {
            return 1;
        }

        if (depth == selectable - 1) {
            return amount - 1;
        }

        final int nokori = selectable - depth + 1;
        if (amount < nokori) {
            return 0;
        }

        int accum = 0;
        for (int i = 1; i < amount; i++) {
            accum += recursive(depth + 1, selectable, amount - i);
            accum %= MOD;
        }

        return accum;
    }

    private static int kumiawase(final int a, final int b) {
        int ue = 1;
        int sita = 1;

        for (int i = 0; i < b; i++) {
            ue *= a - i;
            ue %= MOD;
            sita *= i + 1;
            sita %= MOD;
        }

        return ue / sita;
    }
}
