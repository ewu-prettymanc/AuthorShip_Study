import java.util.*;

public class Word implements Comparable
{
    protected String _word;
    protected int _word_count;
    protected double _percentage;
    protected int _text_count;
    protected int _hash_val;
   
    public Word( int i )
    {
	_word_count = 0;// number of words stored in this object
	_text_count = i;  // number of document words
	_word = null; // haven't store a word yet
	_percentage = 0;
    }
   

    public void increase_count( int i )
    {
	_word_count += i;
	_percentage = (double)_word_count / _text_count; // update percentage
    }
    
    /* add_word stores the String and stores the 
     * the percentage of the text length
     */
    public void add_word( String s )
    {
	_word = s;
	_word_count++;
	
	_hash_val = s.hashCode();
	_percentage = (double)_word_count / _text_count; // update percentage
    }


    public String get_word()
    {
	return _word;
    }

    public int get_hash( )
    {
	return _hash_val;
    }
    
    public void delete_word( )
    {
	if( _word_count > 0 )
	    _word_count--;
    }

    public int compareTo( Object o )
    {
	if( !( o instanceof Word) )
	    throw new RuntimeException("Comparing apples to oranges in Word.");

	Word w = (Word) o;
	
	if( _word == null || w._word == null )
	    throw new RuntimeException( "Comparing empty word objects.");
	
	return _word.compareTo( w._word );
    }

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
}