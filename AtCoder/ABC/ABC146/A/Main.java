package AtCoder.ABC.ABC146.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.next();
        switch (s){
            case "SUN":
                System.out.println(7);
                return;
            case "MON":
                System.out.println(6);
                return;
            case "TUE":
                System.out.println(5);
                return;
            case "WED":
                System.out.println(4);
                return;
            case "THU":
                System.out.println(3);
                return;
            case "FRI":
                System.out.println(2);
                return;
            default:
                System.out.println(1);
        }
    }
}
