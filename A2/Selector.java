import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Your Name (you@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2018-01-25
 *
 */
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
   private Selector() { }


   /**
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
   
      if (coll == null || comp == null) {
      
         throw new IllegalArgumentException();
      
      }
      
      if (coll.isEmpty()) {
      
         throw new NoSuchElementException();
      
      }
     
      Iterator<T> itr = coll.iterator();
       
      T min = itr.next(); 
      
      while (itr.hasNext()) {
      
         T val = itr.next();
         
         if (comp.compare(val, min) < 0) {
            
            min = val; 
             
         }
      }
      
      return min;
      
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
   
      if (coll == null || comp == null) {
      
         throw new IllegalArgumentException();
      
      }
      
      if (coll.size() == 0) {
      
         throw new NoSuchElementException();
      
      }
      
      Iterator<T> itr = coll.iterator();
       
      T max = itr.next();
      
      while (itr.hasNext()) {
      
         T val = itr.next();
         
         if (comp.compare(val, max) > 0) {
            
            max = val; 
             
         }
      }
         
      
      
      return max;
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
     
      if (coll == null || comp == null) {
      
         throw new IllegalArgumentException();
      
      }
      
      if (coll.isEmpty() || k > coll.size() || k < 1) {
      
         throw new NoSuchElementException();
      
      }
      
      ArrayList<T> b = new ArrayList<T>();
      
      for (T t : coll) {
         b.add(t);
      }
      
      java.util.Collections.sort(b, comp);
      for (int i = 0; i < b.size() - 1; i++) {
         if (k == 1) {
            break;
         }
         if (b.get(i) == b.get(i + 1)) {
            b.remove(i + 1);
            i--;
         }
         
         
      }
   
    
      
      
      if (k > b.size()) {
      
         throw new NoSuchElementException();
      
      }
      
      //T kmin = c.get(k - 1);
   
      return b.get(k - 1);
   
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
       
      if (coll == null || comp == null) {
      
         throw new IllegalArgumentException();
      
      }
      
      if (coll.isEmpty() || k > coll.size() || k < 1) {
      
         throw new NoSuchElementException();
      
      }
      
      ArrayList<T> b = new ArrayList<T>();
      
      for (T t : coll) {
         b.add(t);
      }
      
      java.util.Collections.sort(b, comp);
      for (int i = b.size() - 1; i > 0; i--) {
         if (k == 1) {
            break;
         }
         if (b.get(i) == b.get(i - 1)) {
            b.remove(i);
            // i++;
         }
         
         
      }
   
      
      
      
      if (k > b.size()) {
      
         throw new NoSuchElementException();
      
      }
      
      T kmax = b.get(b.size() - k);
   
      return kmax;
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      if (coll == null || comp == null) {
      
         throw new IllegalArgumentException();
      
      }
      
      if (coll.isEmpty()) {
      
         throw new NoSuchElementException();
      
      }
     
      ArrayList<T> range = new ArrayList<T>();
       
      Iterator<T> itr = coll.iterator();
      
      while (itr.hasNext()) { 
        
         T val = itr.next();
         
         if (comp.compare(val, low) >= 0 && comp.compare(val, high) <= 0) {
            
            range.add(val); 
             
         }
      }
      
   
      if (range.isEmpty()) {
      
         throw new NoSuchElementException();
      
      }
      
      return range;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      
      if (coll == null || comp == null) {
      
         throw new IllegalArgumentException();
      
      }
      
      if (coll.isEmpty()) {
      
         throw new NoSuchElementException();
      
      }
      
      Iterator<T> itr = coll.iterator();
      T ceiling = null;
      
      while (itr.hasNext()) { 
      
         T val = itr.next();
         
         if (comp.compare(val, key) >= 0) {
            if (ceiling == null) {
               ceiling = val;
            }
            else {
               if (comp.compare(val, ceiling) < 0) {
                  ceiling = val;
               
               }
            }
             
             
         }  
      }
      
      // while  (itr.hasNext()) {
      // 
         // T val = itr.next();
      //    
         // if (comp.compare(val, key) >= 0 && comp.compare(val, ceiling) <= 0) {
         //    
            // ceiling = val;
         //    
         // }
      //    
      // }
   
      if (ceiling == null) {
      
         throw new NoSuchElementException();
      
      }
      
      return ceiling;
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      
      if (coll == null || comp == null) {
      
         throw new IllegalArgumentException();
      
      }
      
      if (coll.isEmpty()) {
      
         throw new NoSuchElementException();
      
      }
      
      Iterator<T> itr = coll.iterator();
      T floor = null;
      
      while (itr.hasNext()) { 
        
         T val = itr.next();
         
         if (comp.compare(val, key) <= 0) {
            
            if (floor == null) {
               floor = val;
            }
            else {
               if (comp.compare(val, floor) > 0) {
                  floor = val;
               }
            }
            
            // floor = val;
            // continue; 
             
         }  
      }
      
      // while  (itr.hasNext()) {
      // 
         // T val = itr.next();
      //    
         // if (comp.compare(val, key) <= 0 && comp.compare(val, floor) >= 0) {
         //    
            // floor = val;
         //    
         // }
      //    
      // }
   // 
      if (floor == null) {
      
         throw new NoSuchElementException();
      
      }
      
      return floor;
   }

}
