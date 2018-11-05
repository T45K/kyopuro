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
			int a = aArray[i];
			int b = bArray[i];
			String aString = Integer.toString(a);
			String c = Integer.toString(arrayLists[a].indexOf(b) + 1);
			System.out.println(getZeros(aString.length()) + aString + getZeros(c.length()) + c);
		}
	}

	public static String getZeros(int a) {
		if(a ==1) return "00000";
		if(a == 2) return "0000";
		if(a == 3) return "000";
		if( a == 4) return "00";
		if(a == 5) return "0";
		return "";
	}
}
