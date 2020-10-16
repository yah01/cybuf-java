package cybuf.parse.deserializer;

import cybuf.CybufException;

import java.lang.reflect.Type;

public class CharDeserializer implements ObjectDeserializer
{
    public final static CharDeserializer instance = new CharDeserializer();
    @Override
    public Object deserialize(Object object, CybufDeserializer deserializer, Type type)
    {
        String objectString = object.toString();
        if(objectString.length() > 1)
        {
            throw new CybufException("CharDeserializer error, not char type");
        }
        try
        {
            return objectString.charAt(0);
        }
        catch (Exception e)
        {
            throw new CybufException("CharDeserializer error, " + e.getMessage());
        }
    }
}
