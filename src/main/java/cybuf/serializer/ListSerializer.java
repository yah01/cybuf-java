package cybuf.serializer;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ListSerializer implements ObjectSerializer
{
    public final static ListSerializer instance = new ListSerializer();
    @Override
    public void write(Object obj, CybufSerializer serializer) throws InvocationTargetException, IllegalAccessException
    {
        serializer.writeStartCharWithFormat('[');

        List<Object> list = (List) obj;
        int len = list.size();
        for(int i=0;i<len;++i)
        {
            serializer.write(list.get(i));
            if(i != len - 1)
            {
                serializer.writeSeparator();
            }
        }
        serializer.writeEndCharWithFormat(']');
    }
}
