import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.io.PrintWriter;

public class SubstitutionCipher {
    private SubstitutionKey encryptKey = null;
    private Corpus plain = null;
    private Corpus cipher = null;
    private String eText = null;
    private String dText = null;

    /**
     * Constructor to create a completely empty cipher
     */
    public SubstitutionCipher() {
        encryptKey = new SubstitutionKey();
    }

    /**
     * Constructor to create a cipher for which we're given an existing mapping of characters.
     * This constructor is most useful when you're debugging so that you can establish a correct
     * key to see if the rest of the decoding is working
     * @param name - a name that identifies this key set
     * @param key - the encryption key structure from plaintext to ciphertext
     */
    public SubstitutionCipher( String name, HashMap<Character, Character> key ) {

        /* Make sure that we have quality parameter values to start */
        if ((name != null) && (name.length() > 0) && (key != null)) {
            encryptKey = new SubstitutionKey( key );

            /* The name isn't being used, so I have no purpose in storing it. */
        } else {
            encryptKey = new SubstitutionKey();
        }
    }

    /**
     * Provide the plaintext to encrypt to the object.  The plaintext is the full content
     * of the given string.
     * @param theText - the string to use as plaintext
     * @return - true if the text can be used as plaintext, false otherwise.
     */
    public Boolean plaintext( String theText ) {
        Boolean plaintextAccepted = false;

        /* Make sure that we have quality parameter values to start */
        if ((theText != null) && (theText.length() > 0)) {
            /* If we already have a plaintext in the object, replace it. */

            plain = new Corpus( "" );
            plaintextAccepted = plain.replaceContentWithString( theText );
        }

        return plaintextAccepted;
    }

    /**
     * Provide the ciphertext to decrypt to the object.  The ciphertext is the full content
     * of the given string.
     * @param theText - text to use as ciphertext
     * @return - true if the text can be used as ciphertext, false otherwise.
     */
    public Boolean ciphertext( String theText ) {
        Boolean ciphertextAccepted = false;

        /* Make sure that we have quality parameter values to start */
        if ((theText != null) && (theText.length() > 0)) {
            /* If we already have a ciphertext in the object, replace it. */

            cipher = new Corpus( "" );
            ciphertextAccepted = cipher.replaceContentWithString( theText );
        }

        return ciphertextAccepted;
    }

    /**
     * Given plaintext in this object, use whatever key we have to encode the plaintext.
     * @return - return true if the plaintext was properly stored as ciphertext in this object.
     */
    public Boolean encodeText() {
        String encoded = null;
        Set<Character> encryptLetters = encryptKey.getKey().keySet();

        /* Get the letters in the plaintext. */

        Set<Character> plainLetters = null;
        if (plain != null) {
            plainLetters = plain.lettersInCorpus();
        } else {
            plainLetters = new HashSet<>();
        }

        /* The cipher Corpus object gives us an iterator that looks similar to that of a file. */

        if (keyIsValid() && (plain != null) && encryptLetters.containsAll( plainLetters ) && plain.beginCorpus()) {
            StringBuilder inProgress = new StringBuilder();

            int charRead;
            String encodedLine;

            /* Encode each line, one at a time.  The corpus stream should end with a null value as the next line. */

            String line  = plain.nextCorpusLine();
            while (line != null) {
                encodedLine = encryptKey.encryptText( line );
                if (encodedLine != null) {
                    /* Add the encoded line.  Since we're processing one line at a time, assume that all our
                       entries properly end with a carriage return.
                     */
                    inProgress.append( encodedLine );
                    inProgress.append( "\n" );
                } else {
                    /* Something happened in the encryption of a viable line.  Return with an error condition. */
                    return null;
                }
                line = plain.nextCorpusLine();
            }
            plain.endCorpus();

            eText = inProgress.toString();
            if(eText.length()>0){
                return true;

            }

        }
        return false;
    }

    /**
     * Given ciphertext in this object, use whatever key we have to decode the ciphertext.
     * @return - return true if the corresponding plaintext has been stored in this object as plaintext.
     */
    public Boolean decodeText() {
        String decoded = null;

        SubstitutionKey decodeKey = encryptKey.invertKey();
        Set<Character> encryptLetters = decodeKey.getKey().keySet();

        /* Get the letters in the ciphertext. */

        Set<Character> cipherLetters = null;
        if (cipher != null) {
            cipherLetters = cipher.lettersInCorpus();
        } else {
            cipherLetters = new HashSet<>();
        }

        /* The cipher Corpus object gives us an iterator that looks similar to that of a file. */

        if (keyIsValid() && (cipher != null) && encryptLetters.containsAll( cipherLetters ) && cipher.beginCorpus()) {
            StringBuilder inProgress = new StringBuilder();

            int charRead;
            String decodedLine;

            /* Decode each line, one at a time.  The corpus stream should end with a null value as the next line. */

            String line  = cipher.nextCorpusLine();
            while (line != null) {
                decodedLine = decodeKey.encryptText( line );
                if (decodedLine != null) {
                    /* Add the decoded line.  Since we're processing one line at a time, assume that all our
                       entries properly end with a carriage return.
                     */
                    inProgress.append( decodedLine );
                    inProgress.append( "\n" );
                } else {
                    /* Something happened in the decryption of a viable line.  Return with an error condition. */
                    return null;
                }
                line = cipher.nextCorpusLine();
            }
            cipher.endCorpus();

            dText = inProgress.toString();
            if(dText.length()>0){
                return true;

            }

        }
        return false;
    }

    /**
     * Manually create or modify an encryption key.  The method associates one plaintext
     * character with one ciphertext character.  Building a full key would involve calling
     * this method once for each character to be encoded/decoded.
     * @param plaintextChar - the plaintext character to encode
     * @param ciphertextChar - the character to use as the encoding of the plaintext character
     * @return - true if the character could be added as part of the key, false otherwise
     */
    public Boolean setDecodeLetter( Character plaintextChar, Character ciphertextChar ) {
        Boolean changeAccepted = false;

        /* Make sure that we have quality parameter values to start */
        if ((plaintextChar != null) && (Character.isLetter(plaintextChar)) &&
                (ciphertextChar != null) && (Character.isLetter(ciphertextChar))) {
            changeAccepted = encryptKey.setKey( plaintextChar, ciphertextChar );
        }

        return changeAccepted;
    }

    /**
     * Obtain a copy of the encryption key currently used in this object.  The Map has
     * the plaintxt character as the key and the encoded character as the Map value.
     * @return - a Map of the encryption key.  Return null in the case of error.
     */
    public HashMap<Character, Character> getKey() {
        return encryptKey.getKey();
    }

    /**
     * Determine whether or not the encryption key currently in the object is a valid one for
     * the given ciphertext.  To that end, it must cover all the characters of the ciphertext
     * and not have duplicate encodings / decodings of characters.
     * @return - true if the key is a viable encryption / decryption key; false otherwise.
     */
    public Boolean keyIsValid() {
        /* Start by ensuring that we aren't using some letter more than once in each part of the key. */

        HashSet<Character> valuesUsed = new HashSet<>(encryptKey.getKey().values());
        Boolean valid = encryptKey.getKey().keySet().size() == valuesUsed.size();

        return valid;
    }

    /**
     * Send the stored plaintext to some output device.  That output device can be a PrintWriter
     * wrapping around a file, the screen, a string, or whatever else you want.
     * @param output -- the output device.
     */
    public void extractPlaintext( PrintWriter output ) {
        if (dText== null || output == null){
            System.out.println("Bad argument provided");
            return;
        }
        output.print(dText);
    }

    /**
     * Send the stored ciphertext to some output device.  That output device can be a PrintWriter
     * wrapping around a file, the screen, a string, or whatever else you want.
     * @param output -- the output device.
     */
    public void extractCiphertext( PrintWriter output ) {
        if (eText == null || output == null){
            System.out.println("Bad argument provided");
            return;
        }
        output.print(eText);

    }
}
