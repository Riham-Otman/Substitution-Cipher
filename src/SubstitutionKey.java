import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

/**
 * The SubstitutionKey class manages an encryption key for a substitution cipher.
 *
 * A substitution cipher is one in which we do encryption by creating a mapping from each plaintext
 * letter to a ciphertext letter.  Then, each occurrence of the plaintext letter in the text we are encrypting
 * is replaced with its corresponding ciphertext letter.
 *
 * The key that is stored is that of the encryption, so the key is the plaintext character and the
 * value is the ciphertext character.
 */
public class SubstitutionKey {
    /* The mapping stored is that of the encryption key. */
    HashMap<Character, Character> key = new HashMap<>();

    /**
     * Basic constructor for the class.  Only use the default encryption key Map.
     */
    public SubstitutionKey() {
    }

    /**
     * Constructor for the class where the user provides the encryption key directly to the object.
     * @param inKey -- the mapping of plaintext characters to ciphertext characters as the encryption key.
     */
    public SubstitutionKey(HashMap< Character, Character> inKey ) {
        if (inKey != null) {
        /* Copy the Map over to my own key myself rather than cloning or using the constructor
           so that I can ensure that the keys and values are stored in lower case.
         */
            Set<Character> plainChar = inKey.keySet();
            for (Character c : plainChar) {
                key.put(Character.toLowerCase(c), Character.toLowerCase(inKey.get(c)));
            }
        }
    }

    /**
     * Return a copy of the current encryption key to the caller.
     * @return -- the stored key with the plaintext character as the Map key and the ciphertext character as the Map value.
     */
    public HashMap<Character, Character> getKey() {
        return new HashMap( key );
    }

    /**
     * Allow a user to manually build or modify a key.  This method sets one key-value pair for the mapping.
     * It does not ensure that the mapping created is one that can be used for encrypting or decrypting; it makes
     * that choice because the method may be used on an existing key to modify it and the modifications to the
     * key may have the key in an unusable state for a small period of time.
     *
     * If a plaintext letter already exists in the encryption key then its value in the key is updated.
     *
     * The map is only meant to store letters.  It will refuse to store non-letter characters.
     * @param plainChar -- the plaintext character to add or update in the encryption key
     * @param cipherChar -- the ciphertext character to associate with the plaintext character in the encryption key
     * @return -- true if the key-value pair were stored / updated in the encryption key and false otherwise.
     */
    public Boolean setKey( Character plainChar, Character cipherChar ) {
        Boolean stored = false;

        /* Only store letters in this map.  Store as lower case characters. */

        if ((plainChar != null) && (cipherChar != null) && Character.isLetter( plainChar ) && Character.isLetter( cipherChar ) ) {
            key.put( Character.toLowerCase( plainChar ) , Character.toLowerCase( cipherChar ) );
            stored = true;
        }

        return stored;
    }

    /**
     * Given an encryption key, the decryption process operates the same as encryption if the Map that holds the key
     * is reversed, so the Map values become the keys and the Map keys become the values.  Compute and return that
     * inverted key.
     *
     * A key can only be inverted if it is a full bijection (meaning that no ciphertext character appears as a value
     * for more than one Map key)
     * @return -- a SubstitutionKey object that has the inverted map.  Return null if the key can't be inverted.
     */
    public SubstitutionKey invertKey() {
        HashMap<Character, Character> inverted = new HashMap<>();

        /* TODO:  Shouldn't invert the key if two different plaintext characters are mapping to the same
                  ciphertext character.
         */
        /* Build a new key with the keys and values swapped from the current key. */

        Set<Character> plainChar = key.keySet();
        for (Character c : plainChar) {
            inverted.put(key.get(c), c);
        }

        return new SubstitutionKey( inverted );
    }

    /**
     * Encrypt the given string of text using the substitution cipher available in the current object.
     * @param text -- the text to encrypt
     * @return -- the encrypted text, or null in the case of an error.
     */
    public String encryptText( String text ) {
        StringBuilder partiallyEncrypted = new StringBuilder();

        /* TODO:  Shouldn't proceed with encrypting if the key is one that would map two different
                    plaintext characters to the same ciphertext character.
         */
        if (text == null) {
            /* Error conditions: no text to encrypt or we don't have a proper encryption key. */
            return null;
        } else {
            /* Convert each character one at a time.  Only letters are converted. */

            for (int i = 0; i < text.length(); i++) {
                char letter = text.charAt( i );
                if (Character.isLetter( letter ) ) {

                    /* Get the character to encrypt.  Our key is stored entirely in lower case. */

                    Character plain = Character.toLowerCase( letter );
                    if (key.containsKey( plain ) ) {

                        /* We have an encryption for the letter.  Apply it, preserving the case of the letter. */

                        Character encrypt = key.get( plain );
                        if (Character.isUpperCase( letter ) ) {
                            encrypt = Character.toUpperCase( encrypt ) ;
                        }
                        partiallyEncrypted.append( encrypt );
                    } else {

                        /* No matching encryption for the letter.  Leave with a null value to signal that there is a problem. */

                        return null;
                    }
                } else {
                    /* Not something for the key, so just leave it as-is. */

                    partiallyEncrypted.append( letter );
                }
            }
        }
        return partiallyEncrypted.toString();
    }
}
