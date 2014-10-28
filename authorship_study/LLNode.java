

public class LLNode
{
    protected Word _data;
    protected LLNode _next;

    public LLNode( Word d, LLNode n)
    {
	_data = d;
	_next = n;
    }

    public void set_data(Word d)
    {
	_data = d;
    }

    public void set_next(LLNode next)
    {
	_next = next;
    }

    public Word get_data()
    {
	return _data;
    }

    public LLNode get_next()
    {
	return _next;
    }

    public String toString()
    {
	return "" + _data;
    }
}