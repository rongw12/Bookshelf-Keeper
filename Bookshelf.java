// Name: Rong Wang
// USC NetID: rwang424
// CSCI455 PA2
// Fall 2020

import java.util.ArrayList;


/**
 * Class Bookshelf
 * Implements idea of arranging books into a bookshelf.
 * Books on a bookshelf can only be accessed in a specific way so books don’t fall down;
 * You can add or remove a book only when it’s on one of the ends of the shelf.   
 * However, you can look at any book on a shelf by giving its location (starting at 0).
 * Books are identified only by their height; two books of the same height can be
 * thought of as two copies of the same book.
*/

public class Bookshelf {

   /**
      Representation invariant:

      <rep. invar. comment>
      1: All books of the bookshelf have a positive height.
   
    * @param books: an arraylist of all the heights of input books.
    */
   private ArrayList<Integer> books; // the arraylist of input books


   /**
    * Creates an empty Bookshelf object i.e. with no books
    */
   public Bookshelf() {
      books = new ArrayList<Integer>(); // can not add ArrayList<Integer> in the front of books, otherwise will error
      assert isValidBookshelf();  // sample assert statement (you will be adding more of these calls)
   }

   /**
    * Creates a Bookshelf with the arrangement specified in pileOfBooks. Example
    * values: [20, 1, 9].
    * 
    * @param pileOfBooks: an array list of all the heights of input books.
    * PRE: pileOfBooks contains an array list of 0 or more positive numbers
    * representing the height of each book.
    */
   public Bookshelf(ArrayList<Integer> pileOfBooks) {
      // books = pileOfBooks; // can not add ArrayList<Integer> in the front of books
      books = new ArrayList<Integer>(pileOfBooks);
      assert isValidBookshelf(); 
   }

   /**
    * Inserts book with specified height at the start of the Bookshelf, i.e., it
    * will end up at position 0.
    * 
    * @param height: The height of the insert book.
    * PRE: height > 0 (height of book is always positive)
    */
   public void addFront(int height) {
      assert height > 0;
      if (height > 0){
         books.add(0, height);
      }
      assert isValidBookshelf();
   }

   /**
    * Inserts book with specified height at the end of the Bookshelf.
    * 
    * @param height: The height of the insert book.
    * PRE: height > 0 (height of book is always positive)
    */
   public void addLast(int height) {
      assert height > 0;
      if (height > 0){
         books.add(height);
      }
      assert isValidBookshelf();
   }

   /**
    * Removes book at the start of the Bookshelf and returns the height of the
    * removed book.
    * 
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeFront() {
      assert books.size()>0;
      int heightOfFront = books.get(0);
      books.remove(0);
      assert isValidBookshelf();
      return heightOfFront;   
      
   }

   /**
    * Removes book at the end of the Bookshelf and returns the height of the
    * removed book.
    * 
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeLast() {
      assert books.size()>0;
      int len = books.size();
      int heightOfLast = books.get(len-1);
      books.remove(len-1);
      assert isValidBookshelf();
      return heightOfLast;      
   }

   /*
    * Gets the height of the book at the given position.
    * 
    * @param position: the index of the book in the bookshelf.
    * PRE: 0 <= position < this.size()
    */
   public int getHeight(int position) {
      assert 0 <= position && position < books.size();
      assert isValidBookshelf();
      return books.get(position);      
   }

   /**
    * Returns number of books on the this Bookshelf.
    */
   public int size() {
      assert isValidBookshelf();
      return books.size();  

   }

   /**
    * Returns string representation of this Bookshelf. Returns a string with the height of all
    * books on the bookshelf, in the order they are in on the bookshelf, using the format shown
    * by example here:  “[7, 33, 5, 4, 3]”
    */
   public String toString() {
      /**
      String s = "[";
      if (books.size() < 1){ return "[]";}
      else
      {
         s = s + books.get(0);
         for (int i = 1; i < books.size(); i++)
         {
            s = s + ", " + books.get(i);
         }
         s = s + "]";
      }
      return s;  
      */
      assert isValidBookshelf();
      return "" + books;
      
      // The following three lines may not work, just me trying different methods.
      // string s= string.Join(",", (string[])books.ToArray(typeof(string)));
      // String s = StringUtils.join(books,",");
      // s = String.join(",",  books.toArray(new String[books.size()])); 
   }

   /**
    * Returns true iff the books on this Bookshelf are in non-decreasing order.
    * (Note: this is an accessor; it does not change the bookshelf.)
    */
   public boolean isSorted() {
      for (int i = 0; i < books.size()-1; i++){
         if (books.get(i) > books.get(i+1)){return false;}
      }
      assert isValidBookshelf();
      return true;  
   }

   /**
    * Returns true iff the Bookshelf data is in a valid state.
    * (See representation invariant comment for more details.)
    */
   private boolean isValidBookshelf() {
      for (int i = 0; i < books.size(); i++){
         if (books.get(i) < 1){return false;}
      }
      return true;
   }

}
