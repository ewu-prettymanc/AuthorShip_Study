import java.io.*;
import java.util.*;


public class AVL extends Text
{
    private BSTNode _root;

    public AVL( Scanner s, String file)
    {
	super(s, file );
	_root = null;

	store_text(); // store the text into the AVL tree;
    }

    
    public double compare_texts( Text t )
    {
	if( !( t instanceof AVL ) )
	    throw new RuntimeException("Comparing apples " 
				       + "to oranges in compare_texts");
    
	AVL other = (AVL) t ;

	return calc_total( 0, _root, other);
    }

    /* The calc_total method recurses preorder fashion. Each node of the 
     * tree is then searched for in the other AVL tree. 
     * if it finds a mutual element it then finds the difference and 
     * keeps a running total. Once it reaches the end it the returns that
     * total. When its checked each node of the tree it returns the sum
     * of the left and right comparisons.
     */
    protected double calc_total( double total, BSTNode n , AVL other)
    {
	if( n == null ) // we are at the end of the list...recurse back.
	    return total;
	    
	Word w = other.find( n.get_data() );
	// find if the other tree has a match 

	if( w != null) // means we've found a match
	    {
		double diff = w.get_percent() - n.get_data().get_percent();
		total += diff*diff;
	    }

	// each time we call calc_total with left=right=0 so we get
	// that individual sum
	double left=0, right=0;

	left = calc_total( total, n.get_left() , other);
	
	total = 0; // start the right out at sum of zero

	right = calc_total( total, n.get_right(), other );

	return left + right;
    }


    /* The find method searches through the other tree looking for a similar 
     * piece of data. Once it finds it it returns that element which matched the 
     * data. 
     */
    public Word find( Word o )
    {
	return find_rec( o, _root );
    }

    public Word find_rec( Word d, BSTNode n )
    {
	if( n == null )
	    return null; // we didn't find it, return null

	if( n.get_data().compareTo( d ) == 0 )
	    return n.get_data();// return the element that matches d

	if( d.compareTo( n.get_data() ) < 0 ) // search the left tree
	    return find_rec( d, n.get_left() );
	else
	    return find_rec( d, n.get_right() ); // search the right tree
    }



    public void add( String s)
    {
	// create a new word to be stored or added to another node
	Word w = new Word( _text_length );
	w.add_word( s );
	
	_root = add_rec(w, _root);
    }

    public BSTNode add_rec( Word d, BSTNode  n)
    {
	if ( n == null )
	    return new BSTNode (d);

	// if its the same word then add one to that datums count
	if( d.compareTo( n.get_data() ) == 0 )
	    n.get_data().increase_count( 1 ); // same word...increase its count
	else if ( d.compareTo(  n.get_data() ) < 0 )
	    n.set_left(    add_rec(d, n.get_left() ) );
	else
	    n.set_right(   add_rec(d, n.get_right() ) );

	if ( n.unbalanced() )
	    return rotate_helper(d,n);

	return n;
    }





    //***************BASIC AVL METHODS*******************//
    protected BSTNode rotate_helper(Word d, BSTNode n)
    {
	if ( d.compareTo(  n.get_data() ) < 0 ) // left
	{
	    if ( d.compareTo( n.get_left().get_data() ) < 0   )  // outside
		return single_rotate_right(n);
	    else
		return double_rotate_right(n);                   // inside
	}
	else // right hand side
	{
	    if ( d.compareTo( n.get_right().get_data() ) < 0   )  // inside
		return double_rotate_left(n);
	    else
		return single_rotate_left(n);                   // outside
	}
    }
   
    protected BSTNode single_rotate_right(BSTNode  n)
    {
	BSTNode newroot = n.get_left();
	n.set_left( newroot.get_right() );
	newroot.set_right( n );

	return newroot;
    }

    protected BSTNode single_rotate_left(BSTNode n)
    {
	BSTNode newroot = n.get_right();
	n.set_right( newroot.get_left() );
	newroot.set_left( n );

	return newroot;
    }

    protected BSTNode double_rotate_right(BSTNode n)
    {
	n.set_left( single_rotate_left(n.get_left()) );
	return single_rotate_right(n);
    }


    protected BSTNode double_rotate_left(BSTNode n)
    {
	n.set_right( single_rotate_right(n.get_right()) );
	return single_rotate_left(n);
    }

    public boolean search(Word d)
    {
	return search_rec(d, _root);
    }

    protected boolean search_rec(Word d, BSTNode n)
    {
	if ( n == null )
	    return false;

	if ( n.get_data().equals(d) )
	    return true; // may need to override this here!!!

	if ( d.compareTo( n.get_data() ) < 0 )
	    return search_rec(d, n.get_left() );
	else
	    return search_rec(d, n.get_right() );
    }

    public String toString()
    {
	return toString_rec(_root);
    }

    protected String toString_rec(BSTNode n)
    {
	if ( n == null )
	    return "";

	return toString_rec(n.get_left()) + " " +
	    n + " " + toString_rec(n.get_right());
    }

    
    //*************STRUCTURE PRINT**********************/
    public String structure_print()
    {
	if ( _root == null )
	    return "empty tree";

	Space req = find_space_req(_root); 
	int cell_size;

	String[][] tree = new String[req.get_cols()][req.get_rows()];

	place_xy(_root, 0, 0);

	place_data(tree, _root); // place the data into the 2d array

	cell_size = init_tree(tree, req);

	return print_2d_array(tree, req, cell_size);
    }

    private int init_tree(String[][] tree, Space req)
    {
	int x,y;
	int cell_size = 0;
	String s;

	for ( y = 0; y < req.get_rows(); y ++ )
	    for ( x = 0; x < req.get_cols(); x ++ )
		if ( tree[x][y] != null && cell_size < tree[x][y].length() )
		    cell_size = tree[x][y].length();

	char[] space = new char[cell_size];
	for ( x = 0; x < cell_size; x++ )
	    space[x] = ' ';

	s = new String(space);

	for ( y = 0; y < req.get_rows(); y ++ )
	    for ( x = 0; x < req.get_cols(); x ++ )
		if ( tree[x][y] == null )
		    tree[x][y] = s;

	return cell_size;
    }

    private String print_2d_array(String[][] tree, Space req, int cell_size)
    {
	StringWriter sw = new StringWriter();
	PrintWriter sout = new PrintWriter( sw );
	String format = "%" + cell_size + "s";
	int x, y;

	for ( y = 0; y < req.get_rows(); y ++ )
	{
	    for ( x = 0; x < req.get_cols(); x ++ )
		sout.printf(format, tree[x][y]);

	    sout.println();
	}

	return sw.toString();

    }


    // how many cols I have used
    private int place_xy(BSTNode n, int min_x, int y)
    {
	if ( n == null )
	    return 0;

	int used_left = place_xy(n.get_left(), min_x, y+1);
	n.set_xy(used_left+min_x, y);
	int used_right = place_xy(n.get_right(), min_x+used_left+1, y+1);

	return used_right + used_left + 1;
    }


    private void place_data(String[][] tree, BSTNode n)
    {
	if ( n == null )
	    return;

	tree[n.get_x()][n.get_y()] = n.toString();

	place_data(tree, n.get_left());
	place_data(tree, n.get_right());


    }


    private Space find_space_req(BSTNode n)
    {
	Space left, right, me;
	int max;

	if ( n == null )
	    return new Space(0,0);

	left = find_space_req(n.get_left());
	right = find_space_req(n.get_right());

	max = left.get_rows();
	if ( right.get_rows() > max )
	    max = right.get_rows();

	me = new Space(max+1,
		       left.get_cols() + right.get_cols() + 1);

	return me;
    }
    
}