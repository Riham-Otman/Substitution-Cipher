import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ConstructorTest {
    @Test
    void emptyKey() {
        HashMap<Character, Character> key = new HashMap<>();
        SubstitutionKey cipher = new SubstitutionKey( key );

        assertEquals( 0, cipher.getKey().size(), "Constructor with empty map for key");
    }

    @Test
    void viableKey() {
        HashMap<Character, Character> key = new HashMap<>();
        key.put( 'a', 'a' );
        key.put( 'b', 'b' );
        key.put( 'c', 'c' );
        key.put( 'd', 'd' );
        key.put( 'e', 'e' );
        key.put( 'f', 'f' );
        key.put( 'g', 'g' );
        key.put( 'h', 'h' );
        key.put( 'i', 'i' );
        key.put( 'j', 'j' );
        key.put( 'k', 'k' );
        key.put( 'l', 'l' );
        key.put( 'm', 'm' );
        key.put( 'n', 'n' );
        key.put( 'o', 'o' );
        key.put( 'p', 'p' );
        key.put( 'q', 'q' );
        key.put( 'r', 'r' );
        key.put( 's', 's' );
        key.put( 't', 't' );
        key.put( 'u', 'u' );
        key.put( 'v', 'v' );
        key.put( 'w', 'w' );
        key.put( 'x', 'x' );
        key.put( 'y', 'y' );
        key.put( 'z', 'z' );
        SubstitutionKey cipher = new SubstitutionKey( key );

        assertNotNull( cipher.getKey(), "Constructor with viable map for key");
    }

    @Test
    void duplicateCipher() {
        HashMap<Character, Character> key = new HashMap<>();
        key.put( 'a', 'a' );
        key.put( 'b', 'b' );
        key.put( 'c', 'c' );
        key.put( 'd', 'e' );
        key.put( 'e', 'e' );
        key.put( 'f', 'f' );
        key.put( 'g', 'g' );
        key.put( 'h', 'h' );
        key.put( 'i', 'i' );
        key.put( 'j', 'j' );
        key.put( 'k', 'k' );
        key.put( 'l', 'l' );
        key.put( 'm', 'm' );
        key.put( 'n', 'n' );
        key.put( 'o', 'o' );
        key.put( 'p', 'p' );
        key.put( 'q', 'q' );
        key.put( 'r', 'r' );
        key.put( 's', 's' );
        key.put( 't', 't' );
        key.put( 'u', 'u' );
        key.put( 'v', 'v' );
        key.put( 'w', 'w' );
        key.put( 'x', 'x' );
        key.put( 'y', 'y' );
        key.put( 'z', 'z' );
        SubstitutionKey cipher = new SubstitutionKey( key );

        assertNotNull( cipher.getKey(), "Constructor with duplicate cipher letter in key");
    }

}