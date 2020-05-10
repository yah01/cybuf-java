package cybuf.parse.deserializer;

import cybuf.CybufArray;
import cybuf.CybufException;
import cybuf.CybufObject;
import cybuf.util.Creator;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CybufDeserializer
{
    private final static Map<String,ObjectDeserializer> deserializers = new HashMap<>();
    static
    {
        deserializers.put(String.class.getName(), StringDeserializer.instance);
        deserializers.put(Integer.class.getName(), IntegerDeserializer.instance);
        deserializers.put(int.class.getName(),IntegerDeserializer.instance);
        deserializers.put(Double.class.getName(), DoubleDeserializer.instance);
        deserializers.put(double.class.getName(),DoubleDeserializer.instance);
        deserializers.put(Boolean.class.getName(), BooleanDeserializer.instance);
        deserializers.put(boolean.class.getName(),BooleanDeserializer.instance);
        deserializers.put(Object[].class.getName(), ArrayDeserializer.instance);
        deserializers.put(List.class.getName(),ListDeserializer.instance);
        deserializers.put(Map.class.getName(),MapDeserializer.instance);
        deserializers.put(Character.class.getName(),CharDeserializer.instance);
        deserializers.put(char.class.getName(),CharDeserializer.instance);
        deserializers.put("nil",NullDeserializer.instance);
    }
    public Object deserialize(Object obj, Type type)
    {
        final ObjectDeserializer od;
        boolean isParameterizedType = type instanceof ParameterizedType;
        boolean isGenericArrayType = type instanceof GenericArrayType;
        if(isGenericArrayType)
        {
            throw  new CybufException("not support GenericArrayType");
        }
        Class<?> clazz;
        if(isParameterizedType)
        {
            clazz = (Class<?>)((ParameterizedType)type).getRawType();
        }
        else
        {
            clazz = (Class<?>) type;
        }

        if(obj == null)
        {
            od = deserializers.get("nil");
        }
        else if(obj.getClass() == CybufArray.class)
        {
            if(!List.class.isAssignableFrom(clazz) && !clazz.isArray())
            {
                throw new CybufException("can only deserialize to list or array");
            }
            if(clazz == CybufArray.class)
            {
                return obj;
            }
            if(clazz.isArray())
            {
                od = getObjectDeserializer(Object[].class);
            }
            else
            {
                od = getObjectDeserializer(List.class);
            }
        }
        else if(obj.getClass() == CybufObject.class)
        {
            if(clazz == CybufObject.class)
            {
                return obj;
            }
            if(List.class.isAssignableFrom(clazz) || clazz.isArray())
            {
                throw new CybufException("can not deserialize to list or array");
            }
            od = getObjectDeserializer(clazz);
        }
        else
        {
            od = getObjectDeserializer(clazz);
        }
        return od.deserialize(obj,this,type);
    }

    public ObjectDeserializer getObjectDeserializer(Class<?> clazz)
    {
        if(deserializers.containsKey(clazz.getName()))
        {
            return deserializers.get(clazz.getName());
        }
        else
        {
            ObjectDeserializer od = Creator.createJavaBeanDeserializer(clazz);
            deserializers.put(clazz.getName(),od);
            return od;
        }
    }
}
