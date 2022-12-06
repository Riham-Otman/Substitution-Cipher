/**
 * The LetterCount class is a basic class to more about key-value pairs of a letter
 * along with the frequency of the letter.
 *
 * We use a Double for the frequency so the object can either store the number of occurrences
 * of the character or can store the percentage of times this character appears.
 */
public class LetterCount {
     Character letter = null;
     Double count = 0.0;

    /** Constructor for the class to initialize with a letter and a value.  We won't have
     * objects of the class that aren't associated with a character.
     * @param ch -- the character to store in the object
     * @param value -- the frequency of use of the character.
     */
    public LetterCount( Character ch, Double value ) {
        letter = ch;
        count = value;
    }
}
