/* Colton L. Prettyman; Final Project 3, CS280. 
 * Special Thanks to Professor John Mill. 
 * June 16, 2012
 * Authorship Study Program using AVL trees or Hash Tables
 * The Result is the percent similarity between texts.
 *
 * The algorithm uses the geometric distance between word occurances 
 * of each mutual word of the texts. The percent occurance is the 
 * (occurance of word )/(word count of whole text ).
 * the difference of the word percentage of each mutual word is differenced 
 * then squared.
 * A running total of the differences is kept track of. Once complete this 
 * is the result
 */ 

import java.util.*;
import java.io.*;

public class authorship_study
{
    public static void main( String[] args ) throws IOException
    {
	if( args.length  != 3 )
	    {
		System.err.println("Usage: java authorship_study filename1 " +
				   "filename2  hash /or/ avl");
		System.exit( 1 );
	    }
	
	Scanner scan = new Scanner(System.in );
	boolean done = false;
	Scanner file1, file2 ;
	Text text1, text2;
	double result;
	
	file1 = file2 = null;
       

	file1 = new Scanner( new FileReader( args[0] ) ); 
	file2 = new Scanner( new FileReader( args[1] ) ); 
	
	if( args[2].equalsIgnoreCase( "hash") )
	{
		int size;

		System.out.print("\nUse text word count as hash table size?" + 
				 " yes/no: >> ");
		if( scan.next().equalsIgnoreCase("yes") )
		    size = -1;
		else
		    {
			System.out.print("Enter the size of the hash table: " +
					 ">> ");
			size = scan.nextInt();
		    }
		
		text1 = new Hash( file1, size, args[0] );
		text2 = new Hash( file2, size, args[1]);
	} 
	else
	    {
		text1 = new AVL( file1, args[0]);
		System.out.println("\n");
		text2 = new AVL( file2, args[1]);
	    }


	result = text1.compare_texts( text2 );
       	
	System.out.println( "\nAfter comparing, the final threshold is: "
			    + result + "\n");
	
	System.out.println( "Which is " + result * 100 + "%\n\n" );
	
	file1.close();
	file2.close();
    }
}