import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

/**
 * The LetterFrequencies class stores a table of each letter and the number of occurrences of the letter
 * in some corpus.
 *
 * By design, the table will only store the lower case version of any letter and will not store non-letter
 * characters.
 */
public class LetterFrequencies {
    HashMap<Character, Integer> frequencies = null;

    /**
     * Constructor for the class.  Since we know that we're only storing lower case letters,
     * we automatically populate the table of counts for each of the 26 possible letters rather than wait
     * for letters to be added.
     */
    public LetterFrequencies() {
        Character letter;

        /* Build an empty frequency map with every letter that I might consider. */

        frequencies = new HashMap<>();
        for (letter = 'a'; letter <= 'z'; letter++) {
            frequencies.put( letter, 0 );
        }
    }

    /**
     * Indicate that one particular letter has now been seen in the corpus and should have its
     * occurrence count incremented by 1.
     * @param letter -- The letter seen in the corpus whose count should increase
     * @return -- true if the character sent could be updated, false if no count was incremented.
     */
    public Boolean countLetter( Character letter ) {
        if (letter != null) {
            Character lowerLetter = Character.toLowerCase(letter);
            if (Character.isLetter(lowerLetter)) {
                frequencies.put(lowerLetter, frequencies.get(lowerLetter) + 1);
                return true;
            }
        }
        return false;
    }

    public Boolean countStringLetters( String lineOfText ) {
        if (lineOfText != null) {
            for (int i = 0; i < lineOfText.length(); i++) {
                countLetter(lineOfText.charAt(i));
            }
            return true;
        }
        return false;
    }

    /**
     * Export the set of characters and their counts that are currently stored in the frequency table.
     * The list that is returned is sorted in decreasing order of occurrence for the letter.  So, the
     * most frequent letter appears at index 0, the next most frequent letter appears at index 1, and so
     * on.
     *
     * If two letters have the same frequency then they are sorted in alphabetical order.
     * @return -- the sorted list of characters along with their frequencies.
     */
    public ArrayList< LetterCount > getSortedCounts() {
        ArrayList< LetterCount > letters = new ArrayList<>();

        /* Dump the frequency list into the returned list. */

        Set<Character> keys = frequencies.keySet();
        for (Character ch : keys) {
            Double count = (double) frequencies.get(ch);
            letters.add( new LetterCount( ch, count));
        }

        /* Sort with a basic selection sort.  We'll have a small list only, so efficiency isn't a big issue. */

        int biggest = 0;
        for (int i = 0; i < letters.size()-1; i++) {
            biggest = i;
            for (int j = i+1; j < letters.size(); j++) {
                if ((letters.get(j).count.intValue() > letters.get(biggest).count.intValue()) ||
                        ((letters.get(j).count.intValue() == letters.get(biggest).count.intValue()) &&
                                (letters.get(j).letter < letters.get(biggest).letter))) {
                    biggest = j;
                }
            }

            if (i != biggest) {
                /* Swap the entries */
                LetterCount swap = letters.get(i);
                letters.set( i, letters.get(biggest) );
                letters.set( biggest, swap );
            }
        }

        return letters;
    }
}
