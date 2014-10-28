

public class LLNode
{
    protected Object _data;
    protected LLNode _next;

    public LLNode(Object d, LLNode n)
    {
	_data = d;
	_next = n;
    }

    public void set_data(Object d)
    {
	_data = d;
    }

    public void set_next(LLNode next)
    {
	_next = next;
    }

    public Object get_data()
    {
	return _data;
    }

    public LLNode get_next()
    {
	return _next;
    }
      
}