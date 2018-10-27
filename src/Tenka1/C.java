package Tenka1;

import java.util.Arrays;
import java.util.Scanner;

public class C {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int counter = scanner.nextInt();
		int[] array = new int[counter];
		int[] result = new int[counter];
		
		for(int i =0;i<counter;i++) {
			array[i] = scanner.nextInt();
		}
		
		Arrays.sort(array);
		
		if(counter % 2 == 1) {
			int median = array[counter/2];
			result[0] = median;
			int answer1 = 0,answer2 = 0;
			
			for(int i = 1;i<= counter/2;i++) {
				result[2 * i - 1]= array[i -1];
				result[2 * i] = array[counter - i];
			}
			for(int i = 1;i<counter; i++) {
				answer1 += Math.abs(result[i] - result[i - 1] );
			}
		
			for(int i = 1;i<= counter/2;i++) {
				result[2 * i - 1]= array[counter - i];
				result[2 * i] = array[i -1];
			}	
			for(int i = 1;i<counter; i++) {
				answer2 += Math.abs(result[i] - result[i - 1] );
			}			
			
			System.out.println(Math.max(answer1, answer2));
		}
		else {
			int median1 = array[counter/2 - 1];
			int median2 = array[counter/2];
			
			int answer1 = 0;
			
			result[0] = median1;
			
			for(int i = 0;i<counter/2;i++) {
				result[2 * i + 1] = array[counter - i - 1];
			}
			
			for(int i = 1;i<counter/2;i++) {
				result[2 * i] = array[i-1];
			}
			
			for(int i = 1;i<counter; i++) {
				answer1 += Math.abs(result[i] - result[i - 1] );
			}
			
			int answer2 = 0;
			
			result[0] = median2;
			
			for(int i = 0;i<counter/2;i++) {
				result[2 * i + 1] = array[i];
			}
			
			for(int i = 1;i<counter/2;i++) {
				result[2 * i] = array[counter - i];
			}
			
			for(int i = 1;i<counter; i++) {
				answer2 += Math.abs(result[i] - result[i - 1] );
			}
			
			System.out.println(Math.max(answer1, answer2));
		}
	}
}
