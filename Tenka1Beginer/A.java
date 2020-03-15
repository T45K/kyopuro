package Tenka1Beginer;

import java.util.Scanner;

public class A {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		if(line.length() == 2) {
			System.out.println(line);
		}else {
			for(int i = line.length() -1 ; i >= 0 ; i--) {
				System.out.print(line.charAt(i));
			}
			System.out.println();
		}

	}

}
