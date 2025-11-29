package leetcode.medium.twopointer;

/**
 * Problem: Reverse a sentence word-by-word, but keep punctuations in their original relative positions.
 * Input: "God, is Great"
 * Output: "Great, is God"
 **/
public class ReverseWordsWithPunctuation {

    public String reverseWordsKeepingPunctuation(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return sentence;
        }

        // Step 1: Extract all words (including apostrophes within words)
        java.util.List<String> words = new java.util.ArrayList<>();
        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);
            if (Character.isLetterOrDigit(ch) || (ch == '\'' && currentWord.length() > 0 && i + 1 < sentence.length() && Character.isLetter(sentence.charAt(i + 1)))) {
                currentWord.append(ch);
            } else {
                if (currentWord.length() > 0) {
                    words.add(currentWord.toString());
                    currentWord = new StringBuilder();
                }
            }
        }
        // Add the last word if present
        if (currentWord.length() > 0) {
            words.add(currentWord.toString());
        }

        // Step 2: Reverse the words list
        java.util.Collections.reverse(words);

        // Step 3: Reconstruct the sentence with reversed words
        StringBuilder result = new StringBuilder();
        int wordIndex = 0;

        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);

            if (Character.isLetterOrDigit(ch) || (ch == '\'' && i > 0 && i + 1 < sentence.length() && Character.isLetter(sentence.charAt(i - 1)) && Character.isLetter(sentence.charAt(i + 1)))) {
                // We're at the start of a word or continuing a word
                if (wordIndex < words.size()) {
                    // Append the entire reversed word
                    result.append(words.get(wordIndex));
                    // Skip ahead by the length of the original word at this position
                    int originalWordLength = getWordLengthAt(sentence, i);
                    i += originalWordLength - 1; // -1 because loop will increment
                    wordIndex++;
                }
            } else {
                // Non-word character (punctuation, space, etc.)
                result.append(ch);
            }
        }

        return result.toString();
    }

    // Helper method to get the length of a word starting at position 'start'
    private int getWordLengthAt(String sentence, int start) {
        int length = 0;
        while (start < sentence.length()) {
            char ch = sentence.charAt(start);
            if (Character.isLetterOrDigit(ch) || (ch == '\'' && start + 1 < sentence.length() && Character.isLetter(sentence.charAt(start + 1)))) {
                length++;
                start++;
            } else {
                break;
            }
        }
        return length;
    }

    /**
     * Space-Optimized Version
     * Instead of storing full word strings, we store only word boundary indices (start, end pairs)
     * This reduces space from O(n) for word strings + O(n) for result to O(k) for indices + O(n) for result
     * where k = number of words (typically k << n)
     */
    public String reverseWordsKeepingPunctuationOptimized(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return sentence;
        }

        // Step 1: Extract word boundaries (start and end indices only)
        java.util.List<int[]> wordBoundaries = new java.util.ArrayList<>();
        int i = 0;

        while (i < sentence.length()) {
            char ch = sentence.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                int start = i;
                // Find the end of the word
                while (i < sentence.length() &&
                        (Character.isLetterOrDigit(sentence.charAt(i)) ||
                                (sentence.charAt(i) == '\'' && i + 1 < sentence.length() &&
                                        Character.isLetter(sentence.charAt(i + 1))))) {
                    i++;
                }
                wordBoundaries.add(new int[]{start, i - 1});
            } else {
                i++;
            }
        }

        // Step 2: Reverse the word boundaries list (in-place)
        int left = 0, right = wordBoundaries.size() - 1;
        while (left < right) {
            int[] temp = wordBoundaries.get(left);
            wordBoundaries.set(left, wordBoundaries.get(right));
            wordBoundaries.set(right, temp);
            left++;
            right--;
        }

        // Step 3: Reconstruct the sentence using reversed word boundaries
        StringBuilder result = new StringBuilder(sentence.length());
        int wordIndex = 0;
        i = 0;

        while (i < sentence.length()) {
            char ch = sentence.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                // Copy the reversed word using boundary indices
                if (wordIndex < wordBoundaries.size()) {
                    int[] boundary = wordBoundaries.get(wordIndex);
                    for (int j = boundary[0]; j <= boundary[1]; j++) {
                        result.append(sentence.charAt(j));
                    }
                    // Skip the original word
                    int originalWordLength = getWordLengthAt(sentence, i);
                    i += originalWordLength;
                    wordIndex++;
                }
            } else {
                // Copy punctuation/space as-is
                result.append(ch);
                i++;
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        ReverseWordsWithPunctuation solution = new ReverseWordsWithPunctuation();

        System.out.println("=== Testing Both Implementations ===\n");

        String[] testCases = {
                "God, is Great",
                "Hello World",
                "Hello, World!",
                "How are you?",
                "It's a beautiful day"
        };

        String[] expected = {
                "Great, is God",
                "World Hello",
                "World, Hello!",
                "you are How?",
                "day beautiful a It's"
        };

        for (int i = 0; i < testCases.length; i++) {
            String input = testCases[i];
            String exp = expected[i];

            String result1 = solution.reverseWordsKeepingPunctuation(input);
            String result2 = solution.reverseWordsKeepingPunctuationOptimized(input);

            boolean pass1 = exp.equals(result1);
            boolean pass2 = exp.equals(result2);

            System.out.println("Test " + (i + 1) + ": \"" + input + "\"");
            System.out.println("  Expected:  \"" + exp + "\"");
            System.out.println("  Original:  \"" + result1 + "\" [" + (pass1 ? "PASS" : "FAIL") + "]");
            System.out.println("  Optimized: \"" + result2 + "\" [" + (pass2 ? "PASS" : "FAIL") + "]");
            System.out.println();
        }

        // Space complexity comparison
        System.out.println("=== Space Complexity Analysis ===");
        System.out.println("Original Method:");
        System.out.println("  - Stores full word strings: O(n) space");
        System.out.println("  - Result StringBuilder: O(n) space");
        System.out.println("  - Total: ~2n space\n");

        System.out.println("Optimized Method:");
        System.out.println("  - Stores only word boundaries (2 integers per word): O(k) space");
        System.out.println("  - Result StringBuilder: O(n) space");
        System.out.println("  - Total: ~n + 2k space (where k = number of words, k << n)");
        System.out.println("  - For \"Hello World\", saves ~50% space on word storage");
    }
}
