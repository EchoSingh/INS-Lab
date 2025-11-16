import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Hinglish: User se do prime number input karwa rahe hain
        System.out.print("Enter a prime number p: ");
        BigInteger p = sc.nextBigInteger();

        System.out.print("Enter another prime number q: ");
        BigInteger q = sc.nextBigInteger();

        // n = p * q
        BigInteger n = p.multiply(q);

        // φ(n) = (p-1)(q-1)
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Public exponent e choose karna (gcd(e,φ)=1)
        BigInteger e = generateE(phi);

        // Private exponent d = e⁻¹ mod φ(n)
        BigInteger d = e.modInverse(phi);

        // Result print kar rahe hain
        System.out.println("\n===== RSA KEY PAIR GENERATED =====");
        System.out.println("Public Key  (e, n) = (" + e + ", " + n + ")");
        System.out.println("Private Key (d, n) = (" + d + ", " + n + ")");
    }

    // --------------------------------------------------------------------
    // Function to generate 'e' such that gcd(e, φ(n)) = 1
    // Hinglish comments inside
    // --------------------------------------------------------------------
    public static BigInteger generateE(BigInteger phi) {

        Random rand = new Random();
        BigInteger e;
        BigInteger gcd;

        do {
            // Hinglish: random number le rahe hain range (2, phi-1)
            int candidate = rand.nextInt(phi.intValue() - 2) + 2;

            e = BigInteger.valueOf(candidate);

            // gcd(e, phi) calculate kar rahe hain
            gcd = phi.gcd(e);

        } while (gcd.intValue() != 1);  // Jab tak gcd = 1 nahi hota, repeat

        return e;
    }
}
