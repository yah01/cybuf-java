package cybuf.serializer;

import java.lang.reflect.InvocationTargetException;

public class DoubleSerializer implements ObjectSerializer
{
    public final static DoubleSerializer instance = new DoubleSerializer();
    @Override
    public void write(Object obj, CybufSerializer serializer) throws InvocationTargetException, IllegalAccessException
    {
        serializer.writeBaseObject(obj);
    }
}
