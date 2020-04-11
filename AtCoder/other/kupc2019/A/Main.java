package kupc2019.A;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int x = scanner.nextInt();

        final List<Integer> themes = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            themes.add(scanner.nextInt());
        }

        themes.sort(Comparator.reverseOrder());
        final Integer maxValue = themes.get(0);
        int count = 0;
        for (final Integer theme : themes) {
            if(theme + x < maxValue){
                break;
            }

            count++;
        }

        System.out.println(count);
    }
}
