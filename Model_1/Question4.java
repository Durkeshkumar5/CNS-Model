import java.util.Scanner;

public class Que4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Caesar cipher text: ");
        String ciphertext = scanner.nextLine().toUpperCase();

        for (int shift = 1; shift < 26; shift++) {
            StringBuilder decrypted = new StringBuilder();
            for (char c : ciphertext.toCharArray()) {
                if (Character.isLetter(c)) {
                    char base = 'A';
                    decrypted.append((char) ((c - base - shift + 26) % 26 + base));
                } else {
                    decrypted.append(c);
                }
            }
            System.out.println("Shift " + shift + ": " + decrypted);
        }
        scanner.close();
    }
}
