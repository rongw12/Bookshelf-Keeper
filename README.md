# Bookshelf-Keeper


This is a program to interact with a bookshelf-keeper, that maintains a shelf of books in increasing order by height. The program enables users to perform a series of put and pick operations via a BookshelfKeeper object. The pick operation refers to a case where you need to pick a book from a given position (index), and the put operation refers to putting a book of given height on the shelf, such that the bookshelf remains sorted after completing any operation.  The only information we store about a book is its height.


When doing pick or put operations, the BookshelfKeeper is only allowed to access books on the bookshelf from one of the two ends, so books don’t fall over.  Additionally, it must carry out the pick or put operation such that it minimizes the number of books moved this way.  


The user input consists of the initial state of our bookshelf and a series of pick and put operations to perform; for each operation it will print out the contents of the updated bookshelf, the number of lower-level book-moving operations used, and the total number of lower-level operations used since the start of the program. 


Here are two examples of runing the program in the Linux shell::


java -ea BookshelfKeeperProg

java -ea BookshelfKeeperProg < inputFile > outputFile



Bookshelf.java

The interface for the Bookshelf class, that abstracts the idea of arranging books into a bookshelf so books don’t fall down.


BookshelfKeeper.java 

The interface for the BookshelfKeeper class, that enables users to perform efficient put or pick operation on a bookshelf while maintaining it in a sorted state.


BookshelfKeeperProg.java 

A terminal-based interactive program that allows the user to perform a series of pick and put operations on a BookshelfKeeper object; contains the main method.  It can also be run in a batch mode by using input and output redirection.


A note about the System.in Scanner
This (and all Java programs that read from the console) should only have one Scanner object to read from System.in. If you make multiple such Scanner objects your program will not work with our test scripts. You will also have problems if you try to open and close multiple Scanners from System. Once you create that one Scanner, you can pass it as a parameter to other methods to be able to use it in different places. Here is a little program with an example of this:


public class MyClass {

   public static void main(String[] args) {

      Scanner in = new Scanner(System.in);  // create the Scanner

      . . .

      int dataVal = in.nextInt();  // using in directly in main

      . . .

      // readAndValidateString will also read some more input

      String moreData = readAndValidateString(in); // pass in as a param here

      . . .

   }


   // prompts for a String from "in", reads it, and validates it.

   private static String readAndValidateString (Scanner in) {

      // don't create another Scanner for Sytem.in here; use the parameter instead

      . . .

      String theString = in.next();

      . . .

      return theString;

   }

   . . .

}


You can create Scanner objects that have different data sources, should only have one that was created using
new Scanner(System.in)
