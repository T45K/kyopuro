package kyudai;

import java.util.Scanner;

public class C {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner scanner = new Scanner(System.in);
		int height = scanner.nextInt();
		int width = scanner.nextInt();
		int danger = scanner.nextInt();
		
		int[] start = new int[2];
		int[] goal = new int[2];
		
		char[][] table = new char[height][width];
		
		scanner.nextLine();
		
		for(int i = 0;i<height;i++) {
			table[i]= scanner.nextLine().toCharArray(); 
		}
		
		for(int i = 0;i<height;i++) {
			for(int j = 0;j<width;j++) {
				if(table[i][j]== 'S') {
					start[0] = i;
					start[1] = j;
				}
				if(table[i][j]== 'G') {
					goal[0] = i;
					goal[1] = j;
				}
				if(table[i][j] == '@'){
					draw(table,i,j,danger);
					table[i][j]= '#'; 
				}
			}
		}
		
		int[][] answer = new int[height][width];
		
		for(int i = 0;i<height;i++) {
			for(int j = 0;j<width;j++) {
				if(table[i][j] == '#') {
					answer[i][j] = -1;
				}else {
					answer[i][j] = -1000000000;
				}
			}
			
		}
		
		answer[start[0]][start[1]] = 0;
		
		
		for(int x = 0;x < Math.max(height, width);x++) {
			for(int i  =1;i<height-1;i++) {
				for(int j = 1;j<width-1;j++) {					
					if(answer[i][j] == -1 ) continue;
					int  tmp = 1000000000;
					if(answer[i - 1][j] >= 0 && answer[i - 1][j] + 1 < tmp) tmp = answer[i - 1][j] + 1;
					if(answer[i + 1][j] >= 0 && answer[i+1][j] +1 < tmp) tmp = answer[i + 1][j] + 1;
					if(answer[i][j-1] >= 0 && answer[i][j-1] + 1<tmp) tmp = answer[i ][j-1] + 1;
					if(answer[i ][j+1] >= 0 && answer[i][j+1] + 1 < tmp) tmp = answer[i ][j+1] + 1;
					if(tmp != 1000000000 ) {
						if(answer[i ][j] == -1000000000) 
						answer[i][j]= tmp; 
						else {
							if( answer[i][j]> tmp)
								answer[i][j]= tmp;
						}
					
					}
				}
			}
		}
		int answ = 0;
		if((answ = answer[goal[0]][goal[1]])> 0) {
			System.out.println(answ);
		}else {
			System.out.println(-1);
		}
		
		for(int z = 0;z<height;z++) {
			for(int y = 0;y<width;y++) {
				System.out.print(answer[z][y]);
			}
			System.out.println();
		}

	}
	
	public static void draw(char[][] table,int i,int j,int danger) {
		if(danger == 0) return;
		if(table[i -1][j] == 'G'
		 ||table[i +1][j] == 'G'
		 ||table[i][j + 1] == 'G'
		 ||table[i][j + 1] == 'G') {
			System.out.println(-1);
			System.exit(0);
		}else {
			if(table[i - 1][j] == '.') {
				table[i-1][j] = '#';
				draw(table, i-1, j, danger-1);
			}
			if(table[i + 1][j] == '.') {
				table[i+1][j] = '#';
				draw(table, i+1, j, danger-1);
			}
			if(table[i][j -1] == '.') {
				table[i][j-1] = '#';
				draw(table, i, j-1, danger-1);
			}
			if(table[i][j+1] == '.') {
				table[i][j+1] = '#';
				draw(table, i, j + 1, danger-1);
			}
		}
	}
}
