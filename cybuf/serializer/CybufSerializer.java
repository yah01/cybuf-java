package cybuf.serializer;

import cybuf.util.JavaBeanSerializerCreator;

import java.lang.reflect.InvocationTargetException;
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
        initialSerializers();
    }

    private void initialSerializers()
    {
        serializers.put(String.class.getName(),StringSerializer.instance);
        serializers.put(Integer.class.getName(),IntegerSerializer.instance);
        serializers.put(int.class.getName(),IntegerSerializer.instance);
        serializers.put("null",NullSerializer.instance);
    }

    public void write(Object object) throws InvocationTargetException, IllegalAccessException
    {
        final ObjectSerializer os;
        if(object == null)
        {
            os = serializers.get("null");
        }
        else
        {
            os = getObjectSerializer(object.getClass());
        }


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

    public void writeChar(char c)
    {
        writer.writeChar(c);
    }

    public void writeString(String value)
    {
        writer.writeString(value);
    }

    public void writeFieldName(String fieldName)
    {
        writer.writeFieldName(fieldName);
    }
    public void writeNull()
    {
        writer.writeNull();
    }
    public void increaseTab()
    {
        writer.increaseTab();
    }

    public void decreaseTab()
    {
        writer.decreaseTab();
    }

    public void writeln()
    {
        writer.writeln();
    }

}
