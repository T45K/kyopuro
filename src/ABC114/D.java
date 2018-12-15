package ABC114;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class D {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		HashMap<Integer, Integer> map = new HashMap<>();
		int sosu = 2;
		int counter = 0;
		while(true) {
			if(a % sosu == 0) {
				counter++;
				a /= sosu;
			}else {
				if(counter != 0) {
					System.out.println(sosu);
					yaku.add(counter);
					counter = 0;
				}
				sosu ++;
				
				if(a == 1) {
					break;
				}
			}
		}
		yaku.forEach(s->System.out.println(s));
		int result = 0;
		
		for(int i = 0;i<yaku.size();i++) {
			if(yaku.get(i) >= 74) {
				result ++;
			}
		}
		
		for(int i = 0;i<yaku.size();i++) {
			if(yaku.get(i)>= 24) {
				for(int j = 0;j<yaku.size();j++) {
					if(yaku.get(j) >= 2 && j != i) {
						result ++;
					}
				}
			}
		}
		
		for(int i = 0;i<yaku.size();i++) {
			if(yaku.get(i) >= 4) {
				for(int j = i+1;j<yaku.size();j++) {
					if(yaku.get(j) >= 4) {
						for(int k = 0;k < yaku.size();k++) {
							if(yaku.get(k) >= 2 && k != i && k != j) {
								result ++;
							}
						}
					}
				}
			}
		}
		System.out.println(result);
	}
}
