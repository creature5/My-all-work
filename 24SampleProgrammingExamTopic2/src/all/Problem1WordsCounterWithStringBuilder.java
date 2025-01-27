package all;

import java.util.*;

public class Problem1WordsCounterWithStringBuilder {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String text = input.nextLine();
		input.close();
		String[] words = extractWords(text);
		countWords(words);
	}

	private static String[] extractWords(String text) {
        StringBuilder word = new StringBuilder();
        List<String> extractedWords = new ArrayList<String>();
        
        char currentChar;
        for (int i = 0; i < text.length(); i++) {
            currentChar = text.charAt(i);

            if (Character.isLetter(currentChar)) {
                word.append(currentChar);
            } else if (word.length() > 0) {
	            extractedWords.add(word.toString());
	            word.setLength(0);
            }
        }

        if (word.length() > 0) {
            extractedWords.add(word.toString());
            word.setLength(0);
        }

        return extractedWords.toArray(new String[extractedWords.size()]);
    }

	private static boolean isUpperCase(String word) {
		boolean result = word.equals(word.toUpperCase());
		return result;
	}

	private static boolean isLowerCase(String word) {
		boolean result = word.equals(word.toLowerCase());
		return result;
	}

	private static void countWords(String[] words) {
        int upperCaseWordsCount = 0;
        int lowerCaseWordsCount = 0;

        for (String word : words) {
            if (isUpperCase(word)) {
                upperCaseWordsCount++;
            } else if (isLowerCase(word)) {
                lowerCaseWordsCount++;
            }
        }
        
        int allWordsCount = words.length;
        printCounts(allWordsCount, upperCaseWordsCount, lowerCaseWordsCount);
    }

	private static void printCounts(int allWordsCount,
			int allUpperCaseWordsCount, int allLowerCaseWordsCount) {
		System.out.println(allWordsCount);
		
		System.out.println(allUpperCaseWordsCount);
		
		System.out.println(allLowerCaseWordsCount);
	}

}
