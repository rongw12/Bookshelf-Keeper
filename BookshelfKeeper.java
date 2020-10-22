// Name: Rong Wang
// USC NetID: rwang424
// CSCI455 PA2
// Fall 2020

import java.util.ArrayList;

/**
 * Class BookshelfKeeper 
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in 
 * non-decreasing order by height, with the restriction that single books can only be added 
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put 
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
public class BookshelfKeeper {

  /**
      Representation invariant:

      <rep. invar. comment>
      1: Height of all books should be positive integers.
      2: The BookShelfKeeper should be valid all the time, always in non-decreasing order of height.
	  3：Total number of calls can not be negative.
	  4: Number of calls for last operation can not be negative.
   */
   
   // <instance variables>
   /**
    @ param keeper: The books currently on the bookshelf.
    @ param totalOperations: Total number of calls make to mutators  on the contaied bookshelf.
    @ param oprations: The number of calls that took to complete the operation.  
    For the removedBooks, I made them local and Bookshelf.
    @ param removedBooks: The books that are removed from the booksheld during one operation, will be put back.
    */
   private Bookshelf keeper;
   private int totalOperations;
   private int operations;
   // private ArrayList<Integer> removedBooks;
   
   
   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {
      keeper = new Bookshelf();
      operations = 0;
      totalOperations = 0;
      // removedBooks = new ArrayList<Integer>();
      assert isValidBookshelfKeeper();
   }
   

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *
    * @param sortedBookshelf: the input Bookshelf object.
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {
      assert sortedBookshelf.isSorted();
      if (sortedBookshelf.isSorted()){
         keeper = sortedBookshelf;
         operations = 0;
         totalOperations = 0;
         // removedBooks = new ArrayList<Integer>();
      }
      assert isValidBookshelfKeeper();
   }
   

   /**
    * Removes a book from the specified position in the bookshelf and keeps bookshelf sorted 
    * after picking up the book.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * 
    * @param position: the index of the book should be picked from the bookshelf.
    * @param removedBooks: the books need to be removed before removing the target book.
    *
    * PRE: position must be in the range [0, getNumBooks()).
    */
   public int pickPos(int position) {
      assert position >= 0 && position < getNumBooks();
      operations = 0;
      Bookshelf removedBooks = new Bookshelf();
      if (position <= getNumBooks()-1-position){ // pick from the front to minimize the operations
         removedBooks = removeFrontBooks(removedBooks, position, operations);//remove books from 0 to position-1.
         
         keeper.removeFront(); // remove the target book which was initially on the position.
         operations++;
         
         operations += putBackFrontBooks(removedBooks)*2; //pick and put back other books need the same amount of operations.
         // The remove methods can only return removedBooks, can not return the operations it took to remove the books
         // But when put them back, we can return the number of operations took, which is the same number, so *2.     
      }
      else{ // pick from the end of the bookshelf.
         removedBooks = removeEndBooks(removedBooks, position, operations);//remove books from position+1 to the end.
         
         keeper.removeLast();
         operations++;
         
         operations += putBackEndBooks(removedBooks) * 2; //pick and put back other books need the same amount of operations.
      }
      totalOperations += operations;
      assert isValidBookshelfKeeper();
      return operations;   
   }
   

   /**
    * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted 
    * after the insertion.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * 
    * @param height: the height of the book needs to be put to the bookshelf.
    * @param removedBooks: a Bookshelf contains books need to be removed before putting down the target book.
    * @param removedHeight: The height of the books goe removed each time.
    * @param lengthEnd: The number of books need to be removed if start from the end.
    * @param lengthFront: The number of books need to be removed if start from the front.
    *
    * PRE: height > 0
    */
   public int putHeight(int height) {
      assert height > 0;
      if (keeper.size()<=0){
         keeper.addLast(height); 
         operations = 1;
         totalOperations += operations;
         return operations;
      }
      operations = 0;
      Bookshelf removedBooks = new Bookshelf();
      int removedHeight = 0;
 
   // This method is for when books all have distinct heights.   
      /**
      If the size is odd, then size/2 will be the middle element in the keeper.
      So compare the height we want to add with the middle value in the keeper.
      If the height we want to add is > middle value, we should go from the end of the keeper.
      If the height == the middle value, either way is fine, we say go from the end of the keeper.
      In the two cases above, we won't touch the middle value, only pick up the books from the end to position size/2+1.
      If height < middle value, we go from the front of the keeper.
         
      If the size if even, we have 2 middle values, size/2 will be the right middle value.
      If height > right middle value, we definitely go from the end.
      If height ==  right middle value, we also go from the end.
      In the two cases above, we we won't touch the right middle value, only from the end to position size/2+1.
      If height < right middle value, we go from the front
      At most we will move half of the books on the keeper, iif the height we want to add is between the two middle values.
      */
      // int comparedMiddleIndex = (int) keeper.size()/2;
      // int comparedMiddleIndexValue = keeper.getHeight(comparedMiddleIndex);
      // if (height >= comparedMiddleIndexValue){
      
   // This method is for when some books have same height.
      int lengthEnd = lengthFromEnd(height);
      int lengthFront = lengthFromFront(height);
      if (lengthEnd <= lengthFront){
         
         removedBooks = removeEndUtilHeight(removedBooks, height);
         
         keeper.addLast(height); // add the height we want to add into the keeper.
         operations++;
         
         operations += putBackEndBooks(removedBooks)*2; // put all the removed books back.        
      }  
      else{
         removedBooks = removeFrontUtilHeight(removedBooks, height);
         
         keeper.addFront(height); // add the height we want to add into the keeper.
         operations++;
         // System.out.println(operations);    
         
         operations += putBackFrontBooks(removedBooks)*2; // put all the removed books back.  
      }
      totalOperations += operations;
      assert isValidBookshelfKeeper();
      return operations;  
   }
   

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {
      assert isValidBookshelfKeeper();
      return totalOperations;   // dummy code to get stub to compile
   }
   

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {
      assert isValidBookshelfKeeper();
      return keeper.size(); 
   }
   

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String containing height
    * of all books present in the bookshelf in the order they are on the bookshelf, followed 
    * by the number of bookshelf mutator calls made to perform the last pick or put operation, 
    * followed by the total number of such calls made since we created this BookshelfKeeper.
    * 
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    * 
    */
   public String toString() {
      assert isValidBookshelfKeeper();
      return "" + keeper + " " + operations + " " + totalOperations;   
   }
   

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state.
    * (See representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() { 
      return keeper.isSorted() && totalOperations > 0;  // check if the keeper is in non-decreasing order.
   }

   
   // private methods below
   
   /**
    * Remove the books from keeper between 0 and position-1.
    * Put these removed books to removedBooks.
    * The first one of removedBooks is the last one got removed, was at position-1.
    * Can be used when picking the book with known position.
    *
    * @param removedHeight: the height of the removed book.
    */
   private Bookshelf removeFrontBooks(Bookshelf removedBooks, int position, int operations){ 
      int removedHeight = 0; // the height of the removed book
      for (int i=0; i<position; i++) // remove all the books from 0 to position-1, and add them to the removedBooks
      {
         removedHeight = keeper.removeFront();
         removedBooks.addFront(removedHeight); // The first one of removedBooks is the last one got removed, was at position-1.
         operations++;
      }
      return removedBooks;
   }
   
   
   /**
    * Remove the books from keeper from position+1 to the end.
    * Put these removed books to removedBooks.
    * The first one of removedBooks is the last one got removed, was at position-1.
    * Can be used when picking the book with known position.
    *
    * @param removedHeight: the height of the removed book.
    */
   private Bookshelf removeEndBooks(Bookshelf removedBooks, int position, int operations){ 
      int removedHeight = 0; // the height of the removed book
      for (int i=keeper.size()-1; i>position; i--) // remove all the books from 0 to position-1, and add them to the removedBooks
      {
         removedHeight = keeper.removeLast();
         removedBooks.addFront(removedHeight); // The first one of removedBooks is the last one got removed, was at position-1.
         operations++;
      }
      return removedBooks;
   }
   
   
   /**
    * Put these removed books back to the Bookshelf keeper at the front.
    * Can be used by both pick and put when puting books back from removedBooks.
    *
    * @param removedHeight: the height of the removed book.
    * @operations: The number of operations took to put books back.
    * @numRemovedBooks: size of removedBooks.
    */
   private int putBackFrontBooks(Bookshelf removedBooks){
      int removedHeight = 0; // the height of the removed book
      int operations = 0; // The number of operations took to put books back.
      int numRemovedBooks = removedBooks.size();
      for (int i =0; i<numRemovedBooks; i++) // add those books back to the keeper.
      {
         removedHeight = removedBooks.removeFront(); // remove the values in the removedBooks, to make it empty again.
         keeper.addFront(removedHeight); // Put the removed books back to the bookshelf keeper.
         operations++;
      }
      return operations;
   }
   
   
   /**
    * Put these removed books back to the Bookshelf keeper at the end.
    * Can be used by both pick and put when puting books back from removedBooks.
    *
    * @param removedHeight: the height of the removed book.
    * @operations: The number of operations took to put books back.
    * @numRemovedBooks: size of removedBooks.
    */
   private int putBackEndBooks(Bookshelf removedBooks){
      int removedHeight = 0; // the height of the removed book
      int operations = 0; // The number of operations took to put books back.
      int numRemovedBooks = removedBooks.size();
      for (int i =0; i<numRemovedBooks; i++) // add those books back to the keeper.
      {
         removedHeight = removedBooks.removeFront(); // remove the values in the removedBooks, to make it empty again.
         keeper.addLast(removedHeight); // Put the removed books back to the bookshelf keeper.
         operations++;
      }
      return operations;
   }
   
   
    /**
    * Remove the books from keeper from the end, until the next value is smaller than or equal to the height.
    * Put these removed books to removedBooks.
    * The first one of removedBooks is the last one got removed, was at position-1.
    * Can be used when removing the books, but don't know where to end, so keep comparing.
    *
    * @param removedHeight: the height of the removed book.
    */
   private Bookshelf removeEndUtilHeight(Bookshelf removedBooks, int height){ 
      int removedHeight = 0; // the height of the removed book
      int i = keeper.size()-1;
      while (height < keeper.getHeight(i)){
         removedHeight = keeper.removeLast();
         removedBooks.addFront(removedHeight); // The first one of removedBooks is the last one got removed, was at position-1.
         i--;
      }
      return removedBooks;
   }
   
   
   /**
    * Remove the books from keeper from the front, until the next value is bigger than or equal to the height.
    * Put these removed books to removedBooks.
    * The first one of removedBooks is the last one got removed, was at position-1.
    * Can be used when removing the books, but don't know where to end, so keep comparing.
    *
    * @param removedHeight: the height of the removed book.
    */
   private Bookshelf removeFrontUtilHeight(Bookshelf removedBooks, int height){ 
      int removedHeight = 0; // the height of the removed book
      while (height > keeper.getHeight(0)){ //everytime compare with the first value, because the first value may be removed.
         removedHeight = keeper.removeFront();
         removedBooks.addFront(removedHeight); // The first one of removedBooks is the last one got removed, was at position-1.
      }
      return removedBooks;
   }
  
   
// The two mothods below are for the scenarios that some books have the same height.
// The complexity is wrose than the original algorithm, but it will work for duplicate heights. 
   
   /**
    * Calculate the number of books need to be removed if from end.
    * Can be used when removing the books, but don't know where to end, so keep comparing.
    *
    * @param len: The number of books need to be removed if from end.
    */
   private int lengthFromEnd(int height){
      int len = 0;
      int i = keeper.size()-1;
      if (height <= keeper.getHeight(0)){
         return keeper.size();
      }
      while (height < keeper.getHeight(i)){
         len++;
         i--;
      }
      return len;
   }
   
   /**
    * Calculate the number of books need to be removed if from front.
    *
    * @param len: The number of books need to be removed if from front.
    */
   private int lengthFromFront(int height){
      int len = 0;
      int i = 0;
      if (height >= keeper.getHeight(keeper.size()-1)){
         return keeper.size();
      }
      while (height > keeper.getHeight(i)){
         len++;
         i++;
      }
      return len;
   }

}
