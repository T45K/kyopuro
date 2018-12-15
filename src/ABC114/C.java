package ABC114;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class C {
	private static int counter;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		counter = scanner.nextInt();
		ArrayList<Integer> list = new ArrayList<>();
		get(list, 0);
		
		Iterator<Integer> iterator = list.iterator();
		
		while(iterator.hasNext()) {
			Integer aInteger = iterator.next();
			if(!(aInteger.toString().contains("3")
					&& aInteger.toString().contains("5")
					&& aInteger.toString().contains("7"))) {
				iterator.remove();
			}
		}
		System.out.println(list.size());
	}

	public static void get(ArrayList<Integer> list,int a) {
		if(a <= counter && a >= 0) {
			list.add(a);
		}else {
			return;
		}
			
		get(list, a * 10 + 3);
		get(list, a * 10 + 5);
		get(list, a * 10 + 7);
	}
}
