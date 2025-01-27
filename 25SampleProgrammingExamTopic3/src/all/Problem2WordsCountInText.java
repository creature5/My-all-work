package all;

import java.util.*;

public class Problem2WordsCountInText {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String userInput = input.nextLine();
		input.close();
		TreeMap<String, Integer> words = getWordsInText(userInput.toLowerCase());
		for (Map.Entry<String, Integer> item : words.entrySet()) {
			System.out.printf("%s -> %d%n", item.getKey(), item.getValue());
		}
	}

	private static TreeMap<String, Integer> getWordsInText(String text) {
		TreeMap<String, Integer> words = new TreeMap<String, Integer>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			sb.append(c);
			for (int j = 0; j < sb.length(); j++) {
				StringBuilder currentWord = substring(sb, j);
				saveWord(currentWord, words);
			}
		}
		return words;
	}

	private static void saveWord(StringBuilder word, TreeMap<String, Integer> words) {
		if (word.length() == 0) {
			return;
		}
		String newWord = word.toString();
		if (words.containsKey(newWord)) {
			int number = words.get(newWord) + 1;
			words.put(newWord, number);
		} else {
			words.put(newWord, 1);
		}
	}

	private static StringBuilder substring(StringBuilder text, int startIndex) {
		StringBuilder word = new StringBuilder();
		for (int i = startIndex; i < text.length(); i++) {
			char currentSymbol = text.charAt(i);
			word.append(currentSymbol);
		}
		return word;
	}
}
