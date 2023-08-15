/* 9/28/2022
   This class implements the ListInterface */
public class AList <T> implements AListInterface <T>
{
   private T[] L;
   private int counter;
  
   public AList()
   {
      L = (T[]) new Object[20];
      counter = 0;
   }   
   public String toString()
   {
      if(counter == 0)
         return "List is empty";
      
      String temp = "";
      
      for(int x = 0; x < counter; x++)
         temp += L[x] + "\n";
      return temp;
   }
   
   //add
   public void add(T m) throws ListException
   {
      if(m == null) 
         throw new ListException("Error. Unable to add. Cannot add null entries.");
      
      if(counter == L.length)
         resize();
      L[counter] = m;
      counter++;
 
   }
   
   //Insert Operation, implemented as an overloaded add method
   public void add(int pos, T item) throws ListException
   {
      if(item == null)
         throw new ListException("Error. Unable to insert. No null items allowed.");
      if(counter == 0)
         throw new ListException("Error. Unable to insert. List is empty.");
      if(pos < 1 || pos > counter)
         throw new ListException("Error. Unable to insert. Bad position.");
      if(counter == L.length)
         resize();
      for(int x = counter - 1; x >= pos-1; x--)
         L[x + 1] = L[x];
      L[pos - 1] = item;
      counter++;
   }
      
   public T remove(int pos) throws ListException
   {
      if(counter == 0)
         throw new ListException("Error. Unable to remove. List is empty.");
      if(pos < 1 || pos > counter)
         throw new ListException("Error. Unable to remove. Bad position.");
         
      int k;
      k = pos;
      
      while(k < counter)
      {
         L[k-1] = L[k];
         k++;
      }
      
      counter--;
      return L[k];
   }
   
   public T get(int pos) throws ListException
   {  
      if(counter == 0)
         throw new ListException("Error. Unable to get. List is empty.");
      if(pos < 1 || pos > counter)
         throw new ListException("Error. Unable to get. Bad position.");
       
       T temp = L[pos - 1];
      
      return temp;
   }
   
   public T set(T item, int pos) throws ListException
   {
      if(item == null)
         throw new ListException("Error. Unable to replace. No null items allowed.");
      if(counter == 0)
         throw new ListException("Error. Unable to replace. List is empty.");
      if(pos < 1 || pos > counter)
         throw new ListException("Error. Unable to replace. Bad position.");
         
      T temp = L[pos - 1];   
      L[pos - 1] = item;
      
      return temp;
   }
   
   @Override
   public int find(T item, int startpos, int endpos) throws ListException
   {
       if(startpos < 1 || startpos > counter)
         throw new ListException("Error. Unable to find. Start and/or end position bad.");
       if(endpos < 1 || endpos > counter)
         throw new ListException("Error. Unable to find. Start and/or end position bad.");
       if(startpos > endpos)
         return -1;
         
       int x = startpos-1;
       while(x != endpos)
       {
         if(item.equals(L[x]))
            return x+1;
         x++;     
       }
      
      return -1;             
   }
   
   public boolean equals(T[] A)
   {
      boolean isEqual = true;
      if(toArray().length != A.length)
         return false;
      for(int x = 0; x < A.length-1; x++)
      {
         if(toArray()[x] != A[x])
         {
            isEqual = false;
            break;
         }
      }
        
      return isEqual;
   }
   public int getCapacity()
   {
      return L.length;
   }
   
   private T[] resize() throws ListException
   {
      T[] temp = (T[]) new Object[counter + 10];
      
      for(int x = 0; x < counter-1; x++)
         temp[x] = L[x];
         
      L = temp;
      return L;
   }
   
   public int size()
   {
      if(counter == 1) 
        return 0;
      return counter;  
   }
   
   public void clear()
   {
      L = (T[]) new Object[20];
      counter = 0;
   }
   
   public Object[] toArray()
	{
	   Object []objArray = new Object[counter+1];
		for(int i=1; i<=counter; i++)
		{
		   objArray[i] = L[i];
		}
		return objArray;
	}
   
   public boolean isEmpty()
   {
      if(counter == 0)
         return true;
      else
         return false;
   }
     
}