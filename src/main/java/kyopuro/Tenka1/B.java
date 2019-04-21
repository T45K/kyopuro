package Tenka1;

import java.util.Scanner;

public class B {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Scanner scanner = new Scanner(System.in);
		long a = scanner.nextInt();
		long b = scanner.nextInt();
		int k = scanner.nextInt();
		
		for(int i= 0;i<k;i++) {
			if(a % 2 != 0) {
				a--;
			}
			a /= 2;
			b += a;
			
			if(++i < k) {
				if(b % 2 != 0) {
					b--;
				}
				b /= 2;
				a += b;
			}else
				break;
		}
		
		System.out.println(a + " " + b);
	}

}
