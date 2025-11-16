// Simple Java program to demonstrate XOR operation on characters
public class XorString {

    public static void main(String[] args) {

        // Original string
        String str = "Hello World";

        // String ki length nikal li
        int len = str.length();

        // Ek character array banaya jisme hum XOR ke baad wale characters store karenge
        char[] str1 = new char[len];

        // Loop ke through har character ko process kar rahe hain
        for (int i = 0; i < len; i++) {

            // Yahan hum har character ko 0 ke saath XOR kar rahe hain
            // XOR with 0 ka simple matlab: character bilkul same rahega, koi change nahi hoga
            // Kyunki: x ^ 0 = x
            str1[i] = (char)(str.charAt(i) ^ 0);

            // Processed character ko print kar diya
            System.out.print(str1[i]);
        }

        // Last me new line print kar diya
        System.out.println();
    }
}
