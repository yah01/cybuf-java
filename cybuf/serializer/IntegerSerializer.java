package cybuf.serializer;

import java.lang.reflect.InvocationTargetException;

public class IntegerSerializer implements ObjectSerializer
{
    public final static IntegerSerializer instance = new IntegerSerializer();
    @Override
    public void write(Object obj, CybufSerializer serializer) throws InvocationTargetException, IllegalAccessException
    {

    }
}
