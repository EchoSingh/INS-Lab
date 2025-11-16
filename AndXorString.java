public class AndXorString {
    public static void main(String[] args) {

        // Original string
        String str = "Hello World";
        int len = str.length();

        // Arrays to store results
        char[] andResult = new char[len];
        char[] xorEncrypted = new char[len];
        char[] xorDecrypted = new char[len];

        // ------------------------- AND OPERATION -------------------------
        // AND with 127 (0111 1111)
        // Hinglish: AND 127 ka matlab hai highest bit hata dena,
        //           character ka value thoda modify hota hai.
        System.out.print("AND with 127: ");
        for (int i = 0; i < len; i++) {
            andResult[i] = (char)(str.charAt(i) & 127);
            System.out.print(andResult[i]);
        }
        System.out.println();

        // ------------------------- XOR ENCRYPTION -------------------------
        // XOR with 127 (Encryption)
        // Hinglish: XOR 127 se karne par character scramble ho jata hai,
        //           readable nahi rehta – basic encryption.
        for (int i = 0; i < len; i++) {
            xorEncrypted[i] = (char)(str.charAt(i) ^ 127);
        }

        // ------------------------- XOR DECRYPTION -------------------------
        // XOR again with 127 (Decryption)
        // Hinglish: XOR ek reversible operation hai:
        //           (x ^ 127) ^ 127 = x (original data)
        //           Isliye humari string wapas aa jati hai.
        System.out.print("XOR with 127: ");
        for (int i = 0; i < len; i++) {
            xorDecrypted[i] = (char)(xorEncrypted[i] ^ 127);
            System.out.print(xorDecrypted[i]);
        }
        System.out.println();
    }
}
