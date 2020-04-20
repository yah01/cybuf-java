package cybuf.serializer;

import cybuf.util.JavaBeanSerializerCreator;

import java.util.HashMap;
import java.util.Map;

public class CybufSerializer
{
    private final Map<String,ObjectSerializer> serializers;
    private final SerializerWriter writer;

    public CybufSerializer()
    {
        writer = new SerializerWriter();
        serializers = new HashMap<>();
    }

    public void write(Object object)
    {
        final ObjectSerializer os = getObjectSerializer(object.getClass());

        os.write(object,this);
    }

    private ObjectSerializer getObjectSerializer(Class<?> clazz)
    {
        if(serializers.containsKey(clazz.getName()))
        {
            return serializers.get(clazz.getName());
        }
        else
        {
            ObjectSerializer os = JavaBeanSerializerCreator.createJavaBeanSerializer(clazz);
            serializers.put(clazz.getName(),os);
            return os;
        }
    }

    public String toString()
    {
        return writer.toString();
    }

}
