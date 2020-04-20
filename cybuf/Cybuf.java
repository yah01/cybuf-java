package cybuf;
import cybuf.parse.CybufParse;

public class Cybuf
{
    public static String toCybufString(Object object)
    {
        return "";
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