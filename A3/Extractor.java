import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * @author  Elizabeth Dayton (ead0044@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2018-02-27
 *
 */
public class Extractor {
   
   /** raw data: all (x,y) points from source data. */
   private Point[] points;
   
   /** lines identified from raw data. */
   private SortedSet<Line> lines;
  
   /**
    * Builds an extractor based on the points in the file named by filename. 
    */
   public Extractor(String filename) throws FileNotFoundException {
   
      Scanner scanFile = new Scanner(new File(filename));
         
      int numberOfPoints = Integer.parseInt(scanFile.nextLine());
      
      points = new Point[numberOfPoints];
   
      for (int i = 0; i < numberOfPoints; i++) {
      
         int x = Integer.parseInt(scanFile.next());
         int y = Integer.parseInt(scanFile.next());
         points[i] = new Point(x, y);
      
      }
   
   }
  
   /**
    * Builds an extractor based on the points in the Collection named by pcoll. 
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.toArray(new Point[]{});
   }
  
   /**
    * Returns a sorted set of all line segments of exactly four collinear
    * points. Uses a brute-force combinatorial strategy. Returns an empty set
    * if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesBrute() {
      lines = new TreeSet<Line>();
      
      for (int i = 0; i < points.length; i++) {
      
         Point p1 = points[i];
      
         for (int j = i + 1; j < points.length; j++) {
         
            Point p2 = points[j];
         
            for (int k = j + 1; k < points.length; k++) {
            
               Point p3 = points[k];
            
               for (int l = k + 1; l < points.length; l++) {
               
                  Point p4 = points[l];
               
                  if (p1.slopeOrder.compare(p2, p3) == 0 && p1.slopeOrder.compare(p3, p4) == 0) {
                  
                     Line q = new Line();
                     q.add(p1);
                     q.add(p2);
                     q.add(p3);
                     q.add(p4);
                     
                     lines.add(q);
                  
                  }   
                  
               }
               
            }
            
         }
         
      }
      
      return lines;
   }
  
   /**
    * Returns a sorted set of all line segments of at least four collinear
    * points. The line segments are maximal; that is, no sub-segments are
    * identified separately. A sort-and-scan strategy is used. Returns an empty
    * set if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesFast() {
      lines = new TreeSet<Line>();
      
      for (int i = 0; i < points.length; i++) {
         
         for (Line l : lines) {
         
            for (Point p : l) {
            
               if (p == points[i]) {
               
                  break;
               
               }
            
            }
            break;
         }
         Point referencePoint = points[i];
      
         Point[] slopes = new Point[points.length];
      
      
         int k = 1;
         
         for (int j = 1; j < points.length; j++) {
         
            slopes[0] = referencePoint;
            if ((j + 1) >= points.length) {
            
               continue;
            
            }
            if (referencePoint.slopeOrder.compare(points[j], points[j + 1]) == 0) {
            
               slopes[k] = points[j];
               slopes[k + 1] = points [j + 1];
               k++;
            
            }
         
         }
         if (slopes.length == 1 || slopes.length < 4) {
         
            continue;
         
         }
         
         Line q = new Line();
         for (Point p: slopes) {
         
            if (p == null) {
            
               continue;
            
            }
            q.add(p);
         
         }
         
         if (q.length() >= 4) {
         
            lines.add(q);
         
         }
      }
      
      return lines;
   }
   
}
