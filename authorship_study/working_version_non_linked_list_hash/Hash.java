import java.util.*;
import java.io.*;

public class Hash extends Text
{
    protected Word[] _table;
    protected int _table_size;

    public Hash( Scanner s, int size, String file) 
    {
	super(s,file );
	int i;
	
	// use file length for hash size or user hash size
	if( size < 0 )
	    _table_size = _text_length;
	else
	    _table_size = size;

	_table = new Word[_table_size];
	
	for ( i = 0; i < _table_size; i ++ )
	    _table[i] = new Word( _text_length );

	store_text(); // store the text into the hash table
    }

    public int hash_function(Object o)
    {
	if( (o instanceof Word ) )
	    { // for hashing a word object
		Word w = (Word) o;
		return Math.abs( w.get_hash_val() % _table_size );
	    }
	    
	String s = (String) o;
 	
	return Math.abs( s.hashCode() % _table_size );
    }

   
    public void add(Object o)
    {
	int i = hash_function(o);

	_table[i].add_word( o );
    }

    public boolean search(Object o)
    {
	int i = hash_function(o);

	if ( _table[i].compareTo( o ) == 0)
	    return true;

	return false;
    }

    /* The find method searches for a similar Node. If it does find one it 
     * returns that Node. If it doesn't it then returns null 
     */
    public Object find( Object o )
    {
	int i = hash_function( o );
	
	if( _table[i].compare( o ))
	    return _table[i];  // we found it return the element
	
	return null;// we didn't find it
    }

    public void delete(Object o)
    {
	int i = hash_function(o);

	_table[i].delete_word();
    }

    /* For each element in the table it searches for similar 
     * elements in the other hash table. If it does find one it 
     * calculates a difference^2 and keeps a running total.
     * Once its checked all elements it returns that total
     */
    public double compare_texts( Object o )
    {
	if( ! (o instanceof Hash) )
	    throw new RuntimeException( "Comparing apples to oranges in Hash.");
	
	Hash other = (Hash) o;
	double total=0, diff;
	int i;
	Word element;

	for( i=0; i< _table_size; i++)
	    {
		element = (Word) other.find( _table[i] );
		if( element != null )
		    {// calculate geometric square
			diff = element.get_percent() - _table[i].get_percent();
			total += diff*diff;
		    }
	    }

	return total;
    }


    public String toString()
    {
	int i;
	String s = "";

	for( i=0; i < _table_size; i++ ) 
	    s +=  _table[i].toString();

	return s;
    }
}
