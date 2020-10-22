import java.util.ArrayList;

public class BookshelfTester
{
   public static void main(String[] args)
   {
      System.out.println("Testing the empty constructor Bookshelf().");
      Bookshelf test1 = new Bookshelf(); // the first constructor method of Bookshelf class
      System.out.println(test1);
      System.out.println("The current Bookshelf is [exp: []]: " + test1.toString());
      System.out.println();
      
      
      System.out.println("Testing the bookshelf constructor Bookshelf(ArrayList<Integer> pileOfBooks).");
      ArrayList<Integer> l = new ArrayList<Integer>();
      l.add(10);
      l.add(3);
      l.add(5);
      // System.out.println(l);
      Bookshelf test2 = new Bookshelf(l);
      System.out.println(test2);
      System.out.println("The current Bookshelf is [exp: [10, 3, 5]]: " + test2.toString());
      System.out.println();
      
      
      System.out.println("Testing the method addFront(int height).");
      test2.addFront(2);
      System.out.println("The current Bookshelf is [exp: [2, 10, 3, 5]]: " + test2.toString());
      System.out.println();
      
      
      System.out.println("Testing the method addFront(int height) with non positive number.");
      test2.addFront(-2);
      System.out.println("The current Bookshelf is [exp: Assertion error: " + test2.toString());
      System.out.println();
      
      
      System.out.println("Testing the method addLast(int height).");
      test2.addLast(20);
      System.out.println("The current Bookshelf is [exp: [2, 10, 3, 5, 20]]: " + test2.toString());
      System.out.println();
      
      
      System.out.println("Testing the method removeFront().");
      /** 
      test1.removeFront();
      System.out.println("Assertion Error: " + test2.toString());
      System.out.println();
      */
      test2.removeFront();
      System.out.println("The current Bookshelf is [exp: [10, 3, 5, 20]]: " + test2.toString());
      System.out.println();
      
      
      System.out.println("Testing the method removeLast().");
      test2.removeLast();
      System.out.println("The current Bookshelf is [exp: [10, 3, 5]]: " + test2.toString());
      System.out.println();
      
      
      System.out.println("Testing the method getHeight(int position).");
      System.out.println("The height of the book on Bookshelf at position 1 is [exp: 3]: " + test2.getHeight(1));
      System.out.println();
      
      System.out.println("Testing the method size().");
      System.out.println("The number of books on the bookshelf is [exp: 3]: " + test2.size());
      System.out.println();
      
      
      System.out.println("Testing the method isSorted().");
      System.out.println("Are the books on the bookshelf are in non-decreasing order? [exp: false]: " + test2.isSorted());
      System.out.println();
      


   }
}
