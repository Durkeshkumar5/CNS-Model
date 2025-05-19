import java.util.Scanner;

public class Que5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the message: ");
        String message = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "");
        System.out.print("Enter the keyword: ");
        String keyword = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "");

        String encrypted = encrypt(message, keyword);
        System.out.println("Encrypted Message: " + encrypted);

        String decrypted = decrypt(encrypted, keyword);
        System.out.println("Decrypted Message: " + decrypted);

        scanner.close();
    }

    static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int c = (text.charAt(i) - 'A' + key.charAt(i % key.length()) - 'A') % 26;
            result.append((char) (c + 'A'));
        }
        return result.toString();
    }

    static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int c = (text.charAt(i) - key.charAt(i % key.length()) + 26) % 26;
            result.append((char) (c + 'A'));
        }
        return result.toString();
    }
}
