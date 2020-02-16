package ABC155.A;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long count = IntStream.range(0, 3)
                .mapToObj(i -> scanner.nextInt())
                .distinct()
                .count();

        if(count == 2){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
    }
}
