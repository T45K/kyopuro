package ABC122;

import java.util.Scanner;

public class B {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final String string = scanner.nextLine();

		String answerString = "";

		for (int i = 0; i < string.length(); i++) {
			String temp = "";
			for (int j = i; j < string.length() && isATCG(string.charAt(j)); j++) {
				temp += String.valueOf(string.charAt(j));
			}
			if (temp.length() > answerString.length())
				answerString = temp;
		}
		System.out.println(answerString.length());
		scanner.close();
	}

	public static boolean isATCG(final char chara) {
		if (chara == 'A' || chara == 'T' || chara == 'C' || chara == 'G')
			return true;
		return false;
	}

}
