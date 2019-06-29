package ABC132;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.nextLine();
        final int[] counter = new int[26];

        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'A']++;
        }

        for(int i = 0;i<counter.length;i++){
            if(counter[i] != 0 && counter[i] != 2){
                System.out.println("No");
                return;
            }
        }

        System.out.println("Yes");
    }
}
