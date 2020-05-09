package cybuf.parse.deserializer;

import cybuf.CybufException;

public class IntegerDeserializer implements ObjectDeserializer
{
    public final static IntegerDeserializer instance = new IntegerDeserializer();
    @Override
    public <T> Object deserialize(Object object, CybufDeserializer deserializer,Class<T> clazz)
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
