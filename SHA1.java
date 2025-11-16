import java.security.*;

public class SHA1 {

    public static void main(String[] args) {
        try {

            MessageDigest md = MessageDigest.getInstance("SHA1");

            System.out.println("Message digest object info:");
            System.out.println(" Algorithm = " + md.getAlgorithm());
            System.out.println(" Provider  = " + md.getProvider());
            System.out.println(" ToString  = " + md.toString());

            // Test case 1
            String input = "";
            md.update(input.getBytes());
            byte[] output = md.digest();
            System.out.println();
            System.out.println("SHA1(\"" + input + "\") = " + bytesToHex(output));

            // Test case 2
            input = "abc";
            md.update(input.getBytes());
            output = md.digest();
            System.out.println();
            System.out.println("SHA1(\"" + input + "\") = " + bytesToHex(output));

            // Test case 3
            input = "abcdefghijklmnopqrstuvwxyz";
            md.update(input.getBytes());
            output = md.digest();
            System.out.println();
            System.out.println("SHA1(\"" + input + "\") = " + bytesToHex(output));

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    // Convert byte array to hex string
    public static String bytesToHex(byte[] b) {
        char hexDigit[] = {
            '0','1','2','3','4','5','6','7','8','9',
            'A','B','C','D','E','F'
        };

        StringBuffer buf = new StringBuffer();

        for (int j = 0; j < b.length; j++) {
            buf.append(hexDigit[(b[j] >> 4) & 0x0F]);
            buf.append(hexDigit[b[j] & 0x0F]);
        }

        return buf.toString();
    }
}
