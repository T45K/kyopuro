package ABC111;

import java.util.*;
public class C {

	public static void main(String[] args) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		Scanner scanner = new Scanner(System.in);
		
		int counter = scanner.nextInt();
		int[] odd = new int[100000];
		int[] even = new int[100000];
		
		boolean bool = true;
		for(int i = 0;i<counter;i++) {
			if(bool) odd[scanner.nextInt()]++;
			else even[scanner.nextInt()]++;
			bool = !bool;
		}
		
		int a=0,b=0,c=0,d=0;
		
		for(int i = 0;i<100000;i++) {
			if(odd[i] > a) {
				a = odd[i];
				b = i;
			}
			if(even[i] > c) {
				c = odd[i];
				d = i;
			}
		}
		
		bool = b == d;
		
		Arrays.sort(odd);
		Arrays.sort(even);
		
		if(!bool) {
			System.out.println(counter - odd[99999] - even[99999]);
		}else {
			if(odd[99999] > even[99999]) {
				System.out.println(counter - odd[99999] - even[99998]);
			}else {
				System.out.println(counter - odd[99998] - even[99999]);
			}
		}
	}
}
