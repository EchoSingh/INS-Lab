import java.io.*;
import java.util.*;

public class SubstitutionCipher {

    static Scanner sc = new Scanner(System.in);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        // Mapping strings:
        // 'a' maps to 'z', 'b' maps to 'y', 'c' maps to 'x', ...
        String a = "abcdefghijklmnopqrstuvwxyz";
        String b = "zyxwvutsrqponmlkjihgfedcba"; // reverse alphabet

        System.out.print("Enter any string: ");
        String str = br.readLine();

        String decrypt = "";   // encrypted output
        char c;

        // ---------------------- ENCRYPTION LOGIC ----------------------
        // Hinglish:
        // User input ka har character 'a' string me find karte hain.
        // Jis position par milta hai, usi position ka character 'b' string se le kar append kar dete hain.
        // Isme basically simple substitution ho raha hai (Atbash cipher).
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);

            int j = a.indexOf(c);   // find index in normal alphabet

            decrypt = decrypt + b.charAt(j);  // pick corresponding reverse alphabet char
        }

        System.out.println("The encrypted data is: " + decrypt);
    }
}
