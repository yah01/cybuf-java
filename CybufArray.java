import java.util.*;

public class CybufArray implements List<Object>
{
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private final List<Object> list;
    public CybufArray()
    {
        this(DEFAULT_INITIAL_CAPACITY);
    }
    public CybufArray(int initialCapacity)
    {
        this.list = new ArrayList<Object>(initialCapacity);
    }
    public CybufArray(ArrayList<Object> list)
    {
        this.list = list;
    }

    @Override
    public int size()
    {
        return this.list.size();
    }

    @Override
    public boolean isEmpty()
    {
        return this.list.isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return this.list.contains(o);
    }

    @Override
    public Iterator<Object> iterator()
    {
        return this.list.iterator();
    }

    @Override
    public Object[] toArray()
    {
        return this.list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        return this.list.toArray(a);
    }

    @Override
    public boolean add(Object o)
    {
        return this.list.add(o);
    }

    @Override
    public boolean remove(Object o)
    {
        return this.list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        return this.list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<?> c)
    {
        return this.list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<?> c)
    {
        return this.list.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        return this.list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        return this.list.removeAll(c);
    }

    @Override
    public void clear()
    {
        this.list.clear();
    }

    @Override
    public Object get(int index)
    {
        return this.list.get(index);
    }

    @Override
    public Object set(int index, Object element)
    {
        return this.list.set(index, element);
    }

    @Override
    public void add(int index, Object element)
    {
        this.list.add(index, element);
    }

    @Override
    public Object remove(int index)
    {
        return this.list.remove(index);
    }

    @Override
    public int indexOf(Object o)
    {
        return this.list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o)
    {
        return this.list.lastIndexOf(o);
    }

    @Override
    public ListIterator<Object> listIterator()
    {
        return this.list.listIterator();
    }

    @Override
    public ListIterator<Object> listIterator(int index)
    {
        return this.list.listIterator(index);
    }

    @Override
    public List<Object> subList(int fromIndex, int toIndex)
    {
        return this.list.subList(fromIndex, toIndex);
    }
}
