import java.util.*;
import java.io.*;

public class Caesercipher {

    // Alphabet used for shifting
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    // ------------------------- ENCRYPTION -------------------------
    // Hinglish: Plaintext ke har character ko alphabet me dhoondte hain,
    //           fir usko 'key' positions aage shift kar dete hain.
    public static String encrypt(String ptext, int cserkey) {
        String ctext = "";

        for (int i = 0; i < ptext.length(); i++) {

            // Character ka index alphabet me find karna
            int plainnumeric = ALPHABET.indexOf(ptext.charAt(i));

            // Shift (Caesar Cipher logic)
            int ciphernumeric = (plainnumeric + cserkey) % 26;

            // Naya shifted character
            char cipherchar = ALPHABET.charAt(ciphernumeric);

            ctext += cipherchar;
        }
        return ctext;
    }

    // ------------------------- DECRYPTION -------------------------
    // Hinglish: Ciphertext ko wapas original banane ke liye
    //           key positions peeche shift karte hain.
    public static String decrypt(String ctext, int cserkey) {
        String ptext = "";

        for (int i = 0; i < ctext.length(); i++) {

            // Cipher character ka index
            int ciphernumeric = ALPHABET.indexOf(ctext.charAt(i));

            // Reverse shift
            int plainnumeric = (ciphernumeric - cserkey) % 26;

            // Negative values handle karna
            if (plainnumeric < 0) {
                plainnumeric = ALPHABET.length() + plainnumeric;
            }

            char plainchar = ALPHABET.charAt(plainnumeric);
            ptext += plainchar;
        }
        return ptext;
    }

    // ------------------------- MAIN PROGRAM -------------------------
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter the PLAIN TEXT for Encryption: ");
        String plaintext = br.readLine().toLowerCase(); // ensure lowercase

        System.out.println("Enter the CAESAR KEY between 0 and 25: ");
        int cserkey = Integer.parseInt(br.readLine());

        // Encryption
        System.out.println("ENCRYPTION");
        String ciphertext = encrypt(plaintext, cserkey);
        System.out.println("CIPHER TEXT : " + ciphertext);

        // Decryption
        System.out.println("DECRYPTION");
        plaintext = decrypt(ciphertext, cserkey);
        System.out.println("PLAIN TEXT : " + plaintext);
    }
}
