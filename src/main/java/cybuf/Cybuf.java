package cybuf;
import cybuf.parse.CybufParse;
import cybuf.serializer.CybufSerializer;

import java.lang.reflect.InvocationTargetException;

public class Cybuf
{
    public static String toCybufString(Object object)
    {
        CybufSerializer serializer = new CybufSerializer();
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