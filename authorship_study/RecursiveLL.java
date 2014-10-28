public class RecursiveLL
{
    protected LLNode _head;
    protected int _size;

    public RecursiveLL()
    {
	_head = null;
	_size = 0;
    }

    /* add_to_list. Basically add to back recursive function 
     * However if it comes across an identicall word it adds one to that 
     * words count
     */
    public void add_to_list( Word d )
    {
	_head = add_to_list_rec( _head, d );
	_size++;
    }
    
    protected LLNode add_to_list_rec( LLNode n, Word d )
    {
	if( n == null )
	    return new LLNode( d, null );

	if(  d.compareTo( n.get_data() ) == 0 )
	    {
		n.get_data().increase_count( 1 ); 
		// add one to the words count
		return n;
	    }

	n.set_next( add_to_list_rec( n.get_next(), d ) ) ;
	
	return n;
    }
   

    /* get_next_node.. returns the next node in the list.
     * if there isn't any more it return null;
     */
    public Word get_next_node()
    {
	if( _head == null )
	    return null;
	
        Word data = _head.get_data();
	LLNode old_head = _head;

	_head = old_head.get_next();
	old_head = null;

	return data;
    }

    public String toString()
    {
	return toString_rec(_head);
    }

    protected String toString_rec(LLNode n)
    {
	if ( n == null )
	    return "";

	return "" + n + " " + toString_rec(n.get_next());
    }
    
  
    /* returns the element which matches the one being searched for 
     */
    public Word find( Word w )
    {
	return find_rec( _head, w );
    }

    protected Word find_rec(LLNode n, Word w )
    {
	if( n == null )
	    return null; // not found
	  
	if ( n.get_data().compareTo( w ) == 0 )
	    return n.get_data(); // we've found a match
	 
	return find_rec( n.get_next(), w );
    }
}