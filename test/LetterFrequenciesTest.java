import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LetterFrequenciesTest {

    @Test
    void getSortedCounts() {
        LetterFrequencies freq = new LetterFrequencies();

        /* Sort something empty. */

        List<LetterCount>  sorted = freq.getSortedCounts();
        assertNotNull( sorted, "null sorted list" );

        /* Should be in alphabetical order. */

        for (int i = 0; i < 26; i++) {
            Character expected = (char)((int)'a' + i);
            Character let = sorted.get(i).letter;
            assertEquals( expected, let);
        }
    }
    @Test
    void sortSomeTies() {
        LetterFrequencies freq = new LetterFrequencies();

        freq.countLetter( 'b' );
        freq.countLetter( 'b' );
        freq.countLetter( 'b' );
        freq.countLetter( 'b' );
        freq.countLetter( 'c' );
        freq.countLetter( 'c' );
        freq.countLetter( 'c' );
        freq.countLetter( 'c' );
        freq.countLetter( 'f' );
        freq.countLetter( 'f' );
        freq.countLetter( 'e' );
        freq.countLetter( 'e' );

        List<LetterCount>  sorted = freq.getSortedCounts();
        assertNotNull( sorted, "null sorted list" );

        assertEquals( 'b', sorted.get(0).letter);
        assertEquals( 'c', sorted.get(1).letter);
        assertEquals( 'e', sorted.get(2).letter);
        assertEquals( 'f', sorted.get(3).letter);
        assertEquals( 'a', sorted.get(4).letter);
    }

    @Test
    void sortDistinct() {
        LetterFrequencies freq = new LetterFrequencies();

        freq.countLetter( 'b' );
        freq.countLetter( 'b' );
        freq.countLetter( 'b' );
        freq.countLetter( 'c' );
        freq.countLetter( 'c' );
        freq.countLetter( 'c' );
        freq.countLetter( 'c' );
        freq.countLetter( 'c' );
        freq.countLetter( 'c' );
        freq.countLetter( 'f' );
        freq.countLetter( 'f' );
        freq.countLetter( 'e' );
        freq.countLetter( 'e' );
        freq.countLetter( 'e' );
        freq.countLetter( 'e' );

        List<LetterCount>  sorted = freq.getSortedCounts();
        assertNotNull( sorted, "null sorted list" );

        assertEquals( 'c', sorted.get(0).letter);
        assertEquals( 'e', sorted.get(1).letter);
        assertEquals( 'b', sorted.get(2).letter);
        assertEquals( 'f', sorted.get(3).letter);
        assertEquals( 'a', sorted.get(4).letter);
    }

    @Test
    void sortRange() {
        LetterFrequencies freq = new LetterFrequencies();

        freq.countLetter( 'a' );
        freq.countLetter( 'z' );

        List<LetterCount>  sorted = freq.getSortedCounts();
        assertNotNull( sorted, "null sorted list" );

        assertEquals( 'a', sorted.get(0).letter);
        assertEquals( 'z', sorted.get(1).letter);
        assertEquals( 'b', sorted.get(2).letter);
        assertEquals( 'c', sorted.get(3).letter);
        assertEquals( 'd', sorted.get(4).letter);
    }

}