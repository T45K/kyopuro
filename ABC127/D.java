package ABC127;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final int[] cards = new int[n];

        for (int i = 0; i < n; i++) {
            cards[i] = scanner.nextInt();
        }

        Arrays.sort(cards);
        final Cs[] cs = new Cs[m];
        final D d = new D();

        for (int i = 0; i < m; i++) {
            cs[i] = d.new Cs(scanner.nextInt(), scanner.nextInt());
        }

        Arrays.sort(cs, Comparator.reverseOrder());

        int counter = 0;

        for(int i = 0;i<m;i++){
            final int c = cs[i].getNum();
            final int ati = cs[i].getAtai();
            boolean f = false;
            for(int j = 0;j<c;j++){
                if (counter < n && cards[counter] >= ati) {
                    f = true;
                    break;
                }

                cards[counter] = ati;
                counter++;
            }
            if(f){
                break;
            }
        }

        long result = 0;
        for (final int card : cards) {
            result += card;
        }

        System.out.println(result);
    }

    class Cs implements Comparable<Cs> {
        private final int num;
        private final int atai;

        public Cs(final int num, final int atai) {
            this.num = num;
            this.atai = atai;
        }

        public int getNum() {
            return num;
        }

        public int getAtai() {
            return atai;
        }

        @Override
        public int compareTo(final Cs cs) {
            return this.atai - cs.atai;
        }
    }
}
