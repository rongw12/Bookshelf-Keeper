import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class BookshelfKeeperProg
 *
 * Thoroughly test the BookshelfKeeper class with error checkings.
 * Can call putPos and pickHeight according to instructions from keyboard of input files.
 * Will perform on minimum number of operations to call the mutators.
 *
 * Error checkings: 
 * 1. Check if the list of numbers entered for initial arrangement of the bookshelf are all positive 
 * 2. Check if are also in non-decreasing order.
 * 3. Once the initial configuration is read the only commands allowed are pick, put, and end.  
 * 4. For put, check if the the height is given to be positive
 * 5. For pick, check if the position is given to be within the valid bounds for bookshelf.
 */

public class BookshelfKeeperProg
{
   /**
    * Call all the error-checking mothods to check the inpout.
    * Print out the current BookshelfKeeper informration, heights of all books.
    * Print out the operations took to complete this instruction, and total operations so far.
    *
    * @param test: an array list that has integers in it representing the book height.
    * @param testBookshelf: a Bookshelf converted from test.
    * @param testBookshelfKeeper: a BookshelfKeeper converted from testBookshelf.
    * @param action: Pick, put or end.
    */
   public static void main(String[] args){
      System.out.println("Please enter initial arrangement of books followed by newline:");
      Scanner in = new Scanner(System.in);
      int operations = 0;
      
      ArrayList<Integer> test = strToInts(in); // the arraylist now has all input numbers.
      if (errorCheckPositive(test) && errorCheckNonDec(test)){ // check if the input numbers are valid.
         Bookshelf testBookshelf = new Bookshelf(test);
         BookshelfKeeper testBookshelfKeeper = new BookshelfKeeper(testBookshelf); // convert the arraylist into BookshelfKeeper.
         // System.out.println(testBookshelfKeeper.toString() + " " + operations + " " + testBookshelfKeeper.getTotalOperations()); 
         System.out.println(testBookshelfKeeper.toString()); // toString already has the two number of operations in it.
         
         System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");
         String action = in.next(); // read the string of instruction, assume operation line is not empty.
         while (errorCheckAction(action)){
            if (action.equals("put")){
               int putBookHeight = in.nextInt();
               if (errorCheckPut(putBookHeight)){
                  operations = testBookshelfKeeper.putHeight(putBookHeight);
                  System.out.println(testBookshelfKeeper.toString());
               }
               else{
                  break; // if not valid, get out of the while loop and exit the program.
               }
            }
            else{ // the action is "pick"
               int pickPosition = in.nextInt();
               if (errorCheckPick(pickPosition, testBookshelfKeeper)){                
                  operations = testBookshelfKeeper.pickPos(pickPosition);
                  System.out.println(testBookshelfKeeper.toString());
               }
               else{
                  break; // if not valid, get out of the while loop and exit the program.
               }
            }
            action = in.next();         
         }
      } 
   }
   
   
   /**
    * Convert the string read in to numbers and add these numbers to the arraylist.
    * @param in: the Scanner object to read the input from keyboard or files.
    * @param arrayOfInts: The arraylist of the pile of books.
    */
   private static ArrayList<Integer> strToInts(Scanner in){
      ArrayList<Integer> arrayOfInts = new ArrayList<Integer>(); // The arraylist of the pile of books.
      if (in.hasNextLine()){
         Scanner strOfInts = new Scanner(in.nextLine()); // read the whole next line, make the nextline a new scanner.
         while (strOfInts.hasNextInt()){ // convert the string in to numbers and add them to the arraylist.
            arrayOfInts.add(strOfInts.nextInt());
         }
      }
      return arrayOfInts;      
   }
   
   
   /**
    * Check if the list of numbers entered for initial arrangement of the bookshelf are all positive.
    * static method, otherwise non-static method errorCheckPositive(ArrayList<Integer>) cannot be referenced from a static context
    * 
    * @param test: the arraylist that has all height of books in it.
    */
   private static boolean errorCheckPositive(ArrayList<Integer> test){
      for (int i=0; i < test.size(); i++){
         if (test.get(i)<1){
            System.out.println("ERROR: Height of a book must be positive.");
            System.out.println("Exiting Program.");
            return false;
         }
      }
      return true;
   }
   
   
   
   /**
    * Check if the list of numbers entered for initial arrangement of the bookshelf are in non-decreasing order.
    * @param test: the arraylist that has all height of books in it.
    */
   private static boolean errorCheckNonDec(ArrayList<Integer> test){
      for (int i=0; i < test.size()-1; i++){
         if (test.get(i) > test.get(i+1)){
            System.out.println("ERROR: Heights must be specified in non-decreasing order.");
            System.out.println("Exiting Program.");
            return false;
         }
      }
      return true;
   }
   
                
   /**
    * Check if the action is valid (pick or put) or end.
    * @param action: the action want to take.
    */
   private static boolean errorCheckAction(String action){
      if (action.equals("end")){ // use equals(), not  ==
         System.out.println("Exiting Program.");
         return false;
      }
      else if (action.equals("pick") || action.equals("put")){
         return true;
      }
      else{
         System.out.println("ERROR: Operation should be either pick or put.");
         System.out.println("Exiting Program.");
         return false;
      }
   }
         
                    
   /**
    * For put, check if the height given is positive.
    * @param putBookHeight: the height of book you want to put into the bookshelf.
    */
   private static boolean errorCheckPut(int putBookHeight){
      if (putBookHeight > 0){
         return true;
      }
      else{
         System.out.println("ERROR: Height of a book must be positive.");
         System.out.println("Exiting Program.");
         return false;
      }
   }
    
                  
   /**
    * For pick, the position given must be within the valid bounds for bookshelf.
    * @param pickPosition: The position you want to pick from the bookshelf.
    * @param testBookshelfKeeper: the BookshelfKeeper that you want to pick from.
    */
   private static boolean errorCheckPick(int pickPosition, BookshelfKeeper testBookshelfKeeper){
      if (pickPosition >= 0 && pickPosition < testBookshelfKeeper.getNumBooks()){
         return true;
      }
      else{
         System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
         System.out.println("Exiting Program.");
         return false;
      }
   }                                                        
                               
}
