import java.util.Scanner;
// import java.util.*;
// import java.util.Arrays;
// import javax.lang.model.util.ElementScanner6;

//Written by Serena Drouillard
//March 6, 2019
//Transposition Cipher class with encription and decription functions

public class TranspositionCipher{
    public static void main(String[] args){
        Scanner client= new Scanner(System.in);
        System.out.println("Hey, enter 1 for encrypting services or 2 for decrypting services. ");
        int service=0;
        while(service!=1 && service!=2){
            service= client.nextInt();

            if (service==1){
                client.nextLine();
                System.out.println("Please enter the String to encrypt, with % instead of spaces: ");
                String message= client.nextLine();
                System.out.println("How many columns in the cipher matrix? ");
                int columns= client.nextInt();
                client.nextLine();
                //first make the matrix with columns given
                int rows= (int) Math.ceil((double)message.length()/ columns);
                char[][] matrix= new char[rows][columns];
                //now add the message to your matrix
                int counter=0;
                for(int i=0; i<rows; i++){
                    for(int j=0; j<columns; j++){
                        if(counter < message.length()){
                            matrix[i][j]= message.charAt(counter);
                            counter++;
                        }
                        else
                            matrix[i][j]= '%';
                    // System.out.print(matrix[i][j]);
                    }
                    //System.out.println();
                }
                client.close();
                //now you have a matrix, get the encrypted message and figure out the column order to get the key
                System.out.println("Send over the encrypted message, lets figure this key shit out.");
                String encryptedMessage= client.nextLine();
                char[] encryptMessage= encryptedMessage.toCharArray();
                int[] key= new int[columns];

                //take the first letter of the encrypted message, match it to a column in the first row of the matrix
                //there can be many matches...
                counter=0;
                int keys=0;
                boolean match= false;
                for(int x=0; x<columns; x ++){//this searches through all of the columns for every letter in the encrypted column
                    for (int i=0; i<columns; i++){ 
                        for(int j=0; j<rows; j++){ 
                                //go through the columns and see if it matches the first part of message...

                            //go to next column if the char doesn't match row 0 char of column
                                if(encryptMessage[counter]!=matrix[j][i]){
                                    match=false;
                                    break;
                                }
                                match=true;
                                counter++;
                        }
                        //return column that matched to the end of the rows now
                        if (match==true){
                            //System.out.println("Got em bitch! " + i);
                            key[keys]=i;
                            keys++;
                            break;
                        }
                    }
                }

                // System.out.println("What is the permutation key? ");
                System.out.print("[KEY: ");
                for(int i=0; i<columns; i++){
                    System.out.print(key[i]+ " ");
                }
                System.out.println("]");
                System.out.println("Dude, come on, that wasn't even hard.");
                break;
            }

            else if(service==2){
                client.nextLine();
                System.out.println("~~*~~*~~DeCrYpToNaToRrRrRrR~~*~~*~~");
                System.out.println("Ok, give me the crazy looking cipher text, with % instead of spaces.");
                String cipherText= client.nextLine();
                char[] cipherT= cipherText.toCharArray(); //gunna have to look at each letter in a sec

                System.out.println("Ok, now the key. How many numbers are in it? Just make this easy for me.");
                int keyNum= client.nextInt();
                int[] key= new int[keyNum];
                System.out.println("Ok cool, lets hear it then.");
                for(int b=0; b< keyNum; b++){
                    key[b]=client.nextInt();
                }
                // kk so we have the number of keys, that's the number of columns in the matrix
                int columns= keyNum;
                // we have the length of the message, so that divided by the columns will give us the number of rows.
                int rows= (int) Math.ceil((double)cipherText.length()/ columns); //need to round up to the next whole number
                //sick, now we can fill the matrix with the letters in the proper columns and then just read it.
                char[][] matrix= new char[rows][columns];
                int counter=0;
                for(int i=0; i<columns; i++){
                    for(int j=0; j<rows; j++){ //we're going down each column, not across each row
                        //each key is a column number that we fill 
                        matrix[j][key[i]-1]=cipherT[counter]; //I subtracted 1 because the array indexes are 0-4 not 1-5
                        counter++; //or you'll just keep adding the same character to the matrix...
                    }
                }
                //kk now we should just be able to read through the matrix to see the message
                for(int i=0; i<rows; i++){
                    for(int j=0; j<columns; j++){ // notice proper for-loop order now
                        System.out.print(matrix[i][j]);
                    }
                }
                System.out.println("\n\nK BYEEEE");

                break;
            }

            System.out.println("bruh really... try again.");
        }
    }
}