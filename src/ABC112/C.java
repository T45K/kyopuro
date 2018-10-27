package ABC112;

import java.util.Scanner;

public class C {
	
	static int counter;
	static int[] x,y;
	static int[][] height;
	static int resultX = 0,resultY = 0,resultH = 0;

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner scanner = new Scanner(System.in);
		counter = scanner.nextInt();
		x = new int[counter];
		y= new int[counter];
		height = new int[101][101];
		
		for(int i = 0;i<counter;i++) {
			height[x[i] = scanner.nextInt()][y[i]= scanner.nextInt()] = scanner.nextInt();
		}
		
		check();
		
		System.out.println(resultX + " " + resultY + " " + resultH);
		
	}
	
	public static void check() {
		for(resultX = 0;resultX<=100;resultX++) {
			for(resultY = 0;resultY<=100;resultY++) {
				resultH = height[x[0]][y[0]] + Math.abs(resultX-x[0]) + Math.abs(resultY - y[0]);
				for(int k = 1;k<counter;k++) {
					int tmp =  height[x[k]][y[k]] + Math.abs(resultX-x[k]) + Math.abs(resultY - y[k]);
					
					if(resultH == tmp) {
						if(k == counter -1) {
							return;
						}
					}
					else break;
				}
			}
		}
	}
}
