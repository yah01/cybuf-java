package cybuf.parse.deserializer;

import cybuf.CybufArray;
import cybuf.CybufException;

import java.lang.reflect.Array;

public class ArrayDeserializer implements ObjectDeserializer
{
    public final static ArrayDeserializer instance = new ArrayDeserializer();
    @Override
    public <T> Object deserialize(Object object, CybufDeserializer deserializer,Class<T> clazz)
    {
        if(object.getClass() != CybufArray.class)
        {
            throw new CybufException("ArrayDeserializer error, not array type");
        }
        CybufArray cybufArray = (CybufArray) object;
        Integer length = cybufArray.size();
        T newInstance = (T) Array.newInstance(clazz.getComponentType(),length);
        for(int i=0;i<length;++i)
        {
            ((Object[])newInstance)[i] = deserializer.deserialize(cybufArray.get(i),clazz.getComponentType());
        }

        return newInstance;
    }
}
