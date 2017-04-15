package lab7;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Jacob Ragains
 */
public class Assignment5 {

    /**
     * counter for the words found
     */
    public static Long wordsFound=0L;

    /**
     * counter for the words not found
     */
    public static Long wordsNotFound=0L;

    /**
     * counter for the number of searches for words found
     */
    public static Long searchesFound=0L;

    /**
     * counter for the number of searches for words not found
     */
    public static Long searchesNotFound=0L;

    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {
        BinarySearchTree[] dictionary = new BinarySearchTree[26];
        for (int i = 0; i < dictionary.length; i++) {
            dictionary[i] = new BinarySearchTree<String>();
        }
        File f = new File("./src/lab7/random_dictionary.txt");
        Assignment5 a = new Assignment5();
        a.fillBST(dictionary, f);
        File f2 = new File("./src/lab7/oliver.txt");
        a.wordSearch(dictionary, f2);
        System.out.println(wordsFound);
        System.out.println(searchesFound+searchesNotFound);
        System.out.println(wordsNotFound);
        System.out.println(searchesNotFound/wordsNotFound);
        System.out.println(searchesFound/wordsFound);
    }

    /**
     * This method fills an array of binary search trees to create a dictionary
     * @param array the empty binary search tree array
     * @param f the file that will be used to fill the dictionary
     */
    public void fillBST(BinarySearchTree[] array, File f) {
        try {
            Scanner input = new Scanner(f);
            String s;

            while (input.hasNext()) {
                s = input.next();
                s = s.toLowerCase();

                array[s.charAt(0) - 97].insert(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        }
    }

    /**
     * this method takes the dictionary and compares it to a block of text 
     *      to see how many words are found
     * @param array the binary search tree array that is being used as a dictionary
     * @param f the file that is being compared to the dictionary
     */
    public void wordSearch(BinarySearchTree[] array, File f) {
        try {
            Scanner input = new Scanner(f);
            String s;
            Boolean found;
            String delims = "[ .,!?\"*#():\\]\\[\\-;]+";
            while (input.hasNext()) {
                s = input.next();
                s = s.toLowerCase();
                String[] parts = s.split(delims);
                s = "";
                if(parts.length==0){
                    s="*";
                }
                if (parts.length == 1) {
                    s=parts[0];
                } 
                else {
                    for (int i = 0; i < parts.length; i++) {
                        s = s + parts[i];
                    }
                }
                if (s.charAt(0) - 97 <= 26 && s.charAt(0) - 97 >= 0) {
                    array[s.charAt(0) - 97].search(s);

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }
}
