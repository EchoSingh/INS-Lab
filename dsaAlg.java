import java.util.*;
import java.math.BigInteger;

public class dsaAlg {

    final static BigInteger ONE = BigInteger.ONE;
    final static BigInteger ZERO = BigInteger.ZERO;

    /* ------------------------------------------------------------
       Next probable prime n se start hoke return karega
    ------------------------------------------------------------ */
    public static BigInteger getNextPrime(String ans) {
        BigInteger test = new BigInteger(ans);
        while (!test.isProbablePrime(99)) {
            test = test.add(ONE);
        }
        return test;
    }

    /* ------------------------------------------------------------
       p-1 ka largest prime factor find karta hai (DSA ke liye q)
    ------------------------------------------------------------ */
    public static BigInteger findQ(BigInteger n) {
        BigInteger start = new BigInteger("2");

        // n ke factors remove karte jao jab tak prime na ban jaye
        while (!n.isProbablePrime(99)) {

            // start ko increment kare jab tak divide na ho
            while (!n.mod(start).equals(ZERO)) {
                start = start.add(ONE);
            }

            // factor mil gaya, divide kar do
            n = n.divide(start);
        }
        return n; // ye q hai
    }

    /* ------------------------------------------------------------
       Generator g find karne ke liye helper
    ------------------------------------------------------------ */
    public static BigInteger getGen(BigInteger p, BigInteger q, Random r) {
        BigInteger h;
        do {
            h = new BigInteger(p.bitLength(), r);
            h = h.mod(p);
        } while (h.compareTo(BigInteger.TWO) < 0);
        return h.modPow((p.subtract(ONE)).divide(q), p);
    }

    public static void main(String[] args) throws Exception {

        Random randObj = new Random();

        // ---------------- GLOBAL PUBLIC KEY COMPONENTS ----------------
        BigInteger p = getNextPrime("10600"); // approx prime
        BigInteger q = findQ(p.subtract(ONE)); // q divides p-1
        BigInteger g = getGen(p, q, randObj);

        System.out.println("Digital Signature Algorithm");
        System.out.println("----------------------------");
        System.out.println("Global Public Key Components:");
        System.out.println("p: " + p);
        System.out.println("q: " + q);
        System.out.println("g: " + g);

        // ---------------- PRIVATE KEY (x) GENERATION ----------------
        BigInteger x;
        do {
            x = new BigInteger(q.bitLength(), randObj).mod(q);
        } while (x.equals(ZERO));   // x != 0 hona chahiye

        // PUBLIC KEY (y)
        BigInteger y = g.modPow(x, p);

        // ---------------- RANDOM PER-MESSAGE KEY (k) ----------------
        BigInteger k;
        do {
            k = new BigInteger(q.bitLength(), randObj).mod(q);
        } while (k.equals(ZERO) || !k.gcd(q).equals(ONE));  // invertible hona zaroori

        // ---------------- SIGNATURE GENERATION ----------------
        BigInteger r = g.modPow(k, p).mod(q);

        BigInteger hashVal = new BigInteger(q.bitLength(), randObj); // random hash example
        BigInteger kInv = k.modInverse(q); // safe because gcd(k,q)=1

        BigInteger s = kInv.multiply(hashVal.add(x.multiply(r))).mod(q);

        // ---------------- SHOW SECRET INFO ----------------
        System.out.println("\nSecret Information:");
        System.out.println("Private key (x): " + x);
        System.out.println("Random k: " + k);
        System.out.println("Public key (y): " + y);
        System.out.println("Hash (h): " + hashVal);

        System.out.println("\nDigital Signature Generated:");
        System.out.println("r: " + r);
        System.out.println("s: " + s);

        // ---------------- SIGNATURE VERIFICATION ----------------
        BigInteger w = s.modInverse(q);
        BigInteger u1 = hashVal.multiply(w).mod(q);
        BigInteger u2 = r.multiply(w).mod(q);

        BigInteger v = g.modPow(u1, p).multiply(y.modPow(u2, p));
        v = v.mod(p).mod(q);

        System.out.println("\nSignature Verification:");
        System.out.println("w : " + w);
        System.out.println("u1: " + u1);
        System.out.println("u2: " + u2);
        System.out.println("v : " + v);

        if (v.equals(r)) {
            System.out.println("\nSUCCESS: Signature Verified!");
        } else {
            System.out.println("\nERROR: Signature Invalid.");
        }
    }
}
