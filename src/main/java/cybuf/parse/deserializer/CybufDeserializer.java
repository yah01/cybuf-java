package cybuf.parse.deserializer;

import cybuf.CybufArray;
import cybuf.CybufException;
import cybuf.CybufObject;
import cybuf.util.Creator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CybufDeserializer
{
    private final Map<String,ObjectDeserializer> deserializers;
    public CybufDeserializer()
    {
        deserializers = new HashMap<>();
        initialDeserializers();
    }
    private void initialDeserializers()
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
    public <T> T deserialize(Object obj,Class<T> clazz)
    {
        final ObjectDeserializer od;
        if(obj.getClass() == CybufArray.class)
        {
            if(!List.class.isAssignableFrom(clazz) && !clazz.isArray())
            {
                throw new CybufException("can only deserialize to list or array");
            }
            if(clazz == CybufArray.class)
            {
                return (T) obj;
            }
            if(clazz.isArray())
            {
                od = deserializers.get(Object[].class.getName());
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
                return (T) obj;
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
        return (T) od.deserialize(obj,this);
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
