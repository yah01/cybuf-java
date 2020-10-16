package cybuf.parse.deserializer;

import cybuf.CybufArray;
import cybuf.CybufException;

import java.lang.reflect.Array;
import java.lang.reflect.Type;

public class ArrayDeserializer implements ObjectDeserializer
{
    public final static ArrayDeserializer instance = new ArrayDeserializer();
    @Override
    public Object deserialize(Object object, CybufDeserializer deserializer, Type type)
    {
        if(object.getClass() != CybufArray.class)
        {
            throw new CybufException("ArrayDeserializer error, not array type");
        }
        CybufArray cybufArray = (CybufArray) object;
        int length = cybufArray.size();
        Class<?> clazz = (Class<?>) type;
        Object newInstance = Array.newInstance(clazz.getComponentType(),length);
        for(int i=0;i<length;++i)
        {
            ((Object[])newInstance)[i] = deserializer.deserialize(cybufArray.get(i),clazz.getComponentType());
        }

        return newInstance;
    }
}
