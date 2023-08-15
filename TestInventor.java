//Code for testing Inventory Class
public class TestInventor
{
   public static void main(String []args)
   {
      try
      {
         Inventory d = new Inventory("C:\\Users\\Miron Smierzewski\\Desktop\\inventorylist.txt");
         d.run();  
      }
      catch(Exception e)
      {
         System.out.println(e);
      }
   }  
}