import java.util.*;

public class Transposition {

    // ===============================
    //  KEYWORD KO NUMBER KEY BANANA
    // ===============================
    static int[] keywordToKey(String keyword) {

        keyword = keyword.toUpperCase();
        final String kw = keyword;    // final copy -> inner class allowed

        int len = keyword.length();
        Integer[] order = new Integer[len];

        // Index fill
        for (int i = 0; i < len; i++)
            order[i] = i;

        // Sorting alphabetical order ke hisaab se
        Arrays.sort(order, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return Character.compare(kw.charAt(a), kw.charAt(b));
            }
        });

        int[] numericKey = new int[len];

        // key assignment (1..n)
        for (int i = 0; i < len; i++)
            numericKey[order[i]] = i + 1;

        return numericKey;
    }

    // ===============================
    //  ENCRYPTION (COLUMNAR)
    // ===============================
    static String encrypt(String plaintext, int[] key) {

        plaintext = plaintext.replaceAll(" ", "").toUpperCase();

        int cols = key.length;
        int rows = (int) Math.ceil((double) plaintext.length() / cols);

        char[][] matrix = new char[rows][cols];

        int index = 0;

        // Matrix fill row-wise
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (index < plaintext.length())
                    matrix[r][c] = plaintext.charAt(index++);
                else
                    matrix[r][c] = 'X'; // padding
            }
        }

        // Ab column-wise reading key order ke hisaab se
        StringBuilder cipher = new StringBuilder();

        for (int k = 1; k <= cols; k++) {
            int colIndex = -1;
            for (int i = 0; i < cols; i++) {
                if (key[i] == k) {
                    colIndex = i;
                    break;
                }
            }

            for (int r = 0; r < rows; r++)
                cipher.append(matrix[r][colIndex]);
        }

        return cipher.toString();
    }

    // ===============================
    //  DECRYPTION (COLUMNAR)
    // ===============================
    static String decrypt(String ciphertext, int[] key) {

        ciphertext = ciphertext.toUpperCase();

        int cols = key.length;
        int rows = ciphertext.length() / cols;

        char[][] matrix = new char[rows][cols];

        int index = 0;

        // Column-wise fill based on key order
        for (int k = 1; k <= cols; k++) {

            int colIndex = -1;

            for (int i = 0; i < cols; i++) {
                if (key[i] == k) {
                    colIndex = i;
                    break;
                }
            }

            for (int r = 0; r < rows; r++)
                matrix[r][colIndex] = ciphertext.charAt(index++);
        }

        // Row-wise read to get plaintext
        StringBuilder plain = new StringBuilder();

        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                plain.append(matrix[r][c]);

        return plain.toString();
    }

    // ===============================
    //  MAIN FUNCTION
    // ===============================
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter KEYWORD: ");
        String keyword = sc.nextLine();

        System.out.print("Enter PLAIN TEXT: ");
        String plaintext = sc.nextLine();

        int[] key = keywordToKey(keyword);

        String cipher = encrypt(plaintext, key);
        System.out.println("CIPHER TEXT: " + cipher);

        String decrypted = decrypt(cipher, key);
        System.out.println("DECRYPTED TEXT: " + decrypted);
    }
}
