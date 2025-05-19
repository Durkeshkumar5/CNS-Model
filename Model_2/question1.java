import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
public class Que1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the document to be signed:");
        String document = scanner.nextLine();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageBytes = document.getBytes();
            byte[] digestBytes = md.digest(messageBytes);
            StringBuilder sb = new StringBuilder();
            for (byte b : digestBytes) {
                sb.append(String.format("%02x", b));
            }
            System.out.println("SHA-1 Message Digest: " + sb.toString());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("SHA-1 algorithm not found: " + e.getMessage());
        }

        scanner.close();
    }
}