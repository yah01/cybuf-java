package cybuf.parse.deserializer;

import cybuf.CybufException;

import java.lang.reflect.Type;

public class IntegerDeserializer implements ObjectDeserializer
{
    public final static IntegerDeserializer instance = new IntegerDeserializer();
    @Override
    public Object deserialize(Object object, CybufDeserializer deserializer, Type type)
    {
        try
        {
            return Integer.valueOf(object.toString());
        }
        catch (Exception e)
        {
            throw new CybufException("IntegerDeserializer error, " + e.getMessage());
        }
    }
}
