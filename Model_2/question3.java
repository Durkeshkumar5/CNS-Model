import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
public class Que3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        String hashedPassword = hashPasswordSHA1(password);
        if (hashedPassword != null) {
            System.out.println("SHA-1 Hashed Password (store in DB): " + hashedPassword);
        } else {
            System.out.println("Error hashing the password.");
        }

        scanner.close();
    }
    public static String hashPasswordSHA1(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}