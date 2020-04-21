package cybuf.serializer;

import java.lang.reflect.InvocationTargetException;

public class NullSerializer implements ObjectSerializer
{
    public final static NullSerializer instance = new NullSerializer();
    @Override
    public void write(Object obj, CybufSerializer serializer) throws InvocationTargetException, IllegalAccessException
    {
        serializer.writeNull();
    }
}
