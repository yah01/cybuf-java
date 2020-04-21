package cybuf.serializer;

import java.lang.reflect.InvocationTargetException;

public class BooleanSerializer implements ObjectSerializer
{
    public final static BooleanSerializer instance = new BooleanSerializer();
    @Override
    public void write(Object obj, CybufSerializer serializer) throws InvocationTargetException, IllegalAccessException
    {
        serializer.writeBaseObject(obj);
    }
}
