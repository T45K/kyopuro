package wupc;

import java.util.Scanner;

public class E {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final int col = scanner.nextInt();
		final int row = scanner.nextInt();
		final int[][] board = new int[col][row];

		for (int colCounter = 0; colCounter < col; colCounter++) {
			for (int rowCounter = 0; rowCounter < row; rowCounter++) {
				board[colCounter][rowCounter] = scanner.nextInt();
			}
		}

		final int[] colNum = new int[col];
		final int[] rowNum = new int[row];

		for (int colCounter = 0; colCounter < col; colCounter++) {
			int oneCounter = 0;
			for (int rowCounter = 0; rowCounter < row; rowCounter++) {
				if (board[colCounter][rowCounter] == 1) {
					oneCounter++;
				}
			}
			colNum[colCounter] = oneCounter;
		}

		for (int rowCounter = 0; rowCounter < col; rowCounter++) {
			int oneCounter = 0;
			for (int colCounter = 0; colCounter < row; colCounter++) {
				if (board[colCounter][rowCounter] == 1) {
					oneCounter++;
				}
			}
			rowNum[rowCounter] = oneCounter;
		}

		int colResult = 0;
		for (int colCounter = 0;;)

			scanner.close();
	}

}
