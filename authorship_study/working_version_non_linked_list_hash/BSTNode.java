

public class BSTNode 
{
    private Word _data;
    private BSTNode _left; // less
    private BSTNode _right; // more 
    private int _height, _lefth, _righth;

    private int _x, _y;
    
    public int get_height()
    {
	return _height;
    }

    public void set_xy(int x, int y)
    {
	_x = x;
	_y = y;
    }

    public int get_x() 
    {
	return _x;
    }

    public int get_y()
    {
	return _y;
    }


    public BSTNode(Word d)
    {
	_data = d;
	_left = _right = null;
	_height = 0;
	_lefth = _righth = -1;
    }

    public void set_data(Word d)
    {
	_data = d;
    }

    public void set_left(BSTNode left)
    {
	_left = left;
	find_height();
    }

    public void set_right(BSTNode r)
    {
	_right = r;
	find_height();
    }

    private void find_height()
    {
	_lefth = (_left == null)? -1 : _left.get_height();
	_righth = (_right == null)? -1 : _right.get_height();

	_height = (_righth > _lefth)? _righth: _lefth;
	_height ++;
	
    }

    public String toString()
    {
	return "" + _data + "--" + _height;
    }

    public boolean unbalanced()
    {
	return Math.abs( _lefth - _righth ) >= 2;
    }

    public BSTNode get_left()
    {
	return _left;
    }

    public BSTNode get_right()
    {
	return _right;
    }

    public Word get_data()
    {
	return _data;
    }
}