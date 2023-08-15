/* 
Miron Smierzewski
11/15/2022
StoreItem defines 4 attributes of items stored in an inventory
*/
public class StoreItem<T>
{
   String upc;
   String description;
   int avalability;
   double price;
   
   public StoreItem(String u, String d, int a, double p)
   {
      upc = u;
      description = d;
      avalability = a;
      price = p;    
   } 
   
   public String getUPC()
   {
      return upc;
   }
   
   public void setUPC(String u)
   {
      upc = u;
   }
   
   public String getDescription()
   {
      return description;
   }
   
   public void setDescription(String s)
   {
      description = s;
   }
   
   public int getAvailability()
   {
      return avalability;
   }
   
   public void setAvailability(int a)
   {
      avalability = a;
   }
   
   public double getPrice()
   {
      return price;
   }
   
   public void setPrice(double p)
   {
      price = p;
   }
   
   public String toString()
   {
      String temp = " Item: " + description + " UPC: " + upc + " Price per item: " + price + " Quantity: " + avalability;
      return temp;
   }
}