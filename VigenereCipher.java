import java.util.Scanner;
// import java.util.*;
// import java.util.Arrays;
// import javax.lang.model.util.ElementScanner6;

//Written by Serena Drouillard
//March 5, 2019
//Vigenere Cipher class with encription and decription functions

public class VigenereCipher{
    public static void main(String[] args){
        Scanner client= new Scanner(System.in);
        //the user chooses encryption or decription service with the following variable
        String[] alphabett= new String[] {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        
        //make & print the vigenere table
        int z=0;
        String VTable[][]= new String[26][26];
        for (int i=0; i<26; i++){
            z=i;
            for (int y=0; y<26; y++){
                VTable[i][y]=alphabett[z];
                //System.out.print(VTable[i][y]);
                z++;
                z=z%26;
            }
            //System.out.println();
        }

        System.out.println("Enter a string to encrypt: ");
        String plainText= client.nextLine();
        System.out.println("Enter a keyword to use for the encryption: ");
        String keyword= client.nextLine();

        System.out.print("Excrypted string: ");
        client.close();
        //seperate strings into letters
        plainText = plainText.toLowerCase();
        char[] plainTextLetters= plainText.toCharArray();
        keyword = keyword.toLowerCase();
        char[] keywordLetters= keyword.toCharArray();

        //for each letter of each, find the coordinates in the table
        //row== plainttext
        //column== keyword
        String[] encrypted= new String[plainText.length()];
        for(int i=0; i<plainText.length(); i++){
            //run through the original message
            char row=plainTextLetters[i];
            int x= i%keyword.length();
            char column=keywordLetters[x];
            int rowNum= indexOf(alphabett, row);
            int colNum= indexOf(alphabett, column);
           encrypted[i]= VTable[rowNum][colNum];
        }

        for (int a=0 ; a < plainText.length(); a++){
            System.out.print(encrypted[a]);
        }
        System.out.println();
    }
    public static int indexOf(String[] list, char x){
        String a= Character.toString(x);
        for (int i=0; i<list.length; i++){
            if (list[i].equals(a))
                return i;
        }
        return -1;
    }
}