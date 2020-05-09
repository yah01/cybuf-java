package cybuf.parse.deserializer;

public class MapDeserializer implements ObjectDeserializer
{
    public final static MapDeserializer instance = new MapDeserializer();
    @Override
    public <T> Object deserialize(Object object, CybufDeserializer deserializer,Class<T> clazz)
    {
        return null;
    }
}
