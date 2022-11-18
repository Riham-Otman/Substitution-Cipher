import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class decodeTextTest {
    @Test
    void decodeSingleChar() {
        HashMap<Character, Character> key = new HashMap<>();
        key.put( 'b', 'a');

        SubstitutionKey cipher = new SubstitutionKey( key );

        assertEquals( "a", cipher.encryptText( "b" ), "decode single character file");
    }

    @Test
    void decodeLongLine() {
        HashMap<Character, Character> key = new HashMap<>();
        key.put( 'c', 'b');

        SubstitutionKey cipher = new SubstitutionKey( key );

        assertEquals( "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
                cipher.encryptText( "ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc" ), "decode long line file");
    }

    @Test
    void decodeFullRange() {
        HashMap<Character, Character> key = new HashMap<>();
        key.put( 'a', 'x');
        key.put( 'm', 'k');
        key.put( 'z', 'w');

        SubstitutionKey cipher = new SubstitutionKey( key );

        String decoded = cipher.encryptText("aaa\nzzz\nmmm\namz");
        assertEquals( "xxx\nwww\nkkk\nxkw", decoded, "decode range of characters");
    }

    @Test
    void multiLineFile() {
        HashMap<Character, Character> key = new HashMap<>();
        key.put( 'a', 'b' );
        key.put( 'b', 'c' );
        key.put( 'c', 'd' );
        key.put( 'd', 'e' );
        key.put( 'e', 'f' );
        key.put( 'f', 'g' );
        key.put( 'g', 'h' );
        key.put( 'h', 'i' );
        key.put( 'i', 'j' );
        key.put( 'j', 'k' );
        key.put( 'k', 'l' );
        key.put( 'l', 'm' );
        key.put( 'm', 'n' );
        key.put( 'n', 'o' );
        key.put( 'o', 'p' );
        key.put( 'p', 'q' );
        key.put( 'q', 'r' );
        key.put( 'r', 's' );
        key.put( 's', 't' );
        key.put( 't', 'u' );
        key.put( 'u', 'v' );
        key.put( 'v', 'w' );
        key.put( 'w', 'x' );
        key.put( 'x', 'y' );
        key.put( 'y', 'z' );
        key.put( 'z', 'a' );
        SubstitutionKey cipher = new SubstitutionKey( key );
        String decoded = cipher.encryptText( "abc\ndef\nghi\n" );
        assertEquals( "bcd\nefg\nhij\n", decoded, "decode multi-line ciphertext" );
    }

    @Test
    void punctuation() {
        HashMap<Character, Character> key = new HashMap<>();
        key.put( 'a', 'b' );
        key.put( 'b', 'c' );
        key.put( 'c', 'd' );
        key.put( 'd', 'e' );
        key.put( 'e', 'f' );
        key.put( 'f', 'g' );
        key.put( 'g', 'h' );
        key.put( 'h', 'i' );
        key.put( 'i', 'j' );
        key.put( 'j', 'k' );
        key.put( 'k', 'l' );
        key.put( 'l', 'm' );
        key.put( 'm', 'n' );
        key.put( 'n', 'o' );
        key.put( 'o', 'p' );
        key.put( 'p', 'q' );
        key.put( 'q', 'r' );
        key.put( 'r', 's' );
        key.put( 's', 't' );
        key.put( 't', 'u' );
        key.put( 'u', 'v' );
        key.put( 'v', 'w' );
        key.put( 'w', 'x' );
        key.put( 'x', 'y' );
        key.put( 'y', 'z' );
        key.put( 'z', 'a' );
        SubstitutionKey cipher = new SubstitutionKey( key );
        String decoded = cipher.encryptText("ab.\"cde\"fgh;i34 jk_lmn!@op<qrs>tu?vwxyz!");
        assertEquals( "bc.\"def\"ghi;j34 kl_mno!@pq<rst>uv?wxyza!", decoded, "decode punctuation ciphertext" );
    }

    @Test
    void multiCase() {
        HashMap<Character, Character> key = new HashMap<>();
        key.put( 'a', 'b' );
        key.put( 'b', 'c' );
        key.put( 'c', 'd' );
        key.put( 'd', 'e' );
        key.put( 'e', 'f' );
        key.put( 'f', 'g' );
        key.put( 'g', 'h' );
        key.put( 'h', 'i' );
        key.put( 'i', 'j' );
        key.put( 'j', 'k' );
        key.put( 'k', 'l' );
        key.put( 'l', 'm' );
        key.put( 'm', 'n' );
        key.put( 'n', 'o' );
        key.put( 'o', 'p' );
        key.put( 'p', 'q' );
        key.put( 'q', 'r' );
        key.put( 'r', 's' );
        key.put( 's', 't' );
        key.put( 't', 'u' );
        key.put( 'u', 'v' );
        key.put( 'v', 'w' );
        key.put( 'w', 'x' );
        key.put( 'x', 'y' );
        key.put( 'y', 'z' );
        key.put( 'z', 'a' );
        SubstitutionKey cipher = new SubstitutionKey( key );
        String decoded = cipher.encryptText( "abcDEFghi\nABCdefGHI\n");
        assertEquals( "bcdEFGhij\nBCDefgHIJ\n", decoded, "decode punctuation ciphertext" );
    }

    @Test
    void keyFromManual() {
        SubstitutionKey cipher = new SubstitutionKey(  );
        assertTrue( cipher.setKey( 'a', 'b' ) );
        assertTrue( cipher.setKey( 'm', 'n' ) );
        assertTrue( cipher.setKey( 'z', 'y' ) );

        String decoded = cipher.encryptText( "aaa\nzzz\nmmm\namz");
        assertEquals( "bbb\nyyy\nnnn\nbny", decoded, "decode manual key ciphertext" );
    }

    @Test
    void keyFromManualCase() {
        SubstitutionKey cipher = new SubstitutionKey(  );
        assertTrue( cipher.setKey( 'A', 'B' ) );
        assertTrue( cipher.setKey( 'M', 'N' ) );
        assertTrue( cipher.setKey( 'Z', 'Y' ) );

        String decoded = cipher.encryptText( "aaa\nzzz\nmmm\namz");
        assertEquals( "bbb\nyyy\nnnn\nbny", decoded, "decode manual key ciphertext" );
    }
}