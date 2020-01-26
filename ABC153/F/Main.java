package ABC153.F;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    // TODO solve
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int d = scanner.nextInt();
        final int a = scanner.nextInt();

        final List<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final int x = scanner.nextInt();
            final int h = scanner.nextInt();
            monsters.add(new Monster(x, h));
        }
        monsters.sort(Comparator.comparingInt(monster -> monster.x));

        long counter = 0;
        while (!monsters.isEmpty()) {
            final Monster firstMonster = monsters.get(0);
            final int x = firstMonster.x;
            int i = 1;
            for (; i < monsters.size(); i++) {
                final Monster tmp = monsters.get(i);
                if (tmp.x - x > 2 * d) {
                    break;
                }
            }
            final int attack = (firstMonster.h + a - 1) / a * a;
            counter += attack / a;
            for (int j = 0; j < i; j++) {
                if (attack >= monsters.get(j).h) {
                    monsters.remove(j);
                    j--;
                    i--;
                } else {
                    monsters.get(j).h -= attack;
                }
            }
        }
        System.out.println(counter);
    }

    static class Monster {
        int x;
        int h;

        Monster(final int x, final int h) {
            this.x = x;
            this.h = h;
        }
    }
}
