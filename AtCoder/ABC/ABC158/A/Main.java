package AtCoder.ABC.ABC158.A;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.next();
        final long count = IntStream.range(0, 3)
                .mapToObj(s::charAt)
                .distinct()
                .count();

        if(count == 1){
            System.out.println("No");
        }else{
            System.out.println("Yes");
        }
    }
}
