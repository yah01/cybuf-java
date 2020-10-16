package cybuf.parse.deserializer;

import cybuf.CybufArray;
import cybuf.CybufException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ListDeserializer implements  ObjectDeserializer
{
    public final static ListDeserializer instance = new ListDeserializer();
    @Override
    public Object deserialize(Object object, CybufDeserializer deserializer, Type type)
    {
        if(object.getClass() != CybufArray.class)
        {
            throw new CybufException("ListDeserializer error, not list type");
        }
        CybufArray cybufArray = (CybufArray) object;
        boolean isParameterizedType = type instanceof ParameterizedType;
        Class<?> rawType ;
        Type actualType;
        if(isParameterizedType)
        {
            rawType = (Class<?>) ((ParameterizedType)type).getRawType();
            actualType = ((ParameterizedType)type).getActualTypeArguments()[0];
        }
        else
        {
            throw new CybufException("ListDeserializer error, not parameterizedType");
        }

        try
        {
            Object newInstance = rawType.getConstructor().newInstance();
            for (Object o : cybufArray)
            {
                ((List) newInstance).add(deserializer.deserialize(o, actualType));
            }
            return newInstance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
        {
            throw new CybufException("ListDeserializer error, " + e.getMessage());
        }
    }
}
