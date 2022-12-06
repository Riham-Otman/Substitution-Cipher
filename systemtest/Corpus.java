import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.PrintWriter;

/**
 * The Corpus class contains the body of some bit of text.  It captures any of the characteristics
 * that we will need from that body of text.
 *
 * The class accepts the body of text through a file for now.  The characteristics stored for
 * the body of work are items that will help with decrypting a substitution cipher.  Those characteristics
 * are:
 *   - which letters appear in the body of text
 *   - the frequency of letters in the body of text
 *
 * The class also contains a simple iterator through the content of the body of text.
 *
 * This implementation currently keeps the body of text in an in-memory copy of the file contents.
 */
public class Corpus {
    String basefile = null;             // The filename of the body of text
    LetterFrequencies frequencies = new LetterFrequencies();    // The letter frencies in the body of text

    /* When someone wants to access the content of the file, we use a style similar to an iterator through
       the file content.  Use iteratorFile as context to help us track where we are in the file through
       that iteration.
     */
    int iteratorLine = -1;
    ArrayList<String> fileContents = new ArrayList<>();

    /**
     * Only allow a Corpus object to be created when tied with some text.  That text comes from the contents
     * of a file.
     *
     * We fail the creation of the Corpus object if the file contents are somehow inacccessible.
     * @param filename -- the full path to the file that contains the body of text.
     * @throws IllegalArgumentException -- thrown if the file contents are inaccessible for the corpus.
     */
    public Corpus (String filename ) {
        if ((filename != null) && (filename.length() > 0)) {
            basefile = filename;

            /* Keep track of all the letters in the file for later use. */

            try {
                BufferedReader inFile = new BufferedReader(new FileReader(filename));
                String line = inFile.readLine();
                while (line != null) {
                    fileContents.add( line );
                    frequencies.countStringLetters( line );
                    line = inFile.readLine();
                }
                inFile.close();
            } catch (Exception e) {
                /* Something isn't working with the file.  Let's not pretend that the file is good to go. */

                basefile = null;
                fileContents = new ArrayList<>();
            }
        } else {
            basefile = null;
        }
    }

    /**
     * Provide a way to just store the contents of a string as the whole corpus.  That string can contain
     * multiple lines separated by \n characters.
     * @param newText -- text to replace the corpus with
     * @return -- true if the string content replaced the corpus
     */
    public Boolean replaceContentWithString( String newText ) {
        if (newText != null) {
            /* Use an empty file name to represent that the corpus content comes from a string rather than a file. */
            basefile = "";

            /* Store the information along with any ancillary data that we keep on the corpus. */
            fileContents = new ArrayList<>( Arrays.asList( newText.split( "\\n" ) ));
            for (String line : fileContents) {
                frequencies.countStringLetters( line );
            }

            /* Reset the iterator if one was already in progress. */
            iteratorLine = -1;

            return true;
        }
        return false;
    }

    /**
     * Determine if the corpus object is ready to be used.
     * @return -- true if the object has data to provide, false otherwise.
     */
    public Boolean corpusIsReady() {
        return basefile != null;
    }

    /**
     * Start of an iterator through the body of text.  Expected to be matched by a later endCorpus() call.
     * @return -- true if the body of text is available for the iteration.  False if the iterator shouldn't be continued.
     */
    public Boolean beginCorpus() {
        if (iteratorLine == -1) {
            iteratorLine = 0;
        } else {
            return false;
        }

        return true;
    }

    /**
     * Given an iteration through a body of text that has been stated with beginCorpus(), retrieve the next
     * full line (to a carriage return) from the corpus.
     * @return -- the next line of the corpus.  Return null in the case of an error or when we reach the end of the body of text.
     */
    public String nextCorpusLine() {
        if (iteratorLine >= 0) {
            if (iteratorLine < fileContents.size()) {
                iteratorLine++;
                return fileContents.get(iteratorLine-1);
            }
        }
        return null;
    }

    /**
     * Given an iteration through a body of text that has been started with beginCorpus(), signal to the Corpus
     * object that you are finished with the iteration.
     */
    public void endCorpus() {
        iteratorLine = -1;
    }

    /**
     * Return a set of all the alphabetic characters (lower case only) that appear in the corpus that is stored
     * in this object
     * @return -- a HashSet of all letters (lower case of everything) in the current corpus.
     */
    public HashSet<Character> lettersInCorpus() {
        HashSet<Character> letters = new HashSet<>();

        /* Assume that the frequencies accumulated reflect all letters in the corpus so use that information to get all letters. */
        ArrayList<LetterCount> lettersUsed = frequencies.getSortedCounts();
        for ( LetterCount letterCount : lettersUsed) {
            if (letterCount.count > 0) {
                letters.add(letterCount.letter);
            }
        }
        return letters;
    }
}
