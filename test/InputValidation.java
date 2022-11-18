import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import java.util.HashMap;

class InputValidation {

    @Test
     void nullKeyConstructor() {
        SubstitutionKey cipher = new SubstitutionKey( null );

        assertEquals( 0, cipher.getKey().size(), "Constructor with null for key");
    }

    @Test
    void nullPlaintextChar() {
        SubstitutionKey cipher = new SubstitutionKey( );

        assertFalse( cipher.setKey( null, 'a'), "set decode null plaintext");
    }

    @Test
    void nullCiphertextChar() {
        SubstitutionKey cipher = new SubstitutionKey( );

        assertFalse( cipher.setKey( 'a', null), "set decode null ciphertext");
    }

    @Test
    void badPlaintextChar() {
        SubstitutionKey cipher = new SubstitutionKey( );

        assertFalse( cipher.setKey( '?', 'a'), "set decode bad plaintext");
    }

    @Test
    void badCiphertextChar() {
        SubstitutionKey cipher = new SubstitutionKey( );

        assertFalse( cipher.setKey( 'a', '!'), "set decode bad ciphertext");
    }

    @Test
    void countLetter() {
        LetterFrequencies freq = new LetterFrequencies( );

        assertFalse( freq.countLetter( null ), "count frequencies with null letter" );
    }

}