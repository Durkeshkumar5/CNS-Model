import java.util.*;

public class Que2 {

    static int[][] key = { {3, 3}, {2, 5} };
    static int[][] inverseKey;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the plaintext (length multiple of 2): ");
        String plaintext = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "");

        if (plaintext.length() % 2 != 0) plaintext += "X";

        inverseKey = invertKeyMatrix(key);

        String ciphertext = encrypt(plaintext);
        System.out.println("Encrypted: " + ciphertext);

        String decrypted = decrypt(ciphertext);
        System.out.println("Decrypted: " + decrypted);

        sc.close();
    }

    static String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            int[] pair = { text.charAt(i) - 'A', text.charAt(i + 1) - 'A' };
            int c1 = (key[0][0] * pair[0] + key[0][1] * pair[1]) % 26;
            int c2 = (key[1][0] * pair[0] + key[1][1] * pair[1]) % 26;
            result.append((char) (c1 + 'A')).append((char) (c2 + 'A'));
        }
        return result.toString();
    }

    static String decrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            int[] pair = { text.charAt(i) - 'A', text.charAt(i + 1) - 'A' };
            int p1 = (inverseKey[0][0] * pair[0] + inverseKey[0][1] * pair[1]) % 26;
            int p2 = (inverseKey[1][0] * pair[0] + inverseKey[1][1] * pair[1]) % 26;
            result.append((char) ((p1 + 26) % 26 + 'A')).append((char) ((p2 + 26) % 26 + 'A'));
        }
        return result.toString();
    }

    static int[][] invertKeyMatrix(int[][] m) {
        int det = m[0][0] * m[1][1] - m[0][1] * m[1][0];
        det = ((det % 26) + 26) % 26;
        int invDet = -1;
        for (int i = 0; i < 26; i++) {
            if ((det * i) % 26 == 1) {
                invDet = i;
                break;
            }
        }
        int[][] inv = new int[2][2];
        inv[0][0] = m[1][1];
        inv[1][1] = m[0][0];
        inv[0][1] = -m[0][1];
        inv[1][0] = -m[1][0];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                inv[i][j] = ((inv[i][j] * invDet) % 26 + 26) % 26;
        return inv;
    }
}
