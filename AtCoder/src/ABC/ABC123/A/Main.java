package ABC.ABC123.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int[] antennas = new int[5];
        for(int i = 0;i<5;i++){
            antennas[i] = scanner.nextInt();
        }

        final int distance = scanner.nextInt();

        for(int i = 0;i<5;i++){
            for(int j = i+1;j<5;j++){
                if(antennas[j]-antennas[i] > distance){
                    System.out.println(":(");
                    return;
                }
            }
        }
        System.out.println("Yay!");
    }
}
