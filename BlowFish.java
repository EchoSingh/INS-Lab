import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import java.util.Base64;

public class BlowFish {

    public static void main(String[] args) throws Exception {

        // Generate Blowfish Secret Key (128-bit)
        KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
        keyGenerator.init(128);
        Key secretKey = keyGenerator.generateKey();

        // Setup cipher for encryption using Blowfish in CFB mode
        Cipher cipherOut = Cipher.getInstance("Blowfish/CFB/NoPadding");
        cipherOut.init(Cipher.ENCRYPT_MODE, secretKey);

        // Print Initialization Vector in Base64
        byte[] iv = cipherOut.getIV();
        if (iv != null) {
            String ivBase64 = Base64.getEncoder().encodeToString(iv);
            System.out.println("Initialization Vector (Base64): " + ivBase64);
        }

        // Open input file
        FileInputStream fin = new FileInputStream("inputFile.txt");

        // Open output file (encrypted output)
        FileOutputStream fout = new FileOutputStream("outputFile.txt");

        // Attach cipher output stream to encrypt the data
        CipherOutputStream cout = new CipherOutputStream(fout, cipherOut);

        // Read file byte by byte and encrypt
        int input;
        while ((input = fin.read()) != -1) {
            cout.write(input);
        }

        // Close all streams
        fin.close();
        cout.close();
        fout.close();

        System.out.println("Encryption complete. Encrypted file saved as outputFile.txt");
    }
}
