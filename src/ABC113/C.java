package ABC113;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class C {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int p = scanner.nextInt() + 1;
		int counter = scanner.nextInt();
		int[] aArray = new int[counter];
		int[] bArray = new int[counter];
		
		ArrayList<Integer>[] arrayLists = new ArrayList[p];
		for(int i = 0;i<counter;i++) {
			int y = scanner.nextInt();
			int t = scanner.nextInt();
			aArray[i]= y;
			bArray[i]= t; 
			if(arrayLists[y] == null)
				arrayLists[y] = new ArrayList<>(); 
			arrayLists[y].add(t);
		}
		
		for(int i = 0;i<p;i++) {
			if(arrayLists[i]!= null && arrayLists[i].size() > 1 ) {
				Collections.sort(arrayLists[i]);
			}
		}
		
		for(int i = 0;i<counter;i++) {
			System.out.println(String.format("%012d", aArray[i] * 1000000 + arrayLists[aArray[i]].indexOf(bArray[i] ) + 1));
		}
	}
}
