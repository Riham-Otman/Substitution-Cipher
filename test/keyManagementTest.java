import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class keyManagementTest {
    @Test
    void getKeyNoChars() {
        SubstitutionKey cipher = new SubstitutionKey( );

        HashMap<Character, Character> key ;
        key = cipher.getKey();

        assertNotNull( key , "empty key on getKey returning nothing" );
        Set<Character> keyset;
        keyset = key.keySet();

        assertEquals( 0, keyset.size(), "empty key returning characters" );
    }

    @Test
    void validKeyRange() {
        SubstitutionKey cipher = new SubstitutionKey( );
    }

    @Test
    void getValidKey() {
        HashMap<Character, Character> key = new HashMap<>();
        key.put( 'b', 'c');
        key.put( 'c', 'd');

        SubstitutionKey cipher = new SubstitutionKey( key );
        HashMap<Character, Character> retrieved = cipher.getKey();

        assertNotNull( retrieved, "retrieve good key" );

        assertEquals( 'c', retrieved.get( 'b' ), "get character" );
        assertEquals( 'd', retrieved.get( 'c' ), "get character" );
    }


    @Test
    void getInvalidKey() {
        HashMap<Character, Character> key = new HashMap<>();
        key.put( 'b', 'c');
        key.put( 'c', 'c');

        SubstitutionKey cipher = new SubstitutionKey( key );
        HashMap<Character, Character> retrieved = cipher.getKey();

        assertNotNull( retrieved, "retrieve good key" );

        assertEquals( 'c', retrieved.get( 'b' ), "get character" );
        assertEquals( 'c', retrieved.get( 'c' ), "get character" );
    }

    @Test
    void invertKey() {
        HashMap<Character, Character> key = new HashMap<>();
        key.put( 'b', 'c');
        key.put( 'c', 'd');

        SubstitutionKey cipher = new SubstitutionKey( key );
        SubstitutionKey invert = cipher.invertKey();
        HashMap<Character, Character> retrieved = invert.getKey();

        assertNotNull( retrieved, "retrieve good key" );

        assertEquals( 'b', retrieved.get( 'c' ), "get character" );
        assertEquals( 'c', retrieved.get( 'd' ), "get character" );
    }

    @Test
    void invertEmptyKey() {
        HashMap<Character, Character> key = new HashMap<>();

        SubstitutionKey cipher = new SubstitutionKey( key );
        SubstitutionKey invert = cipher.invertKey();
        HashMap<Character, Character> retrieved = invert.getKey();

        assertNotNull( retrieved, "retrieve good key" );
    }

}