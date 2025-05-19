import java.util.Scanner;

public class Que16 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter message: ");
        String message = sc.nextLine().replaceAll("\\s+", "").toUpperCase();
        int size = 5;
        int length = message.length();
        int paddedLength = ((length + size - 1) / size) * size;
        char[] padded = new char[paddedLength];
        for (int i = 0; i < paddedLength; i++) {
            if (i < length) padded[i] = message.charAt(i);
            else padded[i] = 'X';
        }
        char[][] matrix = new char[paddedLength / size][size];
        int k = 0;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < size; j++)
                matrix[i][j] = padded[k++];
        StringBuilder encrypted = new StringBuilder();
        for (int j = 0; j < size; j++)
            for (int i = 0; i < matrix.length; i++)
                encrypted.append(matrix[i][j]);
        System.out.println("Encrypted Message: " + encrypted);
        char[][] decryptMatrix = new char[matrix.length][size];
        k = 0;
        for (int j = 0; j < size; j++)
            for (int i = 0; i < matrix.length; i++)
                decryptMatrix[i][j] = encrypted.charAt(k++);
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < decryptMatrix.length; i++)
            for (int j = 0; j < size; j++)
                decrypted.append(decryptMatrix[i][j]);
        System.out.println("Decrypted Message: " + decrypted);
        sc.close();
    }
}
