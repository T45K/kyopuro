package ABC.ABC113;

import java.util.Scanner;

public class B {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		final double coutner = scanner.nextInt();
		final double t = scanner.nextInt();
		final double a = scanner.nextInt();
		
		double result = 1000000000;
		int r = 0;
		
		for(int i = 0;i<coutner;i++) {
			double d;
			if((d = Math.abs(t - scanner.nextInt() * 0.006 - a)) < result) {
				result = d;
				r =  i + 1;
			}
		}
		System.out.println(r);
		
	}

}
