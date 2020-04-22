package cybuf.serializer;

import java.lang.reflect.InvocationTargetException;

public class ArraySerializer implements ObjectSerializer
{
    public final static ArraySerializer instance = new ArraySerializer();
    @Override
    public void write(Object obj, CybufSerializer serializer) throws InvocationTargetException, IllegalAccessException
    {
        serializer.writeStartCharWithFormat('[');

        Object[] array = (Object[]) obj;
        for(int i=0;i<array.length;++i)
        {
            serializer.write(array[i]);
            if(i != array.length - 1)
            {
                serializer.writeSeparator();
            }
        }

        serializer.writeEndCharWithFormat(']');
    }
}
