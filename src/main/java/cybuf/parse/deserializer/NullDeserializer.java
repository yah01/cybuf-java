package cybuf.parse.deserializer;

public class NullDeserializer implements ObjectDeserializer
{
    public final static NullDeserializer instance = new NullDeserializer();
    @Override
    public <T> Object deserialize(Object object, CybufDeserializer deserializer,Class<T> clazz)
    {
        return null;
    }
}
