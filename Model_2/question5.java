import java.security.*;
import java.util.Scanner;
public class Que5 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your consent text:");
        String consentText = scanner.nextLine();
        KeyPair keyPair = generateKeyPair();
        byte[] signature = signData(consentText, keyPair.getPrivate());
        System.out.println("Digital Signature (Hex): " + bytesToHex(signature));
        boolean isVerified = verifySignature(consentText, signature, keyPair.getPublic());
        System.out.println("Signature Verified: " + isVerified);
        scanner.close();
    }
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        keyGen.initialize(1024);
        return keyGen.generateKeyPair();
    }
    public static byte[] signData(String data, PrivateKey privateKey) throws Exception {
        Signature signer = Signature.getInstance("SHA1withDSA");
        signer.initSign(privateKey);
        signer.update(data.getBytes());
        return signer.sign();
    }
    public static boolean verifySignature(String data, byte[] signature, PublicKey publicKey) throws Exception {
        Signature verifier = Signature.getInstance("SHA1withDSA");
        verifier.initVerify(publicKey);
        verifier.update(data.getBytes());
        return verifier.verify(signature);
    }
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}