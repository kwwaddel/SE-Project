
import java.util.*;

public class Queue<E> {
	
	ArrayList<E> a = new ArrayList<E>();
	int size = 0;
	
	public void add(E e)
	{
		a.add(e);
		size++;
	}
	
	public E remove()
	{
		E e = a.remove(0);
		size--;
		return e;
	}
	
	public boolean isEmpty()
	{
		boolean b = true;
		if (size > 0)
			b = false;
		
		return b;
	}

}
