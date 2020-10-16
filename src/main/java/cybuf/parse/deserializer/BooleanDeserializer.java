package cybuf.parse.deserializer;

import cybuf.CybufException;

import java.lang.reflect.Type;

public class BooleanDeserializer implements ObjectDeserializer
{
    public final static BooleanDeserializer instance = new BooleanDeserializer();
    @Override
    public Object deserialize(Object object, CybufDeserializer deserializer, Type type)
    {
        try
        {
            return Boolean.valueOf(object.toString());
        }
        catch(Exception e)
        {
            throw new CybufException("BooleanDeserializer error, " + e.getMessage());
        }
    }
}
