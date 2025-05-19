import java.util.Scanner;
public class Que4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter source IP address:");
        String srcIP = scanner.nextLine();
        System.out.println("Enter MAC address:");
        String mac = scanner.nextLine();
        if (isSuspiciousARP(srcIP, mac)) {
            System.out.println("Alert: Potential ARP spoofing detected from IP " + srcIP + " with MAC " + mac);
        } else {
            System.out.println("No suspicious ARP activity detected.");
        }
        scanner.close();
    }
    public static boolean isSuspiciousARP(String ip, String mac) {
        String validMacForIP = "00:1A:2B:3C:4D:5E";
        return !mac.equals(validMacForIP);
    }
}