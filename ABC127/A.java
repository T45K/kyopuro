package ABC127;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();

        if(a >= 13){
            System.out.println(b);
        }else if (a >= 6){
            System.out.println(b/2);
        }else{
            System.out.println(0);
        }
    }
}
