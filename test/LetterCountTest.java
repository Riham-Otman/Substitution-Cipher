import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LetterCountTest {

    @Test
    void getLetter() {
        LetterCount letter = new LetterCount( 'a', 200.0 );

        assertEquals( 'a', letter.letter, "retrieve a letter stored in the object");
    }

    @Test
    void getCount() {
        LetterCount letter = new LetterCount( 'a', 200.0 );

        assertEquals( 200.0, letter.count, "retrieve a count stored in the object");
    }
}