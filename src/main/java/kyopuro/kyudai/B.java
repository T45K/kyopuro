package kyudai;

import java.util.Scanner;

public class B {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int counter = scanner.nextInt();
		
		for(int i = 0;i<counter;i++) {
			int yen100 = scanner.nextInt();
			int yen10 = scanner.nextInt();
			int yen1 = scanner.nextInt();
			
			if(yen100 % 2 == 0
			&& yen10  % 2 == 0
			&& yen1   % 2 == 0) {
				System.out.println("Yes");
				continue;
			}
			if(yen1 %2 == 1) {
				System.out.println("No");
				continue;
			}
			
			if(yen100 %2 ==1) {
				if(yen10 >= 10) {
					yen10 -= 10;
					if(yen10 % 2 == 1) {
						if(yen1 >= 10) {
							if(yen1 %2 == 1) {
								System.out.println("No");
								continue;
							}
							else {
								System.out.println("Yes");
								continue;
							}
						}else {
							System.out.println("No");
							continue;
						}
					}else {
						if(yen1 %2 == 1) {
							System.out.println("No");
							continue;
						}
						else {
							System.out.println("Yes");
							continue;
						}
						
					}
				}else {
					if(yen1 >= 100 - yen10 *10) {
						if((yen1 -= 100 - yen10*10) %2 == 0) {
							System.out.println("Yes");
							continue;
						}else {
							System.out.println("No");
							continue;
						}						
					}else {
						System.out.println("No");
						continue;
					}
				}
			}else {
					if(yen10 % 2 == 1) {
						if(yen1 >= 10) {
							if(yen1 %2 == 1) {
								System.out.println("No");
								continue;
							}
							else {
								System.out.println("Yes");
								continue;
							}
						}else {
							System.out.println("No");
							continue;
						}
					}else {
						if(yen1 %2 == 1) {
							System.out.println("No");
							continue;
						}
						else {
							System.out.println("Yes");
							continue;
						}
					}				
			}
		}
		
	}

}
