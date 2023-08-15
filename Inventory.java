/* Miron Smierzewski
   11/15/2022
   Inventory Class */
import java.io.*; //needed for reading file
import java.util.Scanner;
public class Inventory
{   
   private String fileName;
   AList<StoreItem> storeItems;
   
   public Inventory(String s) throws IOException
   {
      this.fileName = s;
      storeItems = new AList<>();
      Scanner scanner = new Scanner(new File(this.fileName));
      String upc;
      String description;
      int availability;
      double price;
      
      while(scanner.hasNext())
      {
         upc = scanner.nextLine();
         description = scanner.nextLine();
         availability = scanner.nextInt();
         price = scanner.nextDouble();
         if(scanner.hasNextLine())
            scanner.nextLine();
         
         try
         {
            StoreItem item = new StoreItem(upc, description, availability, price);
            storeItems.add(item);
         }
         catch(ListException e)
         {
            System.out.println(e);
            System.exit(1);
         }
      }
      scanner.close();   
   }
   
   private void displayInventory()
   {
      System.out.println("The current inventory");
      System.out.println("---------------------");
      if(storeItems.isEmpty())
      {
         System.out.println("(Current Inventory is empty)");
      }
      else
         System.out.println(toString());
   }
   
   private StoreItem getInput()
   {
      Scanner scanner = new Scanner(System.in);
      String upc;
      String description;
      int availability;
      double price;
     
      System.out.print("Enter UPC code: ");
      upc = scanner.nextLine();
      System.out.print("Enter name: ");
      description = scanner.nextLine();
      System.out.print("Enter price per item: ");
      price = scanner.nextDouble();
      System.out.print("Enter quantity on hand: ");
      availability = scanner.nextInt();
      
      StoreItem item;
      item = new StoreItem(upc, description, availability, price);
      
      return item;
      
   }
   
   private void addToInventory()
   {
      try
      {
         StoreItem item = getInput();
         storeItems.add(item);
      }
      catch(ListException e)
      {
         System.exit(1);
      }
   }
   
   private void insertToInventory(int position)
   {
      try
      {
         StoreItem item = getInput();
         storeItems.add(position, item);
      }
      catch(ListException e)
      {
         System.exit(1);
      }
   }
   
   private void searchInInventory(String text)
   {
      StoreItem item = null;
      String str = "";
      for(int i = 1; i <= storeItems.size(); i++)
      {
         try
         {
            item = storeItems.get(i);
            if(item.getDescription().toUpperCase().contains(text.toUpperCase()))
            {
               str += i + ".  ";
               str+= storeItems.get(i).toString();
               str+= "\n";
            }
         }
         catch(ListException e)
         {
         }
         
      }
      if(str.length() == 0)
         System.out.println("No record found with search string" + text);
      else
      {
         System.out.println("Search results: ");
         System.out.print(str);
         System.out.println("(end-of-search)");
      }
   }
   
   private void deleteFromInventory(int position)
   {
      try
      {
         StoreItem item = storeItems.get(position);
         storeItems.remove(position);
         System.out.println("Deletion successful. You deleted: ");
         System.out.println(item.toString());
      }
      catch(ListException e)
      {
         System.exit(1);
      }
   }
   
   private void closeInventory()
   {
      PrintWriter pWriter;
      try
      {
         pWriter = new PrintWriter(new File(fileName));
         for(int i = 1; i <= storeItems.size(); i++)
         {
            pWriter.println(storeItems.get(i).getUPC());
            pWriter.println(storeItems.get(i).getDescription());
            pWriter.println(storeItems.get(i).getAvailability());
            pWriter.println(storeItems.get(i).getPrice());
         }
         pWriter.close();
      }
      catch(Exception e)
      {
         System.out.println(e);
      }
   }
   
   private void changeInInventory(int position)
   {
      Scanner scanner = new Scanner(System.in);
      System.out.print("Which field to change(1 for UPC, 2 for name, 3 for price, 4 for quantity)?");
      int fieldNum = scanner.nextInt();
      try
      {
         switch(fieldNum)
         {
            case 1:
               System.out.print("Enter new UPC: ");
               String upc = scanner.next();
               storeItems.get(position).setUPC(upc);
               break;
            case 2:
               System.out.print("Enter new name: ");
               String description = scanner.next();
               storeItems.get(position).setDescription(description);
               break;
            case 3:
               System.out.print("Enter new price: ");
               double price = scanner.nextDouble();
               storeItems.get(position).setPrice(price);
               break;
            case 4:
               System.out.print("Enter new quantity: ");
               int quantity = scanner.nextInt();
               storeItems.get(position).setAvailability(quantity);
               break;
         }
         System.out.println("The changed record: " + storeItems.get(position));
      }
      catch(ListException e)
      {
         System.out.println(e);
      }
   }
   
   public void run()
   {
      Scanner scanner = new Scanner(System.in);
      int position;
      int choice;
      while(true)
      {
         System.out.println();
         System.out.println("1. Display inventory items");
         System.out.println("2. Add inventory item");
         System.out.println("3. Insert inventory item");
         System.out.println("4. Delete inventory item");
         System.out.println("5. Change inventory item");
         System.out.println("6. Locate inventory item");
         System.out.println("7. Exit");
         System.out.print("Enter option: ");
         choice = scanner.nextInt();
         if(choice < 1 || choice > 7)
            break;
         switch(choice)
         {
            case 1:
               displayInventory();
               break;
            case 2:
               addToInventory();
                  break;
            case 3:
               System.out.print("Position of insertion? ");
               position = scanner.nextInt();
               if(position < 1 || position > storeItems.size())
                  System.out.println("Cannot insert. Posistion is bad.");
               else
                  insertToInventory(position);
               break;
            case 4:
               System.out.print("Position of deletion? ");
               position = scanner.nextInt();
               if(position < 1 || position > storeItems.size())
                  System.out.println("Cannot delete. Position is bad.");
               else
                  deleteFromInventory(position);
               break;
            case 5:
               System.out.print("Which record to change? ");
               position = scanner.nextInt();
               if(position < 1 || position > storeItems.size())
                  System.out.println("Cannot change. Position is bad.");
               else
                  changeInInventory(position);
               break;
            case 6:
               String text;
               System.out.print("Text to search for? ");
               text = scanner.next();
               searchInInventory(text);
               break;
            case 7:
               closeInventory();
               System.exit(1);
         }
      }
   }
   
   
   public String toString()
   {
      String str = "";
      for(int i = 1; i <= storeItems.size();i++)
      {
         str += i + ".  ";
         try
         {
            str += storeItems.get(i).toString();
         }
         catch(ListException e)
         {
         }
         str += "\n";
      }
      return str;
   }
}      
 

//end of program