package cybuf.serializer;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapSerializer implements ObjectSerializer
{
    public final static MapSerializer instance = new MapSerializer();
    @Override
    public void write(Object obj, CybufSerializer serializer) throws InvocationTargetException, IllegalAccessException
    {
        serializer.writeStartCharWithFormat('{');

        Map<String,Object> map = (Map) obj;
        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();

        while(it.hasNext())
        {
            Map.Entry<String,Object> entry = it.next();
            String fieldName = entry.getKey();
            serializer.writeFieldName(fieldName);
            Object value = entry.getValue();
            serializer.write(value);
            if(it.hasNext())
            {
                serializer.writeSeparator();
            }
        }
        serializer.writeEndCharWithFormat('}');
    }
}
