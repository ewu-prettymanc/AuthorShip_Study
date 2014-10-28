import java.util.*;
import java.io.*;


/* This is the abstract parent class. 
 * It calculates the text length and parses each word and stores it in the 
 * child data structure. It is abstract and guarantees a add and compare
 * texts method inside the child
 */
public abstract class Text 
{
    protected int _text_length;
    protected Scanner in;
    protected String file;

    protected abstract void add( Object o );
    public abstract double compare_texts( Object o );

    public Text( Scanner s, String filename ) 
    {
	file = filename;

	_text_length=0;
	in = s;

	while( in.hasNext() )
	    {
		in.next();
		_text_length++;
	    }

	in.close();
	try
	    {
		in = new Scanner( new FileReader( filename ) );
		// reset the scanner to beginning of file
	    }
	catch ( Exception e )
	    {
		System.err.println( e );
		throw new RuntimeException("Reopen of file " + 
					   filename +" failed. ");
	    }
    }


    protected void store_text( )
    {
	String temp;

	while( in.hasNext() )
	    {
		temp = in.next();
		temp = parse_word( temp );
		
		// make sure we aren't adding an empty string
		if( ! temp.isEmpty() )
		    add( temp );
	    }
    }


    protected String parse_word( String instring )
    {
	StringBuffer new_word = new StringBuffer();
	char c;
	
	for(int i = 0; (i < instring.length() ) && (i < 5); i++ )
	    //read in individually the first 5 characters of 
	    // each word
	    {
		c = instring.charAt( i );
		if( Character.isLetter( c ) ) // check to make sure its letter
		    new_word.append( Character.toLowerCase( c ) ); 
		// append to end of buffer  if it is a letter
	    }
	
	return new_word.toString();
    }

    // returns the length of the text in number of words
    protected int get_text_length()
    {
	return _text_length;
    }
 
}