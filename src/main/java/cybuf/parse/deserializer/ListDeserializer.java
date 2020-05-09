package cybuf.parse.deserializer;

public class ListDeserializer implements  ObjectDeserializer
{
    public final static ListDeserializer instance = new ListDeserializer();
    @Override
    public <T> Object deserialize(Object object, CybufDeserializer deserializer,Class<T> clazz)
    {
        return null;
    }
}
