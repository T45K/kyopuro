package ABC112;

import java.util.Scanner;

public class D {

	public static void main(String[] args) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		
		if(a == 1) {
			System.out.println(b);
			return;
		}
	
		for(int i = b/2;i>0;i--) {
			if(b%i == 0) {
				if(b/i >= a) {
					System.out.println(i);
					return;
				}
			}
		}
	}
}


/*
TLE
�񐔂�O(��M)�ŋ��߂�
*/