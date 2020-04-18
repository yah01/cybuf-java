import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CybufObject implements Map<String, Object>
{
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private final Map<String, Object> map;

    public CybufObject(Map<String, Object> map)
    {
        this.map = map;
    }
    public CybufObject()
    {
        this(DEFAULT_INITIAL_CAPACITY);
    }
    public CybufObject(int initialCapacity)
    {
        this.map = new HashMap<String,Object>(initialCapacity);
    }

    @Override
    public int size()
    {
        return this.map.size();
    }

    @Override
    public boolean isEmpty()
    {
        return this.map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key)
    {
        return this.map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value)
    {
        return this.map.containsValue(value);
    }

    @Override
    public Object get(Object key)
    {
        return this.map.get(key);
    }

    @Override
    public Object put(String key, Object value)
    {
        return this.map.put(key, value);
    }

    @Override
    public Object remove(Object key)
    {
        return this.map.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ?> m)
    {
        this.map.putAll(m);
    }

    @Override
    public void clear()
    {
        this.map.clear();
    }

    @Override
    public Set<String> keySet()
    {
        return this.map.keySet();
    }

    @Override
    public Collection<Object> values()
    {
        return this.map.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet()
    {
        return this.map.entrySet();
    }

    @Override
    public String toString()
    {
        return this.map.toString();
    }
}
