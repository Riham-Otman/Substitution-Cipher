import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;

class CorpusTest {

    @Test
    void nullFilenameCorpus() {
        Corpus body = new Corpus(null);
        assertFalse( body.corpusIsReady(), "Corpus with null key still worked");
    }

    @Test
    void noFilename() {
        Corpus info = new Corpus( null );
        assertFalse( info.corpusIsReady(), "null filename for corpus" );
    }

    @Test
    void emptyFilename() {
        Corpus info = new Corpus( "" );
        assertFalse( info.corpusIsReady(), "empty filename for corpus" );
    }

    @Test
    void nonExistentFile() {
        Corpus info = new Corpus( "testfiles/nofile.txt" );
        assertFalse( info.corpusIsReady(), "non-existent filename for corpus" );
    }

    @Test
    void emptyFile() {
        Corpus info = new Corpus( "testfiles/emptyfile.txt" );
        assertTrue( info.corpusIsReady(), "null filename for corpus" );

        assertTrue( info.beginCorpus(), "begin empty file" );
        assertNull( info.nextCorpusLine(), "get line from empty file");
        info.endCorpus();
    }

    @Test
    void singleCharacterFile() {
        Corpus info = new Corpus( "testfiles/singleCharacter.txt" );
        assertTrue( info.corpusIsReady(), "single character file for corpus" );

        assertTrue( info.beginCorpus(), "begin single character file" );
        assertEquals( "a", info.nextCorpusLine(), "get line from single character file file");
        assertNull( info.nextCorpusLine(), "get past last line file");
        info.endCorpus();
    }

    @Test
    void singleLineFile() {
        Corpus info = new Corpus( "testfiles/singleLine.txt" );
        assertTrue( info.corpusIsReady(), "single line file for corpus" );

        assertTrue( info.beginCorpus(), "begin line character file" );
        String line = info.nextCorpusLine();
        assertEquals( "the quick brown fox jumps over the lazy dog", line, "get line from single line file file");
        assertNull( info.nextCorpusLine(), "get past last line file");
        info.endCorpus();
    }

    @Test
    void multiLineFile() {
        Corpus info = new Corpus( "testfiles/multiLineSimpleEncode.txt" );
        assertTrue( info.corpusIsReady(), "multi line file for corpus" );

        assertTrue( info.beginCorpus(), "begin multi character file" );
        assertEquals( "abc", info.nextCorpusLine(), "get first line from multi line file file");
        assertEquals( "def", info.nextCorpusLine(), "get second line from multi line file file");
        assertEquals( "ghi", info.nextCorpusLine(), "get third line from multi line file file");
        assertNull( info.nextCorpusLine(), "get past last line file");
        info.endCorpus();
    }

    @Test
    void closeNoOpen() {
        Corpus info = new Corpus( "testfiles/multiLineSimpleEncode.txt" );
        assertTrue( info.corpusIsReady(), "multi line file for corpus" );

        /* Just make sure we don't crash. */

        info.endCorpus();
    }

    @Test
    void readEarly() {
        Corpus info = new Corpus( "testfiles/multiLineSimpleEncode.txt" );
        assertTrue( info.corpusIsReady(), "multi line file for corpus" );

        assertNull(  info.nextCorpusLine(), "get line without opening file");
    }

    @Test
    void openTwice() {
        Corpus info = new Corpus( "testfiles/multiLineSimpleEncode.txt" );
        assertTrue( info.corpusIsReady(), "multi line file for corpus" );

        assertTrue( info.beginCorpus(), "begin multi character file" );
        assertFalse( info.beginCorpus(), "begin multi character file a second time" );
        info.endCorpus();
    }

    @Test
    void emptyLetters() {
        Corpus info = new Corpus( "testfiles/emptyfile.txt" );
        assertTrue( info.corpusIsReady(), "empty file for corpus" );
    }

    @Test
    void oneLetter() {
        Corpus info = new Corpus( "testfiles/singleCharacter.txt" );
        assertTrue( info.corpusIsReady(), "single character file for corpus" );
    }

    @Test
    void allLetters() {
        Corpus info = new Corpus( "testfiles/singleLine.txt" );
        assertTrue( info.corpusIsReady(), "single line file for corpus" );
    }

    @Test
    void cases() {
        Corpus info = new Corpus( "testfiles/multiLineSimpleEncode.txt" );
        assertTrue( info.corpusIsReady(), "multi line file for corpus" );
    }

}