package cybuf.parse.deserializer;

import java.lang.reflect.Type;

public class ListDeserializer implements  ObjectDeserializer
{
    public final static ListDeserializer instance = new ListDeserializer();
    @Override
    public Object deserialize(Object object, CybufDeserializer deserializer, Type type)
    {
        return null;
    }
}
