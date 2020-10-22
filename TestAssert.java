import java.util.ArrayList;

public class TestAssert
{
   public static void main(String[] args)
   {
      System.out.println("Testing the assert functin with bookshelf constructor Bookshelf(ArrayList<Integer> pileOfBooks).");
      ArrayList<Integer> l1 = new ArrayList<Integer>();
      l1.add(10);
      l1.add(3);
      l1.add(2);
      // System.out.println(l);
      Bookshelf test1 = new Bookshelf(l1);
      System.out.println("The current Bookshelf is [exp: [10, 3, 2]]: " + test1.toString());
      System.out.println();
      
      System.out.println("Testing the assert functin with bookshelf constructor Bookshelf(ArrayList<Integer> pileOfBooks).");
      ArrayList<Integer> l2 = new ArrayList<Integer>();
      l2.add(10);
      l2.add(3);
      l2.add(-2);
      // System.out.println(l);
      Bookshelf test2 = new Bookshelf(l2);
      System.out.println("The current Bookshelf is [exp: Assertion: " + test2.toString());
      System.out.println();
     

   }
}
