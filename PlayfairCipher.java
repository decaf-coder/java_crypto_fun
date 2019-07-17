import java.util.Scanner;
// import java.util.*;
// import java.util.Arrays;
// import javax.lang.model.util.ElementScanner6;

//Written by Serena Drouillard
//March 6, 2019
//Transposition Cipher class with encription and decription functions

public class PlayfairCipher{
    public static void main(String[] args){
        Scanner client= new Scanner(System.in);
        char[] alphabet= new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        System.out.println("Hey, we're gunna make a key matrix here, so what's the key phrase? ");
        String keyPhrase= client.nextLine();
        keyPhrase= keyPhrase.toLowerCase();
        char[] keyP= keyPhrase.toCharArray();
        //now we have to actually make the matrix, it's only 5x5 with no J's
        char[][] matrix= new char[5][5];
        int counter=0;
        int alphCounter=0;
        for(int i=0; i< 5; i++){
            for(int j=0; j< 5; j++){
                if(counter>=keyPhrase.length()){ //ran out of letters, now we go to the alphabet
                    while(contains(matrix, alphabet[alphCounter]) || alphabet[alphCounter]=='j'){//check if the leter is already in this bitch, or if it's j... cause no j's
                        alphCounter++;
                    }
                    matrix[i][j]=alphabet[alphCounter];
                    alphCounter++;
                }
                else if(counter<keyPhrase.length()){
                    if(keyP[counter]==' '){
                        counter++;
                    }
                    if(contains(matrix, keyP[counter]))
                        counter++;
                    matrix[i][j]=keyP[counter];
                    counter++;
                }
                System.out.print(matrix[i][j]);
            }
        System.out.println();
        }

        //kk coooool, we have the matrix, now the fun
        System.out.println("Ok, now what's the message you want to encrypt?");
        String message= client.nextLine();
        message= message.toLowerCase();
        //lets just take out the spaces here
        message = message.replaceAll("\\s","");
        char[] messageArray= message.toCharArray();
        client.close();

        //i'm gunna try a matrix for this, cause why not
        int rows= message.length()/2;
        if(message.length()%2==1) //if it's odd we'll just change that..
            rows= (message.length()+1)/2;
        char[][] pairs= new char[rows][2];//2 columns because it's pairs

        //ok now add all the pairsssss
        counter=0;
        for(int i=0; i<rows; i++){
            for(int j=0; j<2; j++){ 
                if(counter>=message.length()){
                    pairs[i][j]='z'; //pad it 
                    System.out.print(pairs[i][j]);
                    break;
                }
                //need to check the pairs to make sure there aren't any
                if(j==1){//check second column of row
                    if(messageArray[counter]==(pairs[i][j-1])){ //they equal! so make column 2 an 'x' and move to the next row
                        pairs[i][j]='x';
                        //System.out.print(pairs[i][j]);
                        break;
                    }
                }
                pairs[i][j]=messageArray[counter];
                counter++;
                //System.out.print(pairs[i][j]);
            }
            //System.out.println();
        }
        //okayyy, now we have all the pairs sorted out, we need to use the key matrix to encrypt!!
        //need to find the indexes of each pair in key matrix
        for(int i=0; i<rows; i++){
                //for each pair, replace char with new char , pairs[i][1]=
                System.out.print(find(0, pairs[i][0], pairs[i][1], matrix));
                System.out.print( find(1, pairs[i][0], pairs[i][1], matrix) +" ");
        }

        System.out.println("\nOK BYEE");

    }
    //this looks dumb, it only returns one pair-mate at a time, because, whatever it's late
    public static char find(int pair, char a, char b, char[][] matrix){
        //need to store coordinates
        int[][] coordinates= new int[2][2];
        for(int i=0; i< 5; i++){
            for(int j=0; j< 5; j++){
                //I need to look through this whole matrix and return new chars for the pairs
                if(matrix[i][j]==a){ //then we found a pair
                    coordinates[0][0]=i;
                    coordinates[0][1]=j;
                }
                if(matrix[i][j]==b){ //then we found a pair
                    coordinates[1][0]=i;
                    coordinates[1][1]=j;
                }
            }
        }
        //have the coordinates for the pairs now
        //the pairs are a(row 0) and b(row 1)
        //there are 3 rules to follow
        //if same column, replace each with letter just below
        if(coordinates[0][1]==coordinates[1][1]){
            int row= ((coordinates[0][0])+1)%5;
            a=matrix[row][coordinates[0][1]];
            row= ((coordinates[1][0])+1)%5;
            b=matrix[row][coordinates[0][1]];
            if(pair==0)
                return a;
            if(pair==1)
                return b;
        }
        //if same row, replace each with letter just to the right
        else if(coordinates[0][0]==coordinates[1][0]){
            int column= ((coordinates[0][1])+1)%5;
            a=matrix[coordinates[0][0]][column];
            column= ((coordinates[1][1])+1)%5;
            b=matrix[coordinates[1][0]][column];
            if(pair==0)
                return a;
            if(pair==1)
                return b;
        }
        //if both diff, switch columns
        else {
            a=matrix[coordinates[0][0]][coordinates[1][1]];
            b=matrix[coordinates[1][0]][coordinates[0][1]];

            if(pair==0)
                return a;
            if(pair==1)
                return b;
        }
        return '~';
    }

    //I think you can figure out what this does
    public static boolean contains(char[][] matrix, char x){
        for(int i=0; i< 5; i++){
            for(int j=0; j< 5; j++){
                if(matrix[i][j] == x)
                    return true;
            }
        }
        return false;
    }

}