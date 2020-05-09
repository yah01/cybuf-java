package cybuf.parse.deserializer;

import cybuf.CybufException;

public class DoubleDeserializer implements ObjectDeserializer
{
    public final static DoubleDeserializer instance = new DoubleDeserializer();
    @Override
    public <T> Object deserialize(Object object, CybufDeserializer deserializer,Class<T> clazz)
    {
        try
        {
            return Double.valueOf(object.toString());
        }
        catch(Exception e)
        {
            throw new CybufException("DoubleDeserializer error, " + e.getMessage());
        }
    }
}
