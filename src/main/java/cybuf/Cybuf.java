package cybuf;
import cybuf.parse.CybufParse;
import cybuf.serializer.CybufSerializer;
import cybuf.serializer.SerializerConfig;

import java.lang.reflect.InvocationTargetException;

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
        return toCybufString(object,compressedFormat,hasStartBrace,' ');
    }

    public static String toCybufString(Object object,Boolean compressedFormat,Boolean hasStartBrace,Character separator)
    {
        if(!compressedFormat)
        {
            separator = ' ';
        }
        SerializerConfig config = new SerializerConfig(compressedFormat,hasStartBrace,separator);
        CybufSerializer serializer = new CybufSerializer(config);
        try
        {
            serializer.write(object);
            return serializer.toString();
        } catch (InvocationTargetException e)
        {
            throw new CybufException(e.getMessage());
        } catch (IllegalAccessException e)
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
        return null;
    }

}