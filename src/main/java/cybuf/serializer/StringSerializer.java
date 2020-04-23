package cybuf.serializer;

import cybuf.CybufException;

import java.lang.reflect.InvocationTargetException;

public class StringSerializer implements ObjectSerializer
{
    public final static StringSerializer instance = new StringSerializer();
    @Override
    public void write(Object obj, CybufSerializer serializer) throws InvocationTargetException, IllegalAccessException
    {
        if(obj.getClass() == String.class)
        {
            serializer.writeString((String) obj);
        }
        else
        {
            throw new CybufException("StringSerializer write error");
        }
    }
}
