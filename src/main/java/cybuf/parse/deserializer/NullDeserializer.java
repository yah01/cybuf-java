package cybuf.parse.deserializer;

import java.lang.reflect.Type;

public class NullDeserializer implements ObjectDeserializer
{
    public final static NullDeserializer instance = new NullDeserializer();
    @Override
    public Object deserialize(Object object, CybufDeserializer deserializer, Type type)
    {
        return null;
    }
}
