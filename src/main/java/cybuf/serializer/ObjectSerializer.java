package cybuf.serializer;

import java.lang.reflect.InvocationTargetException;

public interface ObjectSerializer
{
    void write(Object obj,CybufSerializer serializer) throws InvocationTargetException, IllegalAccessException;
}
