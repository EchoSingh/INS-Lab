import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;

public class DiffeHellman {

    // Values given in lab manual (simple example)
    public final static int pValue = 47;
    public final static int gValue = 71;
    public final static int XaValue = 9;     // Alice ka private key
    public final static int XbValue = 14;    // Bob ka private key

    public static void main(String[] args) throws Exception {

        // ---- Simple DH example using manual values ----
        BigInteger p = new BigInteger(Integer.toString(pValue));
        BigInteger g = new BigInteger(Integer.toString(gValue));
        BigInteger Xa = new BigInteger(Integer.toString(XaValue));
        BigInteger Xb = new BigInteger(Integer.toString(XbValue));

        System.out.println("==== Simple Diffie-Hellman Example (Manual Values) ====");

        // Alice public key = g^Xa mod p
        BigInteger Ya = g.modPow(Xa, p);

        // Bob public key = g^Xb mod p
        BigInteger Yb = g.modPow(Xb, p);

        // Shared keys
        BigInteger Ka = Yb.modPow(Xa, p);   // Alice computes
        BigInteger Kb = Ya.modPow(Xb, p);   // Bob computes

        System.out.println("p = " + p);
        System.out.println("g = " + g);
        System.out.println("Alice private Xa = " + Xa);
        System.out.println("Bob private Xb = " + Xb);

        System.out.println("Alice public key Ya = " + Ya);
        System.out.println("Bob public key Yb = " + Yb);

        System.out.println("Alice shared key Ka = " + Ka);
        System.out.println("Bob shared key Kb = " + Kb);

        // ---- Random strong DH parameters ----
        System.out.println("\n==== Java Generated Diffie-Hellman Keys ====");
        createKey();

        // ---- User specified large prime parameters ----
        System.out.println("\n==== Diffie-Hellman with Custom Large p and g ====");
        int bitLength = 512;
        SecureRandom rnd = new SecureRandom();
        p = BigInteger.probablePrime(bitLength, rnd);
        g = BigInteger.probablePrime(bitLength, rnd);

        createSpecificKey(p, g);
    }

    // -------------------------------------------------------------
    // Java DH key generation (automatic safe parameters)
    // -------------------------------------------------------------
    public static void createKey() throws Exception {

        // Hinglish comment: Java apne aap safe p aur g generate karega
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DiffieHellman");
        kpg.initialize(512);

        KeyPair kp = kpg.generateKeyPair();
        KeyFactory kfactory = KeyFactory.getInstance("DiffieHellman");

        DHPublicKeySpec kspec =
            (DHPublicKeySpec) kfactory.getKeySpec(kp.getPublic(), DHPublicKeySpec.class);

        System.out.println("Public key (Java generated): ");
        System.out.println("p = " + kspec.getP());
        System.out.println("g = " + kspec.getG());
        System.out.println("y = " + kspec.getY());
    }

    // -------------------------------------------------------------
    // DH with user supplied p and g
    // -------------------------------------------------------------
    public static void createSpecificKey(BigInteger p, BigInteger g) throws Exception {

        // Hinglish comment: Ab hum custom p aur g ke sath key generate kar rahe hain
        DHParameterSpec param = new DHParameterSpec(p, g);

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DiffieHellman");
        kpg.initialize(param);

        KeyPair kp = kpg.generateKeyPair();
        KeyFactory kfactory = KeyFactory.getInstance("DiffieHellman");

        DHPublicKeySpec kspec =
            (DHPublicKeySpec) kfactory.getKeySpec(kp.getPublic(), DHPublicKeySpec.class);

        System.out.println("Custom p and g ke sath generated public key:");
        System.out.println("p = " + kspec.getP());
        System.out.println("g = " + kspec.getG());
        System.out.println("y = " + kspec.getY());
    }
}
