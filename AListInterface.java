/* An array list
   9/28/2022 */

public interface AListInterface <T>
{
   //add
   public void add(T e) throws ListException;
   
   //insert
   public void add(int pos, T item) throws ListException;
    
   //delete at index
   public T remove(int pos) throws ListException;
   
   //returns the item at a specific location
   public T get(int pos) throws ListException;
 
   //replaces a list item with another, returns item replaced
   public T set(T item, int pos) throws ListException;
   
   //locates a paticular item in a list, returns the position at which the item was found. if item is not found returns -1
   public int find(T item, int startpos, int endpos) throws ListException;
        
   //returns the number of items in the list
   public int size();
  
   //returns true or false if array is empty
   public boolean isEmpty();
   
   //removes all items from the list
   public void clear();
   
}