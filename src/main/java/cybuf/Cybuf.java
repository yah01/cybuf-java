package cybuf;
import cybuf.parse.CybufParse;
import cybuf.parse.deserializer.CybufDeserializer;
import cybuf.serializer.CybufSerializer;
import cybuf.serializer.SerializerConfig;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Cybuf
{
    public static String toCybufString(Object object)
    {
        return toCybufString(object,true);
    }

    public static String toCybufString(Object object,Boolean compressedFormat)
    {
        return toCybufString(object,compressedFormat,true);
    }

    public static String toCybufString(Object object,Boolean compressedFormat,Boolean hasStartBrace)
    {
        return toCybufString(object,compressedFormat,hasStartBrace,SerializerConfig.SPACE);
    }

    public static String toCybufString(Object object,Boolean compressedFormat,Boolean hasStartBrace,Integer separator)
    {
        if(!compressedFormat)
        {
            separator = SerializerConfig.NEWLINE;
        }
        SerializerConfig config = new SerializerConfig(compressedFormat,hasStartBrace,separator);
        CybufSerializer serializer = new CybufSerializer(config);
        try
        {
            serializer.write(object);
            return serializer.toString();
        } catch (InvocationTargetException | IllegalAccessException e)
        {
            throw new CybufException(e.getMessage());
        }
    }

    public static CybufObject parseObject(String text)
    {
        text = text.trim();
        CybufParse parser = new CybufParse(text);
        Object obj = parser.parse();
        return (CybufObject) obj;
    }

    public static CybufArray parseArray(String text)
    {
        text = text.trim();
        CybufParse parser = new CybufParse(text);
        Object obj = parser.parse();
        return (CybufArray) obj;
    }

    public static <T> T parseObject(String text,Class<T> clazz)
    {
        Object cybufObject;
        if(clazz.isArray() || List.class.isAssignableFrom(clazz))
        {
            cybufObject = (CybufArray) parseArray(text);
        }
        else
        {
            cybufObject = (CybufObject) parseObject(text);
        }
        CybufDeserializer deserializer = new CybufDeserializer();
        Object object = deserializer.deserialize(cybufObject,clazz);
        return (T) object;
    }

}