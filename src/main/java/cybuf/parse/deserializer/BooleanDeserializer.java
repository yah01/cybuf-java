package cybuf.parse.deserializer;

import cybuf.CybufException;

public class BooleanDeserializer implements ObjectDeserializer
{
    public final static BooleanDeserializer instance = new BooleanDeserializer();
    @Override
    public <T> Object deserialize(Object object, CybufDeserializer deserializer,Class<T> clazz)
    {
        try
        {
            return (T) object;
        }
        catch(Exception e)
        {
            throw new CybufException("BooleanDeserializer error, " + e.getMessage());
        }
    }
}
