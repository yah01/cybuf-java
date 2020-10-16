package cybuf.parse.deserializer;

import java.lang.reflect.Type;

public class MapDeserializer implements ObjectDeserializer
{
    public final static MapDeserializer instance = new MapDeserializer();
    @Override
    public Object deserialize(Object object, CybufDeserializer deserializer, Type type)
    {
        return null;
    }
}
