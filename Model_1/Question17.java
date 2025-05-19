import java.math.BigInteger;
import java.util.Scanner;

public class Que17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int p = 19;
        int q = 41;
        int n = p * q;
        int phi = (p - 1) * (q - 1);
        int e = 7;
        BigInteger bigE = BigInteger.valueOf(e);
        BigInteger bigPhi = BigInteger.valueOf(phi);
        BigInteger d = bigE.modInverse(bigPhi);
        System.out.print("Enter numeric message (m): ");
        int m = sc.nextInt();
        BigInteger bigM = BigInteger.valueOf(m);
        BigInteger bigN = BigInteger.valueOf(n);
        BigInteger c = bigM.modPow(bigE, bigN);
        System.out.println("Encrypted message (ciphertext): " + c);
        BigInteger decrypted = c.modPow(d, bigN);
        System.out.println("Decrypted message: " + decrypted);
        sc.close();
    }
}
