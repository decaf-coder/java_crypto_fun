import java.util.Scanner;
// import java.util.*;
// import java.util.Arrays;
// import javax.lang.model.util.ElementScanner6;

//Written by Serena Drouillard
//March 5, 2019
//Caesar Cipher class with encription and decription functions

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner client = new Scanner(System.in);
        // the user chooses encryption or decription service with the following variable
        boolean correctService = false;
        System.out.println();
        System.out.println("*********************************************************************************");
        System.out.println("***********************************  WELCOME  ***********************************");
        System.out.println("*******************************************'**************************************\n");
        System.out.println("Please enter 1 for encription services or 2 for decription services");
        int service = client.nextInt();
        client.nextLine();
        // this while loop protects against improper service number chosen
        while (!correctService) {
            if (service == 1) {
                System.out.println("Please enter the string to be encrypted");
                String m = client.nextLine();
                System.out.println("Please enter the integer value of the key");
                int k = client.nextInt();
                encrypter(m, k);
                correctService = true;
            } else if (service == 2) {
                System.out.println("Please enter the string to be decrypted");
                String c = client.nextLine();
                System.out.println("Do you know the key? yes or no");
                String key = client.nextLine();
                if (key.equals("yes")) {
                    // enter the key now
                    System.out.println("Please enter the integer value of the key");
                    int k = client.nextInt();
                    decrypter(c, k);
                    correctService = true;
                }
                if (key.equals("no")) {
                    // have to guess the key then
                    System.out.println(
                            "Please enter true or t for a correct decryption and false or f for an incorrect decryption after each decryption presented");
                    boolean decryption = false;
                    int x = 1;

                    while (decryption == false) {
                        int k = x;
                        decrypter(c, k);
                        x++;
                        System.out.println("true/t or false/f?");
                        String decryptionString = client.nextLine();
                        decryptionString.toLowerCase();
                        if (decryptionString.equals("true") || decryptionString.equals("t"))
                            decryption = true;
                        if (x == 26) {
                            System.out.println("These are all of the possible decryptions! Sorry!");
                            decryption = true;
                        }
                    }
                    correctService = true;
                }
            } else {
                System.out.println("Please enter 1 or 2");
                service = client.nextInt();
            }

        }
        System.out.println("*********************************************************************************");
        System.out.println("*********************** Thank you for using this service. ***********************");
        System.out.println("*********************************************************************************");
        client.close();
    }

    public static int indexOf(char[] list, char x) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == x)
                return i;
        }
        return -1;
    }

    // m is the plain text, k is the key, a value between 1-25
    // returns an encrypted string (c), or cipher text of given string
    public static void encrypter(String m, int k) {
        System.out.print("ENCRYPTING");
        try {
            Thread.sleep(400);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.print(" . ");
        try {
            Thread.sleep(400);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.print(" . ");
        try {
            Thread.sleep(400);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println(" . ");

        m = m.toLowerCase();
        k = k % 26;
        char[] c = m.toCharArray();
        char[] alphabet = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        System.out.print("      -----------------------");
        for (int z = 0; z < m.length(); z++)
            System.out.print("-");
        System.out.println();

        System.out.print("      | Encrypted message: ");

        for (int i = 0; i < m.length(); i++) {
            char letter = c[i]; // each letter of string entered
            int index = indexOf(alphabet, letter); // finds index of letter in alphabet (0-25)
            if (index != -1) {
                index = index + k; // new index value with key value added
                if (index > 25) // to loop around the alphabet
                    index = index % 25;
                c[i] = alphabet[index];
            }
            System.out.print(c[i]);
        }
        System.out.println(" |");
        System.out.print("      -----------------------");
        for (int z = 0; z < m.length(); z++)
            System.out.print("-");
        System.out.println("\n");

    }

    // c is the cipher text to be decrypted
    // returns a plain text string (m)
    public static void decrypter(String c, int k) {
        System.out.print("DECRYPTING");
        try {
            Thread.sleep(400);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.print(" . ");
        try {
            Thread.sleep(400);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.print(" . ");
        try {
            Thread.sleep(400);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println(" . ");

        c = c.toLowerCase();
        k = k % 26;

        char[] m = c.toCharArray();
        char[] alphabet = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        System.out.print("      -----------------------");
        for (int z = 0; z < c.length(); z++)
            System.out.print("-");
        System.out.println();

        System.out.print("      | Decrypted message: ");
        for (int i = 0; i < c.length(); i++) {
            char letter = m[i]; // each letter of string entered
            // System.out.println("here- " + letter);
            int index = indexOf(alphabet, letter); // finds index of letter in alphabet (0-25)

            if (index != -1) {
                index = index - k; // new index value with key value added
                if (index < 0)
                    index = index + 26;
                index = index % 26;
                m[i] = alphabet[index];
            }
            System.out.print(m[i]);
        }
        System.out.println(" |");
        System.out.print("      -----------------------");
        for (int z = 0; z < c.length(); z++)
            System.out.print("-");
        System.out.println("\n");
    }
}
