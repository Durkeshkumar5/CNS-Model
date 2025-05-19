import java.security.*;
import java.util.Scanner;
public class Que6 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the legal document text to sign:");
        String document = scanner.nextLine();
        KeyPair keyPair = generateKeyPair();
        byte[] signature = signDocument(document, keyPair.getPrivate());
        System.out.println("\nDocument signed successfully.");
        System.out.println("Signature (Hex): " + bytesToHex(signature));
        boolean isVerified = verifyDocument(document, signature, keyPair.getPublic());
        System.out.println("Signature Verification Result: " + isVerified);

        scanner.close();
    }
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        keyGen.initialize(1024); 
        return keyGen.generateKeyPair();
    }
    public static byte[] signDocument(String data, PrivateKey privateKey) throws Exception {
        Signature signer = Signature.getInstance("SHA1withDSA");
        signer.initSign(privateKey);
        signer.update(data.getBytes());
        return signer.sign();
    }
    public static boolean verifyDocument(String data, byte[] signature, PublicKey publicKey) throws Exception {
        Signature verifier = Signature.getInstance("SHA1withDSA");
        verifier.initVerify(publicKey);
        verifier.update(data.getBytes());
        return verifier.verify(signature);
    }
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
}