import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.SecureRandom;
import java.util.Scanner;

public class Que12 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter path to the video file: ");
        String inputFile = sc.nextLine();
        System.out.print("Enter output encrypted file name: ");
        String encryptedFile = sc.nextLine();
        System.out.print("Enter output decrypted file name: ");
        String decryptedFile = sc.nextLine();
        System.out.print("Enter 16-character AES key: ");
        String keyString = sc.nextLine();

        byte[] keyBytes = keyString.getBytes();
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

        encryptFile(key, new File(inputFile), new File(encryptedFile));
        decryptFile(key, new File(encryptedFile), new File(decryptedFile));
        System.out.println("Encryption and decryption complete.");
        sc.close();
    }

    public static void encryptFile(SecretKey key, File inputFile, File outputFile) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        FileInputStream fis = new FileInputStream(inputFile);
        FileOutputStream fos = new FileOutputStream(outputFile);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            byte[] output = cipher.update(buffer, 0, bytesRead);
            if (output != null) fos.write(output);
        }
        byte[] outputBytes = cipher.doFinal();
        if (outputBytes != null) fos.write(outputBytes);
        fis.close();
        fos.close();
    }

    public static void decryptFile(SecretKey key, File inputFile, File outputFile) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        FileInputStream fis = new FileInputStream(inputFile);
        FileOutputStream fos = new FileOutputStream(outputFile);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            byte[] output = cipher.update(buffer, 0, bytesRead);
            if (output != null) fos.write(output);
        }
        byte[] outputBytes = cipher.doFinal();
        if (outputBytes != null) fos.write(outputBytes);
        fis.close();
        fos.close();
    }
}
