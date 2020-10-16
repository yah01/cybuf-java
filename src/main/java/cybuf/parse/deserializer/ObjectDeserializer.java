package cybuf.parse.deserializer;

import java.lang.reflect.Type;

public interface ObjectDeserializer
{
    Object deserialize(Object object, CybufDeserializer deserializer, Type type);
}
