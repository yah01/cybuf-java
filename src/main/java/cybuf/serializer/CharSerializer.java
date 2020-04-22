package cybuf.serializer;

import cybuf.CybufException;

import java.lang.reflect.InvocationTargetException;

public class CharSerializer implements ObjectSerializer
{
    public final static CharSerializer instance = new CharSerializer();
    @Override
    public void write(Object obj, CybufSerializer serializer) throws InvocationTargetException, IllegalAccessException
    {
        if(obj.getClass() == char.class || obj.getClass() == Character.class)
        {
            serializer.writeCharacter((Character) obj);
        }
        else
        {
            throw new CybufException("CharSerializer write error");
        }
    }
}
