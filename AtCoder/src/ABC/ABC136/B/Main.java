package ABC.ABC136.B;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.nextLine();

        switch (s.length()) {
            case 1:
                System.out.println(Integer.parseInt(s));
                break;
            case 2:
                System.out.println(9);
                break;
            case 3:
                System.out.println((9 + Integer.parseInt(s) - 99));
                break;
            case 4:
                System.out.println(909);
                break;
            case 5:
                System.out.println((909 + Integer.parseInt(s) - 9999));
                break;
            default:
                System.out.println(90909);
        }
    }
}
