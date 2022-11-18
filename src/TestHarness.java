import java.util.Map;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class TestHarness {
    // Command options for the user to use

    private static final String plaintextCommand = "plain";
    private static final String ciphertextCommand = "cipher";
    private static final String plainAppendCommand = "pappend";
    private static final String cipherAppendCommand = "cappend";
    private static final String encryptCommand = "encode";
    private static final String decryptCommand = "decode";
    private static final String changeKeyCommand = "key";
    private static final String showKeyCommand = "showkey";
    private static final String showPlainCommand = "showplain";
    private static final String showCipherCommand = "showcipher";
    private static final String helpCommand = "help";
    private static final String quitCommand = "quit";

    // Print the information on all the commands available in this test harness

    private static void printUsage() {
        System.out.println("Commands available:");
        System.out.println("  " + plaintextCommand + " <string>");
        System.out.println("  " + ciphertextCommand + " <string>");
        System.out.println("  " + plainAppendCommand + " <string>");
        System.out.println("  " + cipherAppendCommand + " <string>");
        System.out.println("  " + encryptCommand + "  [ | null | <filename>  ]" );
        System.out.println("  " + decryptCommand + "  [ | null | <filename>  ]");
        System.out.println("  " + changeKeyCommand + " <plain character> <cipher character>");
        System.out.println("  " + showKeyCommand );
        System.out.println("  " + showPlainCommand );
        System.out.println("  " + showCipherCommand );
        System.out.println("  " + quitCommand);
    }

    // Retrieve data to the end of the line as an argument for a method call
    // Include two special kinds of arguments:
    //   "null" asks us to return no string
    //   "empty" asks us to return an empty string

    private static String getEndingString(Scanner userInput ) {
        String userArgument = null;

        userArgument = userInput.nextLine();
        userArgument = userArgument.trim();

        // Include a "hack" to provide null and empty strings for testing
        if (userArgument.equalsIgnoreCase("empty")) {
            userArgument = "";
        } else if (userArgument.equalsIgnoreCase("null")) {
            userArgument = null;
        }

        return userArgument;
    }

    private static void printKey( SubstitutionCipher cipher ) {
        System.out.println( "System reports key valid status:\n" + cipher.keyIsValid());

        Map<Character, Character> key = cipher.getKey();

        System.out.println( "Full key is:");
        for (Character ch = 'a'; ch <= 'z'; ch++) {
            if (key.containsKey(ch)) {
                System.out.println("  " + ch + " -> " + key.get( ch ));
            }
        }
        System.out.println( "end of key");

    }
    // Main program to process user commands.
    // This method is not robust.  When it asks for a command, it expects all arguments to be there.
    // It is a quickly-done test harness rather than a full solution for an assignment.

    public static void main(String[] args) {
        // Define variables to manage user input

        String userCommand = "";
        Scanner userInput = new Scanner(System.in);

        // Define the auction system that we will be testing.

        SubstitutionCipher cipher = new SubstitutionCipher();

        // Define variables to catch the return values of the methods that we don't use but might want
        // to have around.

            String ignoredString;
            String storedPlaintext = "";
            String storedCiphertext = "";

            // Process the user input until they provide the command "quit"

            do {
                // Find out what the user wants to do
                userCommand = userInput.next();

                        /* Do what the user asked for.  If condition for each command.  Since each command
                           has a different number of parameters, we do separate handling of each command. */

                if (userCommand.equalsIgnoreCase(plaintextCommand)) {
                    String text = getEndingString( userInput );
                    Boolean added = cipher.plaintext( text );

                    if (text == null) {
                        storedPlaintext = "";
                    } else {
                        storedPlaintext = new String(text);
                    }
                    System.out.println("Plaintext add outcome: " + added);
                } else if (userCommand.equalsIgnoreCase(ciphertextCommand)) {
                    String text = getEndingString( userInput );
                    Boolean added = cipher.ciphertext( text );

                    if (text == null) {
                        storedCiphertext = "";
                    } else {
                        storedCiphertext = new String(text);
                    }
                    System.out.println("Ciphertext add outcome: " + added);
                } else if (userCommand.equalsIgnoreCase(plainAppendCommand)) {
                    String text = getEndingString( userInput );
                    storedPlaintext = storedPlaintext + "\n";

                    if ((text != null) && (text.length() > 0)) {
                        storedPlaintext = storedPlaintext + text;
                    }

                    Boolean added = cipher.plaintext( storedPlaintext );

                    System.out.println("Plaintext append outcome: " + added);
                } else if (userCommand.equalsIgnoreCase(cipherAppendCommand)) {
                    String text = getEndingString( userInput );
                    storedCiphertext = storedCiphertext + "\n";

                    if ((text != null) && (text.length() > 0)) {
                        storedCiphertext = storedCiphertext + text;
                    }

                    Boolean added = cipher.ciphertext( storedCiphertext );

                    System.out.println("Ciphertext append outcome: " + added);
                } else if (userCommand.equalsIgnoreCase(encryptCommand)) {
                    String destination = getEndingString(userInput);
                    System.out.println( "System reports key valid status:\n" + cipher.keyIsValid());

                    if (!cipher.encodeText()) {
                        System.out.println("null returned for encoded string");
                    } else {
                        PrintWriter screen = new PrintWriter( System.out );;
                        if (destination == null) {
                            screen = null;
                        } else if (destination.length() > 0) {
                            try {
                                screen = new PrintWriter(new BufferedWriter(new FileWriter(destination)));
                            } catch (Exception e) {
                                screen = null;
                            }
                        } else {
                            System.out.println("Returned string:");
                        }
                        cipher.extractCiphertext(screen);
                        if (screen != null) {
                            screen.flush();
                        }
                        System.out.println();
                    }
                } else if (userCommand.equalsIgnoreCase(decryptCommand)) {
                    String destination = getEndingString(userInput);
                    System.out.println( "System reports key valid status:\n" + cipher.keyIsValid());

                    if (!cipher.decodeText()) {
                        System.out.println("null returned for decrypted string");
                    } else {
                        PrintWriter screen = new PrintWriter( System.out );;
                        if (destination == null) {
                            screen = null;
                        } else if (destination.length() > 0) {
                            try {
                                screen = new PrintWriter(new BufferedWriter(new FileWriter(destination)));
                            } catch (Exception e) {
                                screen = null;
                            }
                        } else {
                            System.out.println("Returned string:");
                        }
                        cipher.extractPlaintext(screen);
                        if (screen != null) {
                            screen.flush();
                        }
                        System.out.println();
                    }
                } else if (userCommand.equalsIgnoreCase(changeKeyCommand)) {
                    Character plainChar = userInput.next().charAt(0);
                    Character cipherChar = userInput.next().charAt(0);
                    ignoredString = getEndingString(userInput);

                    Boolean changed = cipher.setDecodeLetter( plainChar, cipherChar );

                    System.out.println( "Change return:\n" + changed );
                    System.out.println( "System reports key valid status:\n" + cipher.keyIsValid());
                } else if (userCommand.equalsIgnoreCase(showPlainCommand)) {
                    System.out.println( "Stored plaintext:\n" + storedPlaintext );
                } else if (userCommand.equalsIgnoreCase(showCipherCommand)) {
                    System.out.println( "Stored ciphertext:\n" + storedCiphertext );
                } else if (userCommand.equalsIgnoreCase(showKeyCommand)) {
                    ignoredString = getEndingString(userInput);
                    printKey( cipher );
                } else if (userCommand.equalsIgnoreCase(helpCommand)) {
                    printUsage();
                } else if (userCommand.equalsIgnoreCase(quitCommand)) {
                    System.out.println(userCommand);
                } else {
                    System.out.println("Bad command: " + userCommand);
                }
            } while (!userCommand.equalsIgnoreCase("quit"));

            // The user is done so close the stream of user input before ending.

            userInput.close();
        }
}
