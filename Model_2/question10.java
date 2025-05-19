import java.security.*;
import java.util.Scanner;
public class Que10 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your vote (e.g., Candidate A):");
        String vote = scanner.nextLine();
        KeyPair keyPair = generateKeyPair();
        byte[] signedVote = signVote(vote, keyPair.getPrivate());
        System.out.println("\nVote signed successfully.");
        System.out.println("Digital Signature (Hex): " + bytesToHex(signedVote));
        boolean isVerified = verifyVote(vote, signedVote, keyPair.getPublic());
        if (isVerified) {
            System.out.println("Vote verification successful. Vote stored in blockchain.");
        } else {
            System.out.println("Vote verification failed. Vote rejected.");
        }

        scanner.close();
    }
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        keyGen.initialize(1024);  
        return keyGen.generateKeyPair();
    }
    public static byte[] signVote(String vote, PrivateKey privateKey) throws Exception {
        Signature dsa = Signature.getInstance("SHA1withDSA");
        dsa.initSign(privateKey);
        dsa.update(vote.getBytes());
        return dsa.sign();
    }
    public static boolean verifyVote(String vote, byte[] signature, PublicKey publicKey) throws Exception {
        Signature dsa = Signature.getInstance("SHA1withDSA");
        dsa.initVerify(publicKey);
        dsa.update(vote.getBytes());
        return dsa.verify(signature);
    }
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
}