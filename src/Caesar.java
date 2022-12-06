import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.*;
public class Caesar{
    public static char caesar(char letter, int shift){
        char letterShifted;

        if(letter =='z' && shift ==0){
            letterShifted = letter;
        }
        else if(letter =='z'){
            letterShifted = getLetterShifted(letter, shift);
        }
        else if ((letter + shift) <= 'z') {
            letterShifted = (char) ((letter + shift));
        } else {
            letterShifted = getLetterShifted(letter, shift);
        }
        return letterShifted;

    }

    private static char getLetterShifted(char letter, int shift) {
        return (char) ((letter + shift) -26);
    }

}
