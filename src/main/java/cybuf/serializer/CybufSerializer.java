package cybuf.serializer;

import cybuf.CybufException;
import cybuf.util.JavaBeanSerializerCreator;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CybufSerializer
{
    private final Map<String,ObjectSerializer> serializers;
    private final SerializerWriter writer;
    private final SerializerConfig config;

    public CybufSerializer(SerializerConfig config)
    {
        writer = new SerializerWriter();
        serializers = new HashMap<>();
        this.config = config;
        initialSerializers();
    }
    public SerializerConfig getSerializerConfig()
    {
        return config;
    }

    private void initialSerializers()
    {
        serializers.put(String.class.getName(),StringSerializer.instance);
        serializers.put(Integer.class.getName(),IntegerSerializer.instance);
        serializers.put(int.class.getName(),IntegerSerializer.instance);
        serializers.put(Double.class.getName(),DoubleSerializer.instance);
        serializers.put(double.class.getName(),DoubleSerializer.instance);
        serializers.put(Boolean.class.getName(),BooleanSerializer.instance);
        serializers.put(boolean.class.getName(),BooleanSerializer.instance);
        serializers.put(Object[].class.getName(),ArraySerializer.instance);
        serializers.put(List.class.getName(),ListSerializer.instance);
        serializers.put(Map.class.getName(),MapSerializer.instance);
        serializers.put(Character.class.getName(),CharSerializer.instance);
        serializers.put(char.class.getName(),CharSerializer.instance);
        serializers.put("nil",NullSerializer.instance);
    }

    public void write(Object object) throws InvocationTargetException, IllegalAccessException
    {
        final ObjectSerializer os;
        if(object == null)
        {
            os = serializers.get("nil");
        }
        else if(object.getClass().isArray())
        {
            os = serializers.get(Object[].class.getName());
        }
        else if(List.class.isAssignableFrom(object.getClass()))
        {
            os = serializers.get(List.class.getName());
        }
        else if(Map.class.isAssignableFrom(object.getClass()))
        {
            os = serializers.get(Map.class.getName());
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
        String result = writer.toString();
        int len = result.length();
        if(!config.getHasStartBrace())
        {
            if(result.startsWith("{"))
            {
                result = result.substring(1,len - 1);
            }
        }
        return result.trim();
    }
    public void writeChar(char c)
    {
        writer.writeChar(c);
    }

    public void writeString(String value)
    {
        writer.writeString(value);
    }
    public void writeCharacter(Character value)
    {
        writer.writeCharacter(value);
    }

    public void writeFieldName(String fieldName)
    {
        writer.writeFieldName(fieldName);
        if(!config.getCompressedFormat())
        {
            writeChar(' ');
        }
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
    public void writeBaseObject(Object object)
    {
        writer.writeBaseObject(object);
    }
    public char getLastChar()
    {
        return writer.getLastChar();
    }
    public void writeSeparator()
    {
        if(config.getCompressedFormat())
        {
            Character ch = getLastChar();
            if(ch == '\'' || ch == '"' || ch == '}' || ch == ']')
            {
                return ;
            }
            else
            {
                writeChar(config.getSeparator());
            }
        }
        else
        {
            writeln();
        }
    }
    public void writeStartCharWithFormat(Character c)
    {
        writeChar(c);
        if(!config.getCompressedFormat())
        {
            increaseTab();
            writeln();
        }
    }
    public void writeEndCharWithFormat(Character c)
    {
        if(!config.getCompressedFormat())
        {
            decreaseTab();
            writeln();
        }
        writeChar(c);
    }
}
