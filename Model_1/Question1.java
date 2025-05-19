import java.util.*;
public class Que1 {
    static char[][] matrix = new char[5][5];
    static String key;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Playfair key: ");
        key = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        System.out.print("Enter the message to encrypt: ");
        String message = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        constructMatrix(key);
        String encrypted = encryptMessage(message);
        System.out.println("\nEncrypted Message: " + encrypted);
        String decrypted = decryptMessage(encrypted);
        System.out.println("Decrypted Message: " + decrypted);

        scanner.close();
    }
    static void constructMatrix(String key) {
        Set<Character> used = new LinkedHashSet<>();
        for (char c : key.toCharArray()) {
            used.add(c);
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c != 'J') used.add(c);
        }
        Iterator<Character> it = used.iterator();
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                matrix[i][j] = it.next();

        System.out.println("\nPlayfair Matrix:");
        for (char[] row : matrix) {
            for (char c : row)
                System.out.print(c + " ");
            System.out.println();
        }
    }
    static String prepareText(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(text.charAt(i));
            if (i < text.length() - 1 && text.charAt(i) == text.charAt(i + 1)) {
                sb.append('X');
            }
        }
        if (sb.length() % 2 != 0) {
            sb.append('X');
        }
        return sb.toString();
    }
    static String encryptMessage(String message) {
        StringBuilder encrypted = new StringBuilder();
        message = prepareText(message);

        for (int i = 0; i < message.length(); i += 2) {
            char a = message.charAt(i);
            char b = message.charAt(i + 1);
            encrypted.append(encryptPair(a, b));
        }

        return encrypted.toString();
    }
    static String decryptMessage(String cipher) {
        StringBuilder decrypted = new StringBuilder();

        for (int i = 0; i < cipher.length(); i += 2) {
            char a = cipher.charAt(i);
            char b = cipher.charAt(i + 1);
            decrypted.append(decryptPair(a, b));
        }

        return decrypted.toString().replace("X", ""); 
    }
    static String encryptPair(char a, char b) {
        int[] posA = findPosition(a);
        int[] posB = findPosition(b);
        if (posA[0] == posB[0]) { 
            return "" + matrix[posA[0]][(posA[1] + 1) % 5] + matrix[posB[0]][(posB[1] + 1) % 5];
        } else if (posA[1] == posB[1]) { 
            return "" + matrix[(posA[0] + 1) % 5][posA[1]] + matrix[(posB[0] + 1) % 5][posB[1]];
        } else { 
            return "" + matrix[posA[0]][posB[1]] + matrix[posB[0]][posA[1]];
        }
    }
    static String decryptPair(char a, char b) {
        int[] posA = findPosition(a);
        int[] posB = findPosition(b);
        if (posA[0] == posB[0]) { 
            return "" + matrix[posA[0]][(posA[1] + 4) % 5] + matrix[posB[0]][(posB[1] + 4) % 5];
        } else if (posA[1] == posB[1]) { 
            return "" + matrix[(posA[0] + 4) % 5][posA[1]] + matrix[(posB[0] + 4) % 5][posB[1]];
        } else { 
            return "" + matrix[posA[0]][posB[1]] + matrix[posB[0]][posA[1]];
        }
    }
    static int[] findPosition(char c) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (matrix[i][j] == c)
                    return new int[]{i, j};
        return null;
    }
}