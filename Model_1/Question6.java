import java.util.*;

public class Que6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the message: ");
        String message = sc.nextLine().replaceAll("\\s+", "").toUpperCase();
        System.out.print("Enter the number of rails: ");
        int rails = sc.nextInt();

        String encrypted = encryptRailFence(message, rails);
        System.out.println("Encrypted Message: " + encrypted);

        String decrypted = decryptRailFence(encrypted, rails);
        System.out.println("Decrypted Message: " + decrypted);

        sc.close();
    }

    static String encryptRailFence(String text, int key) {
        StringBuilder[] rail = new StringBuilder[key];
        for (int i = 0; i < key; i++) rail[i] = new StringBuilder();
        int dir = 1, row = 0;
        for (char c : text.toCharArray()) {
            rail[row].append(c);
            row += dir;
            if (row == 0 || row == key - 1) dir *= -1;
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder r : rail) result.append(r);
        return result.toString();
    }

    static String decryptRailFence(String cipher, int key) {
        char[][] rail = new char[key][cipher.length()];
        boolean dirDown = false;
        int row = 0, col = 0;
        for (int i = 0; i < cipher.length(); i++) {
            rail[row][col++] = '*';
            if (row == 0 || row == key - 1) dirDown = !dirDown;
            row += dirDown ? 1 : -1;
        }
        int index = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < cipher.length(); j++) {
                if (rail[i][j] == '*' && index < cipher.length()) {
                    rail[i][j] = cipher.charAt(index++);
                }
            }
        }
        StringBuilder result = new StringBuilder();
        row = 0;
        col = 0;
        dirDown = false;
        for (int i = 0; i < cipher.length(); i++) {
            result.append(rail[row][col++]);
            if (row == 0 || row == key - 1) dirDown = !dirDown;
            row += dirDown ? 1 : -1;
        }
        return result.toString();
    }
}
