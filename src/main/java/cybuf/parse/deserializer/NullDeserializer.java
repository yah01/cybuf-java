package cybuf.parse.deserializer;

public class NullDeserializer implements ObjectDeserializer
{
    public final static NullDeserializer instance = new NullDeserializer();
    @Override
    public Object deserialize(Object object, CybufDeserializer deserializer)
    {
        return null;
    }
}
