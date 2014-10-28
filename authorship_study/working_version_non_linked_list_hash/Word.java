import java.util.*;

public class Word implements Comparable
{
    protected int _hash_value;
    protected String _word;
    protected int _word_count;
    protected double _percentage;
    protected int _text_count;
   
    public Word( int w )
    {
	_word_count = 0;// number of words stored in this object
	_text_count = w;  // number of document words
	_word = null; // haven't store a word yet
	_percentage = 0;
    }

    public void increase_count( int i )
    {
	_word_count += i;
	_percentage = (double)_word_count / _text_count; // update percentage
	
	//	System.out.println( "THE WORD count is " + _word_count + 
	//		    "  percent is " + _percentage + 
	//		    "  text size is " + _text_count );
    }
    
    public void add_word( Object o )
    {
	if( ! (o instanceof String ) )
	    throw new RuntimeException("Cannot add Object to Word.");

	_word = (String) o;
	
	if( _word_count == 0 ) // only store new word if it is empty
	    _word = (String) o;
	
	_word_count++;

	// assume that if a word is hashed into this element it is the 
	// same word

	_hash_value = _word.hashCode(); // get the words hash value
	_percentage = (double)_word_count / _text_count; // update percentage

	//System.out.println( "\nWORD IS "+ _word );
	//System.out.println( "The word count is " + _word_count + 
	//		    "  percent is " + _percentage + 
	//		    "  text size is " + _text_count );
    }
    
    public void delete_word( )
    {
	if( _word_count > 0 )
	    _word_count--;

    }

    public boolean compare( Object o )
    {
	if( !( o instanceof Word ) )
	    throw new RuntimeException("Comparing apples to oranges in Word.");
	
	Word w = (Word) o;

	if( _word == null || w._word == null )
	    return false; // make sure neither are empty

	return _word.equalsIgnoreCase(w._word );
    }


    // returns the alphabetic comparison
    public int compareTo( Object o )
    {
	if( !( o instanceof Word) )
	    throw new RuntimeException("Comparing apples to oranges in Word.");

	Word w = (Word) o;
	
	if( _word == null || w._word == null )
	    throw new RuntimeException( "Comparing empty word objects.");
	
	return _word.compareTo( w._word );
    }

    /* public int compareTo( Object o )
    {
	if( !( o instanceof Word ) )
	    throw new RuntimeException( "Comparing apples to oranges in Word.");

	Word w = (Word) o ;

	if( _hash_value >  w._hash_value )
	    return 1; // its greater

	if(  _hash_value <  w._hash_value )
	    return -1; // its less
	
	return 0; // its equal
	}*/

    public String toString()
    {
	if( _word == null )
	    return "";

	return " (" + _word + " " + _percentage + ")" ;
    }
    
    public double get_percent()
    {
	return _percentage;
    }

    public int get_word_count()
    {
	return _word_count;
    }


    public int get_hash_val()
    {
	return _hash_value;
    }
}