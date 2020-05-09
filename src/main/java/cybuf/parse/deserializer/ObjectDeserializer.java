package cybuf.parse.deserializer;

public interface ObjectDeserializer
{
    <T> Object deserialize(Object object,CybufDeserializer deserializer,Class<T> clazz);
}
