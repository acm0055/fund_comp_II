/**
* LinkedStrand.java
* Provides a linked chunk list implementation of the DnaStrand interface.
* 
* @author   Your Name <you@tigermail.auburn.edu>
* @author   Dean Hendrix <dh@auburn.edu>
* @version  2015-10-12
*
*/
public class LinkedStrand implements DnaStrand {

   /**
   * Container for storing DNA information. A given DNA strand is
   * represented by a chain of nodes.
   *
   * D O   N O T   M A K E   A N Y   C H A N G E S   T O
   *
   * T H E   N O D E   C L A S S 
   *
   */
   public class Node {
      public String dnaInfo;
      public Node next;
   
      public Node() {
      }
   
      public Node(String s, Node n) {
         dnaInfo = s;
         next = n;
      }
   }

   // the first and last node in the DNA strand
   // L E A V E   T H E S E   F I E L D S   P U B L I C
   public Node front;
   public Node rear;

   // an empty strand
   public static LinkedStrand EMPTY_STRAND = new LinkedStrand();

   // the number of nucleotides in this strand
   private long size;

   /**
   * Create a strand representing an empty DNA strand, length of zero.
   *
   * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
   */
   public LinkedStrand() {
      front = rear = null;
      size = 0;
   }


   /**
   * Create a strand representing s. No error checking is done to see if s represents
   * valid genomic/DNA data.
   *
   * @param s is the source of cgat data for this strand
   */
   public LinkedStrand(String s) {
      // YOU MUST COMPLETE THIS METHOD
      initializeFrom(s);
   }
   
   private LinkedStrand (Node n) {
      if (n == null) {
         throw new IllegalStateException();
      }
      front = rear = n;
      size = front.dnaInfo.length();
   }


   /**
   * Initialize by copying DNA data from the string into this strand,
   * replacing any data that was stored. The parameter should contain only
   * valid DNA characters, no error checking is done by the this method.
   * 
   * @param source is the string used to initialize this strand
   */
   public void initializeFrom(String source) {
      // YOU MUST COMPLETE THIS METHOD
      front = rear = new Node(source, null);
      size = front.dnaInfo.length();
   }


   /**
   * Simulate a restriction enzyme cut by finding the first occurrence of
   * enzyme in this strand and replacing this strand with what comes before
   * the enzyme while returning a new strand representing what comes after the
   * enzyme. If the enzyme isn't found, this strand is unaffected and an empty
   * strand with size equal to zero is returned.
   * 
   * @param enzyme is the string being searched for
   * @return the part of the strand that comes after the enzyme
   */
   public DnaStrand cutWith(String enzyme) {
      // YOU MUST COMPLETE THIS METHOD
      if (enzyme == null) {
         return EMPTY_STRAND;
      }
      if (this.front == null) {
         throw new IllegalStateException();
      }
      if (this.front != this.rear) {
         //more than one node
         throw new IllegalStateException();
      }
      if (enzyme.compareTo("") == 0) {
         return EMPTY_STRAND;
      }
      
      Node result = cutWithoutChecks(this.front, enzyme);
      this.rear = this.front;
      
      if (result == null) {
         return EMPTY_STRAND;
      }
      
      return new LinkedStrand(result);
   }


   /**
   * Cut this strand at every occurrence of enzyme, replacing
   * every occurrence of enzyme with splice.
   *
   * @param enzyme is the pattern/strand searched for and replaced
   * @param splice is the pattern/strand replacing each occurrence of enzyme
   * @return the new strand leaving the original strand unchanged.
   */
   public DnaStrand cutAndSplice(String enzyme, String splice) {
      // YOU MUST COMPLETE THIS METHOD
      if (splice == null) {
         return EMPTY_STRAND;
      }
      if (enzyme == null) {
         return EMPTY_STRAND;
      }
      if (this.front == null) {
         throw new IllegalStateException();
      }
      if (enzyme.compareTo("") == 0) {
         return EMPTY_STRAND;
      }
      if (this.front != this.rear) {
         //more than one node
         throw new IllegalStateException();
      }
      
      //copy of calling object to prevent modification
      LinkedStrand copyOf = new LinkedStrand(this.front.dnaInfo);
      DnaStrand spliceStrand = new LinkedStrand(splice);
      
      Node prev = new Node();
      boolean end = false;
      
      while (!end) {
         //cut dna at rear which is the front
         //for the first run
         //rear node modified to everything before the enzyme
         copyOf.rear.next = copyOf.cutWithoutChecks(copyOf.rear, enzyme);
         //IE not enzyme found at end of array
         if (copyOf.rear.next == null) {
            end = true;
         }
         //check to make sure rear node is not an empty
         //string node and removes this if so
         else if (copyOf.rear.dnaInfo.compareTo("") == 0) {
            //obviously splice suceeded so change size
            copyOf.size -= enzyme.length() - splice.length();
            //check for the unique case rear is the front
            if (copyOf.rear == copyOf.front) {
               copyOf.front = new Node(splice, null);
               copyOf.front.next = copyOf.rear.next;
               copyOf.rear = copyOf.front.next;
               prev = copyOf.front;
            }
            //all other cases
            else {
               prev.next = new Node(splice, null);
               prev.next.next = copyOf.rear.next;
               prev = prev.next;
               copyOf.rear = copyOf.rear.next;
            }
         }
         //typical case
         else {
            copyOf.size -= enzyme.length() - splice.length();
            copyOf.rear.next = new Node(splice, copyOf.rear.next);
            prev = copyOf.rear.next;
            copyOf.rear = copyOf.rear.next.next;
         }
      }
      
      //checks to make sure the final rear
      //cut did not result in an empty strand
      //if it did this removes it
      if (copyOf.rear.dnaInfo.compareTo("") == 0) {
         prev.next = null;
         copyOf.rear = prev;
      }
      
      //Case when enzyme not in strand.
      if (copyOf.toString().compareTo(this.toString()) == 0) {
         return EMPTY_STRAND;
      }
      
      return copyOf;
   }
   
   private Node cutWithoutChecks(Node n, String enzyme) {
      int index = n.dnaInfo.indexOf(enzyme);
      
      if (index == -1) {
         return null;
      }
      
      String result = 
         n.dnaInfo.substring(index + enzyme.length(), n.dnaInfo.length());
      n.dnaInfo = n.dnaInfo.substring(0, index);
      
      return new Node(result, null);
   }

   /**
   * Returns the number of elements/base-pairs/nucleotides in this strand.
   * @return the number of base-pairs in this strand
   */
   public long size() {
      // YOU MUST COMPLETE THIS METHOD
      return size;
   }


   @Override
   public String toString() {
      // YOU MUST COMPLETE THIS METHOD
      Node p = front;
      StringBuilder output = new StringBuilder();
      while (p != null) {
         output.append(p.dnaInfo);
         p = p.next;
      }
      return output.toString();
   }


   /**
   * Appends the parameter to this strand changing this strand to represent
   * the addition of new characters/base-pairs, e.g., changing this strand's
   * size.
   * 
   * If possible implementations should take advantage of optimizations
   * possible if the parameter is of the same type as the strand to which data
   * is appended.
   * 
   * @param dna is the strand being appended
   * @return this strand after the data has been added
   */
   public DnaStrand append(DnaStrand  dna) {
      // YOU MUST COMPLETE THIS METHOD
      String className = dna.getClass().getName();
      switch (className) {
         case "LinkedStrand":
            LinkedStrand dnaLinked = (LinkedStrand) dna;
            Node p = dnaLinked.front;
            while (p != null) {
               //Constructs new node to prevent encapsulation
               //issues and errors due to inadvertant causality
               this.rear.next = new Node(p.dnaInfo, null);
               this.rear = this.rear.next;
               this.size += p.dnaInfo.length();
               p = p.next;
            }
            break;
         case "ArrayStrand":
            this.rear.next = new Node(dna.toString(), null);
            this.rear = this.rear.next;
            break;
         default:
            throw new IllegalStateException();
      }
      return this;
   }


   /**
   * Similar to append with a DnaStrand parameter in
   * functionality, but fewer optimizations are possible. Typically this
   * method will be called by the append method with an DNAStrand
   * parameter if the DnaStrand passed to that other append method isn't the same 
   * class as the strand being appended to.
   * 
   * @param dna is the string appended to this strand
   * @return this strand after the data has been added
   */
   public DnaStrand append(String dna) {
      // YOU MUST COMPLETE THIS METHOD
      this.rear.next = new Node(dna, null);
      this.rear = this.rear.next;
      return this;
   }
}

