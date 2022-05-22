// Owen O'Connor
// CSC 205
// Assignment 23
// Mostly left this file alone except added checkRoute() and checkRouteRecurse()


// File: Graph.java from the package EDU.colorado.graphs
// Complete documentation is available from the Graph link in
//   http://www.cs.colorado.edu/~main/docs/

package airplane;

/******************************************************************************
* A <CODE>Graph</CODE> is a labeled graph with a fixed number of vertices.
*
* <dt><b>Java Source Code for this class:</b><dd>
*   <A HREF="../../../../edu/colorado/collections/Graph.java">
*   http://www.cs.colorado.edu/~main/EDU/colorado/collections/Graph.java
*   </A>
*
* @author Michael Main 
*   <A HREF="mailto:main@colorado.edu"> (main@colorado.edu) </A>
*
* @version
*   Jun 12, 1998
******************************************************************************/
public class Graph implements Cloneable
{
   // Invariant of the Graph class:
   //   1. The vertex numbers range from 0 to labels.length-1.
   //   2. For each vertex number i, labels[i] contains the label for vertex i.
   //   3. For any two vertices i and j, edges[i][j] is true if there is a
   //      vertex from i to j; otherwise edges[i][j] is false.  
   private boolean[ ][ ] edges;
   private Object[ ] labels;
   

   /**
   * Initialize a <CODE>Graph</CODE> with <CODE>n</CODE> vertices,
   * no edges, and null labels.
   * @param <CODE>n</CODE>
   *   the number of vertices for this <CODE>Graph<CODE>
   * <dt><b>Precondition:</b><dd>
   *   <CODE>n</CODE> is nonnegative.
   * <dt><b>Postcondition:</b><dd>
   *   This <CODE>Graph</CODE> has <CODE>n</CODE> vertices, numbered 
   *   <CODE>0<CODE> to <CODE>n-1</CODE>. It has no edges and all
   *   vertex labels are null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for the specified number of nodes.
   * @exception NegativeArraySizeException
   *   Indicates that <CODE>n</CODE> is negative. 
   **/   
   public Graph(int n)
   {
      edges = new boolean[n][n];  // All values initially false
      labels = new Object[n];     // All values initially null
   }
   
   
   /**
   * Add a new edge to this <CODE>Graph</CODE>.
   * @param <CODE>source</CODE>
   *   the vertex number of the source of the new edge
   * @param <CODE>target</CODE>
   *   the vertex number of the target of the new edge
   * <dt><b>Precondition:</b><dd>
   *   Both <CODE>source</CODE> and <CODE>target</CODE> are nonnegative and
   *   less than <CODE>size()</CODE>.
   * <dt><b>Postcondition:</b><dd>
   *   This <CODE>Graph</CODE> has all the edges that it originally had plus
   *   another edge from the specified <CODE>source</CODE> to the specified
   *   <CODE>target</CODE>. (If the edge was already present, then this
   *   <CODE>Graph</CODE> is unchanged.)
   * @exception ArrayIndexOutOfBoundsException
   *   Indicates that the <CODE>source</CODE> or <CODE>target</CODE> was not a
   *   valid vertex number.
   **/
   public void addEdge(int source, int target)   
   {
      edges[source][target] = true;
   }
   
      
   /**
   * Generate a copy of this <CODE>Graph</CODE>.
   * @param - none
   * @return
   *   The return value is a copy of this <CODE>Graph</CODE>. Subsequent changes to the
   *   copy will not affect the original, nor vice versa. Note that the return
   *   value must be type cast to a <CODE>Graph</CODE> before it can be used.
   * @throws OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public Object clone( )
   {  // Clone a Graph object.
      Graph answer;
      
      try
      {
         answer = (Graph) super.clone( );
      }
      catch (CloneNotSupportedException e)
      {  // This would indicate an internal error in the Java runtime system
         // because super.clone always works for an Object.
         throw new InternalError(e.toString( ));
      }
      
      answer.edges = (boolean [ ][ ]) edges.clone( );
      answer.labels = (Object [ ]) labels.clone( );
      
      return answer;
   }
   


   
/**
 * Works with checkRouteRecurse to find out if there is a route between two cities. 
 * Traverses through the graph from the start city and flags a static boolean "route"
 * if the destination can be reached by traversing through the graph. 
 * @param g
 * @param start
 * @param destination
 */
   public static void checkRoute(Graph g, int start, int destination)
   {
      boolean[ ] marked = new boolean [g.size( )];
      
      checkRouteRecurse(g, start, marked, destination);
      
   }
   
   public static void checkRouteRecurse(Graph g, int v, boolean[ ] marked, int d)
   {
      int[ ] connections = g.neighbors(v);
      int i;
      int nextNeighbor;
      
      marked[v] = true;

      
      // Traverse all the neighbors, looking for unmarked vertices:
      for (i = 0; i < connections.length; i++)
      {
         nextNeighbor = connections[i];

         if (!marked[nextNeighbor])
        	 checkRouteRecurse(g, nextNeighbor, marked,d);
         
         if (nextNeighbor == d) {
        	 Airline.route = true;
         }
         
      } 
      
   }
   
   

   /**
   * Static method to print the labels of a graph with a depth-first search.
   * @param <CODE>g</CODE>
   *   a nonnull <CODE>Graph</CODE>
   * @param <CODE>start</CODE>
   *   a vertex number from the <CODE>Graph g</CODE>
   * <dt><b>Precondition:</b><dd>
   *   <CODE>start</CODE> is nonnegative and less than <CODE>g.size()</CODE>.
   * <dt><b>Postcondition:</b><dd>
   *   A depth-first search of <CODE>g</CODE> has been conducted, starting at
   *   the specified start vertex. Each vertex visited has its label printed 
   *   using <CODE>System.out.println</CODE>. Note that vertices that are not 
   *   connected to the start will not be visited.
   * @throws NullPointerException
   *   Indicates that <CODE>g</CODE> is null.
   * @throws ArrayIndexOutOfBoundsException
   *   Indicates that the vertex was not a valid vertex number.
   * @throws OutOfMemoryError
   *   Indicates that there is insufficient memory for an array of boolean values 
   *   used by this method.
   **/
   public static void depthFirstPrint(Graph g, int start)
   {
      boolean[ ] marked = new boolean [g.size( )];
      
      depthFirstRecurse(g, start, marked);
      
   }
   
   
   /**
   * Recursive method to carry out the work of <CODE>depthFirstPrint</CODE>.
   * @param <CODE>g</CODE>
   *   a nonnull <CODE>Graph</CODE>
   * @param <CODE>v</CODE>
   *   a vertex number from the <CODE>Graph g</CODE>
   * @param <CODE>marked</CODE>
   *   an array to indicate which vertices of <CODE>g</CODE> have already
   *   been visited
   * <dt><b>Precondition:</b><dd>
   *   <CODE>v</CODE> is nonnegative and less than <CODE>g.size()</CODE>.
   *   <CODE>marked.length</CODE> is equal to <CODE>g.size()</CODE>;
   *   for each vertex <CODE>x</CODE> of </CODE>g</CODE>, <CODE>marked[x]</CODE>
   *   is <CODE>true</CODE> if <CODE>x</CODE> has already been visited by this
   *   search; otherwise <CODE>marked[x]</CODE> is <CODE>false</CODE>.
   *   The vertex </CODE>v</CODE> is an unmarked vertex that the search has
   *   just arrived at.
   * <dt><b>Postcondition:</b><dd>
   *   The depth-first search of <CODE>g</CODE> has been continued through 
   *   vertex </CODE>v</CODE> and beyond to all vertices that can be reached
   *   from <CODE>v</CODE> via a path of unmarked vertices. Each vertex visited
   *   has its label printed using <CODE>System.out.println.</CODE> 
   * @throws NullPointerException
   *   Indicates that <CODE>g</CODE> is null.
   * @throws ArrayIndexOutOfBoundsException
   *   Indicates that the vertex was not a valid vertex number, or 
   *   <CODE>marked</CODE> was the wrong size. 
   **/
   public static void depthFirstRecurse(Graph g, int v, boolean[ ] marked)
   {
      int[ ] connections = g.neighbors(v);
      int i;
      int nextNeighbor;
      
      marked[v] = true;
      System.out.println(g.getLabel(v));
      
      // Traverse all the neighbors, looking for unmarked vertices:
      for (i = 0; i < connections.length; i++)
      {
         nextNeighbor = connections[i];
         if (!marked[nextNeighbor])
            depthFirstRecurse(g, nextNeighbor, marked);
      } 
   }
   
   
   /**
   * Accessor method to get the label of a vertex of this <CODE>Graph</CODE>. 
   * @param <CODE>vertex</CODE>
   *   a vertex number
   * <dt><b>Precondition:</b><dd>
   *   <CODE>vertex</CODE> is nonnegative and
   *   less than <CODE>size()</CODE>.
   * @return
   *   the label of the specified vertex in this <CDOE>Graph</CODE>
   * @exception ArrayIndexOutOfBoundsException
   *   Indicates that the <CODE>vertex</CODE> was not a
   *   valid vertex number.
   **/
   public Object getLabel(int vertex)
   {
      return labels[vertex];
   }

 
   /**
   * Accessor method to determine whether this <CODE>Graph</CODE> contains
   * a specified edge.
   * @param <CODE>source</CODE>
   *   the vertex number of the source of the specified edge
   * @param <CODE>target</CODE>
   *   the vertex number of the target of the specified edge
   * <dt><b>Precondition:</b><dd>
   *   Both <CODE>source</CODE> and <CODE>target</CODE> are nonnegative and
   *   less than <CODE>size()</CODE>.
   * @return
   *   The return value is true if this <CODE>Graph</CODE> has an edge from the
   *   specified <CODE>source</CODE> to the specified <CODE>target</CODE>.
   *   Otherwise the return value is false.
   * @exception ArrayIndexOutOfBoundsException
   *   Indicates that the <CODE>source</CODE> or <CODE>target</CODE> was not a
   *   valid vertex number.
   **/
   public boolean isEdge(int source, int target)
   {
      return edges[source][target];
   }

   /**
   * Accessor method to obtain a list of neighbors of a specified vertex of
   * this <CODE>Graph</CODE>
   * @param <CODE>vertex</CODE>
   *   a vertex number
   * @param <CODE>target</CODE>
   *   a vertex number 
   * <dt><b>Precondition:</b><dd>
   *   Both <CODE>source</CODE> and <CODE>target</CODE> are nonnegative and
   *   less than <CODE>size()</CODE>.
   * <dt><b>Precondition:</b><dd>
   *   <CODE>vertex</CODE> is nonnegative and
   *   less than <CODE>size()</CODE>.
   * @return
   *   The return value is an array that contains all the vertex numbers of
   *   vertices that are targets for edges with a source at the specified
   *   <CODE>vertex</CODE>.
   * @exception ArrayIndexOutOfBoundsException
   *   Indicates that the <CODE>source</CODE> or <CODE>target</CODE> was not a
   *   valid vertex number.
   **/
   public int[ ] neighbors(int vertex)
   {
      int i;
      int count;
      int[ ] answer;
      
      // First count how many edges have the vertex as their source
      count = 0;
      for (i = 0; i < labels.length; i++)
      {
         if (edges[vertex][i])
            count++;
      }
           
      // Allocate the array for the answer
      answer = new int[count];
      
      // Fill the array for the answer
      count = 0;
      for (i = 0; i < labels.length; i++)
      {
         if (edges[vertex][i])
            answer[count++] = i;
      }
      
      return answer;
   }
              
   
   /**
   * Remove an edge from this <CODE>Graph</CODE>.
   * @param <CODE>source</CODE>
   *   the vertex number of the source of the removed edge
   * @param <CODE>target</CODE>
   *   the vertex number of the target of the removed edge
   * <dt><b>Precondition:</b><dd>
   *   Both <CODE>source</CODE> and <CODE>target</CODE> are nonnegative and
   *   less than <CODE>size()</CODE>.
   * <dt><b>Postcondition:</b><dd>
   *   This <CODE>Graph</CODE> has all the edges that it originally had minus
   *   the edge from the specified <CODE>source</CODE> to the specified
   *   <CODE>target</CODE>. (If the edge was not present, then this
   *   <CODE>Graph</CODE> is unchanged.)
   * @exception ArrayIndexOutOfBoundsException
   *   Indicates that the <CODE>source</CODE> or <CODE>target</CODE> was not a
   *   valid vertex number.
   **/
   public void removeEdge(int source, int target)   
   {
      edges[source][target] = false;
   }
   
   
   /**
   * Modification method to change the label of a vertex of this <CODE>Graph</CODE>. 
   * @param <CODE>vertex</CODE>
   *   a vertex number
   * @param <CODE>newLabel</CODE>
   *   a new label (which may be null)
   * <dt><b>Precondition:</b><dd>
   *   <CODE>vertex</CODE> is nonnegative and
   *   less than <CODE>size()</CODE>.
   * <dt><b>Postcondition:</b><dd>
   *   The label of the specified vertex in this <CDOE>Graph</CODE> has been
   *   changed to the <CODE>newLabel</CODE>.
   * @exception ArrayIndexOutOfBoundsException
   *   Indicates that the <CODE>vertex</CODE> was not a
   *   valid vertex number.
   **/
   public void setLabel(int vertex, Object newLabel)
   {
      labels[vertex] = newLabel;
   }   
   
   
   /**
   * Determine the number of vertices in this <CODE>Graph</CODE>.
   * @param - none
   * @return
   *   the number of vertices in this <CODE>Graph</CODE>
   **/ 
   public int size( )
   {
      return labels.length;
   }
        
}
           