package deverta2;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int ball = scanner.nextInt();
        final int people = scanner.nextInt();

        if(people == 1){
            System.out.println(0);
            return;
        }

        System.out.println(ball - people);
    }
}
