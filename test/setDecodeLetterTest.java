import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

class setDecodeLetterTest {
    @Test
    void letterRange() {
        SubstitutionKey cipher = new SubstitutionKey();

        assertTrue(cipher.setKey('a', 'c'), "set decode first plain letter");
        assertTrue(cipher.setKey('z', 'y'), "set decode last plain letter");
        assertTrue(cipher.setKey('b', 'a'), "set decode first cipher letter");
        assertTrue(cipher.setKey('d', 'z'), "set decode last cipher letter");

        Map<Character, Character> key = cipher.getKey();

        assertNotNull(key, "key not created with setDecodeLetter");

        /* Verify the key */

        assertEquals('c', key.get('a'), "retrieve first plain letter ");
        assertEquals('y', key.get('z'), "retrieve last plain letter ");
        assertEquals('a', key.get('b'), "retrieve first cipher letter ");
        assertEquals('z', key.get('d'), "retrieve last cipher letter ");

    }

    @Test
    void settingKeys1() {
        SubstitutionKey cipher = new SubstitutionKey();

        assertTrue(cipher.setKey('m', 'n'), "letter is fine");
        Map<Character, Character> key = cipher.getKey();
        assertNotNull(key, "key not created with setDecodeLetter");
        assertEquals('n', key.get('m'), "retrieve good letter letter ");
    }

    @Test
    void replacePlaintext() {
        SubstitutionKey cipher = new SubstitutionKey();

        assertTrue(cipher.setKey('m', 'n'), "letter is fine");
        assertTrue(cipher.setKey('m', 'p'), "letter is fine");
        Map<Character, Character> key = cipher.getKey();
        assertNotNull(key, "key not created with setDecodeLetter");
        assertEquals('p', key.get('m'), "retrieve good letter letter ");
    }

    @Test
    void badPlaintext() {
        SubstitutionKey cipher = new SubstitutionKey();

        assertFalse(cipher.setKey('.', 'n'), "plain letter is bad");
    }

    @Test
    void badCiphertext() {
        SubstitutionKey cipher = new SubstitutionKey();

        assertFalse(cipher.setKey('m', '.'), "cipher letter is bad");
    }

    @Test
    void reuseCipherLetter() {
        SubstitutionKey cipher = new SubstitutionKey();

        assertTrue(cipher.setKey('m', 'b'), "letter is fine");
        assertTrue(cipher.setKey('n', 'b'), "letter is fine");
        Map<Character, Character> key = cipher.getKey();
        assertNotNull(key, "key not created with setDecodeLetter");
        assertEquals('b', key.get('m'), "retrieve first letter ");
        assertEquals('b', key.get('n'), "retrieve second letter ");
    }

    @Test
    void upperCases() {
        SubstitutionKey cipher = new SubstitutionKey();

        assertTrue(cipher.setKey('M', 'B'), "letter case is fine");
    }
}