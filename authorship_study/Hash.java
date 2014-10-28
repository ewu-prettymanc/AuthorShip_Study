import java.util.*;
import java.io.*;

public class Hash extends Text
{
    protected RecursiveLL[] _table;
    protected int _table_size;

    public Hash( Scanner s, int size, String file) 
    {
	super(s,file );

	int i;
	// use file length for hash size or user hash size
	if( size < 0 )
	    {
		_table_size = _text_length;
		
		System.out.println( file + " hash table size is " + 
				    _text_length );
	    }
	else
	    _table_size = size;

	_table = new RecursiveLL[_table_size];
	
	for ( i = 0; i < _table_size; i ++ )
	    _table[i] = new RecursiveLL();

	store_text(); // store the text into the hash table
    }

   
    public int hash_function( Word w )
    {
	return Math.abs( w.get_hash() % _table_size );
    }

   
    public void add( String s)
    {
	Word w = new Word( _text_length );
	w.add_word( s );
	int i = hash_function( w );

	// add the word to the list...the special add method
	_table[i].add_to_list( w );
    }


     /* For each element in the table it searches for similar 
     * elements in the other hash table. If it does find one it 
     * calculates a difference^2 and keeps a running total.
     * Once its checked all elements it returns that total
     */
    public double compare_texts( Text t )
    {
	if( ! (t instanceof Hash) )
	    throw new RuntimeException( "Comparing apples to oranges in Hash.");
	
	Hash other = (Hash) t;
	double total=0, diff;
	int i;
	Word my_word, his_word;

	for( i=0; i< _table_size; i++)
	    {
		my_word = _table[i].get_next_node();
		
		// returns null if _table[i] is empty
		while( my_word != null )
		    {
			his_word =  other.find( my_word);
			
			// returns null if other has no match
			if( his_word != null )
			    {// calculate geometric square
				diff = his_word.get_percent() - 
				    my_word.get_percent();

				total += diff*diff;
			    }

			my_word = _table[i].get_next_node();
		    }
	    }

	return total;
    }


    /* The find method searches for a similar Node. If it does find one it 
     * returns that Node. If it doesn't it then returns null 
     */
    public Word find( Word w)
    {
	int i = hash_function( w );
	return _table[i].find( w ); 
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