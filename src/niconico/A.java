package niconico;

import java.util.Scanner;

public class A {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int counter = scanner.nextInt();
		int[] sam = new int[counter];
		int sams = 0;
		
		for(int i = 0;i<counter;i++) {
			sam[i]= scanner.nextInt();
			sams += sam[i];
		}
		double ave = (double) (((double)sams)/((double)counter));
		System.out.println(ave);
		
		double sa = 100;
		int result = -1;
		
		for(int i = 0;i<counter;i++) {
			if(sa > shortAbs((double) ((double)sam[i]-ave))) {
				sa = shortAbs((double) ((double)sam[i]-ave));
				result = i;
			}
		}
		System.out.println(result);
	}
	
	public static double shortAbs(double a) {
		if(a < 0) {
			return (double) -a;
		}
		else {
			return a;
		}
	}
}
