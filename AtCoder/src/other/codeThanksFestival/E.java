package other.codeThanksFestival;

import java.util.Scanner;

public class E {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int counter = scanner.nextInt();
		long[] nums = new long[counter];
		long result = 0;
		
		for(int i= 0;i<counter;i++) {
			nums[i] = scanner.nextLong();
			if(nums[i] != 0 ) {
				result += (Math.log(nums[i])/Math.log(2)) + 1;
			}
		}
		
		

	}

}
